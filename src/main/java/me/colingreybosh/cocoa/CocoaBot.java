package me.colingreybosh.cocoa;

import java.util.Set;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.requests.GatewayIntent;

/**
 * An ADT representing a discord bot that interacts with users of the 
 * Slugfest Discord server.
 * 
 * @author Colin Greybosh
 *
 */
public class CocoaBot extends Bot {
    
    public CocoaBot(String token) {
        super(token, Set.of(new CocoaBotListener()), Set.of(GatewayIntent.GUILD_MEMBERS));
        // TODO listeners and intents
    }
    
    public static void main(String[] arguments) {
        System.err.println("Creating bot...");
        final CocoaBot bot = new CocoaBot(Constants.TOKEN);
        System.err.println("Bot created!");
        System.err.println("Connecting bot...");
        try {
            bot.start();
            System.err.println("Bot logged in!");
        } catch (LoginException | IllegalArgumentException | InterruptedException e) {
            System.err.println("Bot failed to log in!");
            e.printStackTrace();
        }
    }
}
