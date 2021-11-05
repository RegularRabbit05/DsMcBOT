package net.reg.dsmcbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.reg.dsmcbot.events.Discord;
import net.reg.dsmcbot.events.Minecraft;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class MCBot extends JavaPlugin {
    static JDA jda;
    private static MCBot mcBot;

    @Override
    public void onEnable() {
        mcBot = this;
        new Thread(() -> {
            JDABuilder builder = JDABuilder.createDefault(Data.tkn).addEventListeners(new Discord());
            try {
                jda = builder.build().awaitReady();
                jda.getGuildById(896049448914915338L).getTextChannelById(906230175035965471L).sendMessage("MINECRAFT: Sync system started...").complete();
            } catch (LoginException | InterruptedException e) {

            }
        }).start();
        RegisterDiscordCommands.registerAll();
        getServer().getPluginManager().registerEvents(new Minecraft(), this);
    }

    @Override
    public void onDisable() {
        jda.getGuildById(896049448914915338L).getTextChannelById(906230175035965471L).sendMessage("MINECRAFT: Sync system shutting down...").queue(e -> {
            jda.shutdownNow();
        });
    }

    public static MCBot getInstance(){
        return mcBot;
    }
}
