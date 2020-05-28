package me.colingreybosh.cocoa;

import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CocoaBotListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        } else if (event.getMessage().getContentRaw().equals("!cocoa")) {
            final MessageChannel channel = event.getChannel();
            channel.sendMessage("☕ COOOOOOOOOOOCOOOOOOOOOOOOOOOOAAAAAAAA!!! ☕").queue();
        } else if (event.getMessage().getContentRaw().matches(/* TODO */ "")) {
            final MessageChannel channel = event.getChannel();
            channel.sendMessage("☕ COOOOOOOOOOOCOOOOOOOOOOOOOOOOAAAAAAAA!!! ☕").queue();
        }
    }
}
