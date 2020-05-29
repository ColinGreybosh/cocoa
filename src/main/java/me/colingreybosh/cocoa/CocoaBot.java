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
    }
    
    public static void main(String[] arguments) {
        final CocoaBot bot = new CocoaBot(Constants.TOKEN);
        try {
            bot.start();
        } catch (LoginException | IllegalArgumentException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
