package net.reg.dsmcbot.events;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.reg.dsmcbot.Data;
import net.reg.dsmcbot.MCBot;
import net.reg.dsmcbot.types.DiscordCommand;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Discord extends ListenerAdapter {
    public static List<DiscordCommand> commands = new ArrayList<>();
    private static List<Long> listeningIDs = new LinkedList<>();
    private static List<Long> consoleIDs = new LinkedList<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getIdLong() == 906230175035965471L) {
            if (event.getAuthor().getDiscriminator().equalsIgnoreCase("1241") || event.getAuthor().getDiscriminator().equalsIgnoreCase("0000")) return;
            Bukkit.broadcastMessage(event.getAuthor().getAsTag()+" >> "+event.getMessage().getContentDisplay());
        }
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (!Data.ids.contains(event.getAuthor().getIdLong())) return;

        if (consoleIDs.contains(event.getAuthor().getIdLong())) {
            if (event.getMessage().getContentDisplay().equalsIgnoreCase("exit")) {
                consoleIDs.remove(event.getAuthor().getIdLong());
            } else {
                Bukkit.getScheduler().scheduleSyncDelayedTask(MCBot.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), event.getMessage().getContentDisplay()));
                event.getMessage().addReaction("☑️").queue();
            }
            return;
        }

        int task = -1;
        for (DiscordCommand command:commands) {
            if (command.isThisCommand(event.getMessage().getContentDisplay())) {
                task = command.getId();
                break;
            }
        }
        if (task == -1) return;


        if (task == 0) if (listeningIDs.contains(event.getAuthor().getIdLong())) listeningIDs.remove(event.getAuthor().getIdLong());
        if (task == 1) if (!listeningIDs.contains(event.getAuthor().getIdLong())) listeningIDs.add(event.getAuthor().getIdLong());
        if (task == 1 || task == 0) return;

        TextChannel channel = event.getChannel();
        switch (task) {
            case 2: playerOnlineN(channel);break;
            case 3: playerOnlineNames(channel);break;
            case 4: serverTPS(channel);break;
            case 5: {if (!consoleIDs.contains(event.getAuthor().getIdLong())) consoleIDs.add(event.getAuthor().getIdLong()); channel.sendMessage("Now sending commands in console...").queue();}break;
        }
    }

    private void playerOnlineN(TextChannel target) {
        target.sendMessage("Player online: " + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()).queue();
    }

    private void playerOnlineNames(TextChannel target) {
        String result = "";
        for (Player online:Bukkit.getOnlinePlayers()) result+="> "+online.getName()+"\n";
        target.sendMessage("Players:\n"+result).queue();
    }


    private static Field recentTps;
    private static Object minecraftServer;
    private static double[] getRecentTpsRefl() throws Throwable {
        if (minecraftServer == null) {
            Server server = Bukkit.getServer();
            Field consoleField = server.getClass().getDeclaredField("console");
            consoleField.setAccessible(true);
            minecraftServer = consoleField.get(server);
        }
        if (recentTps == null) {
            recentTps = minecraftServer.getClass().getSuperclass().getDeclaredField("recentTps");
            recentTps.setAccessible(true);
        }
        return (double[]) recentTps.get(minecraftServer);
    }

    private void serverTPS(TextChannel target) {
        try {
            target.sendMessage(getRecentTpsRefl()[0] + " TPS").queue();
        } catch (Throwable ignored) {
            target.sendMessage("Unable to compute...").queue();
        }
    }
}