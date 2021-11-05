package net.reg.dsmcbot.events;

import net.reg.dsmcbot.Data;
import net.reg.dsmcbot.DiscordWebhook;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.io.IOException;

public class Minecraft implements Listener {
    byte currentHook = 0;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        DiscordWebhook webhook = prepareWebHook(event.getPlayer().getName());
        webhook.setUsername("Server Joins");
        webhook.setTts(false);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(event.getPlayer().getName() + " joined the server.")
                .setColor(Color.RED));
        webhook.execute();
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) throws IOException {
        DiscordWebhook webhook = prepareWebHook(event.getPlayer().getName());
        webhook.setUsername("Server Quits");
        webhook.setTts(false);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(event.getPlayer().getName() + " left the server.")
                .setColor(Color.RED));
        webhook.execute();
    }

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent event) throws IOException {
        DiscordWebhook webhook = prepareWebHook(event.getPlayer().getName());
        webhook.setUsername("Server Messages");
        webhook.setTts(false);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(event.getPlayer().getName() + " said:")
                .setDescription(event.getMessage())
                .setColor(Color.ORANGE));
        webhook.execute();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) throws IOException {
        DiscordWebhook webhook = prepareWebHook(event.getEntity().getName());
        webhook.setUsername("Server Deaths");
        webhook.setTts(false);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(event.getDeathMessage())
                .setColor(Color.ORANGE));
        webhook.execute();
    }





    private DiscordWebhook prepareWebHook(String player) {
        if (currentHook >= Data.hooks.length-1) currentHook = 0; else currentHook++;
        String name = player;
        String target = Data.hooks[currentHook];
        DiscordWebhook webhook = new DiscordWebhook(target);
        webhook.setAvatarUrl("https://crafthead.net/helm/"+name);
        return webhook;
    }
}