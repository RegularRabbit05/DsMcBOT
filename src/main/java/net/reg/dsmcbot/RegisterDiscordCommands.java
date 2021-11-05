package net.reg.dsmcbot;

import net.reg.dsmcbot.events.Discord;
import net.reg.dsmcbot.types.DiscordCommand;

import java.util.Arrays;

public class RegisterDiscordCommands {
    public static void registerAll() {
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"stop"}), 0, (byte) 100));

        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"comandi"}), 1, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"ascolta"}), 1, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"send"}), 1, (byte) 100));

        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"quanti","online"}), 2, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"quante","online"}), 2, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"quanti","connessi"}), 2, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"player","connessi"}), 2, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"giocatori","connessi"}), 2, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"giocatori","online"}), 2, (byte) 100));

        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"chi","online"}), 3, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"quali","online"}), 3, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"quali","connessi"}), 3, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"quali","connesso"}), 3, (byte) 100));

        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"laggando"}), 4, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"quanti","tps"}), 4, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"quali","tps"}), 4, (byte) 100));
        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"quale","tps"}), 4, (byte) 100));

        Discord.commands.add(new DiscordCommand(Arrays.asList(new String[]{"console"}), 5, (byte) 100));


    }
}