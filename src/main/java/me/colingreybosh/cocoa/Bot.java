package me.colingreybosh.cocoa;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

/**
 * A mutable ADT representing a basic Discord bot.
 * 
 * @author Colin Greybosh
 *
 */
public class Bot {

    private final String token;
    private final Set<Object> listeners;
    private final Set<GatewayIntent> intents;
    
    /*
     * Abstraction Function:
     *   AF(token, listeners, intents) = a bot running on the Discord application denoted by the `token`
     *                                   with all the listener methods in `listeners` that operates with
     *                                   all intent flags in `intents` enabled along with JDA's default
     *                                   intents.
     *   
     * Representation Invariant:
     *   the entries of listeners and intents are non-null
     * 
     * Safety From Representation Exposure:
     *   all fields are private and final
     *   all sets are returned within an unmodifiable wrapper
     *   the objects contained in the representation sets are immutable
     *   the collections received in the constructors are defensively copied
     */
    
    /**
     * Create a new bot.
     * 
     * @param token A Discord bot application token
     */
    public Bot(String token) {
        this.token = token;
        this.listeners = new HashSet<>();
        this.intents = new HashSet<>(Set.of(GatewayIntent.GUILD_MEMBERS));
        checkRep();
    }
    
    public Bot(String token, Set<Object> listeners) {
        this.token = token;
        this.listeners = new HashSet<>(listeners);
        this.intents = new HashSet<>(Set.of(GatewayIntent.GUILD_MEMBERS));
        checkRep();
    }
    
    public Bot(String token, Set<Object> listeners, Set<GatewayIntent> intents) {
        this.token = token;
        this.listeners = new HashSet<>(listeners);
        this.intents = new HashSet<>(intents);
        checkRep();
    }
    
    public static void main(String[] arguments) {
        System.err.println("Creating bot...");
        final Bot bot = new Bot(Constants.TOKEN);
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
    
    /**
     * Asserts the representation invariant.
     */
    private void checkRep() {
       assert token != null;
       for (Object listener : listeners) {
           assert listener != null;
       }
       for (Object intent : intents) {
           assert intent != null;
       }
    }
    
    /**
     * Connect to Discord.
     * 
     * @return The current JDA instance.
     * @throws LoginException If this bot's token is invalid
     * @throws IllegalArgumentException If this bot's token is empty or null
     * @throws InterruptedException If this bot's thread was interrupted while waiting to connect to Discord
     */
    public JDA start() throws LoginException, IllegalArgumentException, InterruptedException {
        final JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        if (!intents.isEmpty()) {
            jdaBuilder.enableIntents(intents);
        }
        if (!listeners.isEmpty()) {
            jdaBuilder.addEventListeners(listeners);
        }
        return jdaBuilder.build().awaitReady();
    }
    
    /**
     * Get this bot's token.
     * 
     * @return This bot's token
     */
    public String getToken() {
        return token;
    }
    
    /**
     * Add a listener to this bot.
     * 
     * @param listener The listener to add
     * @return {@code true} if this bot did not already have this listener  
     */
    public boolean addListener(Object listener) {
        boolean result = listeners.add(listener);
        checkRep();
        return result;
    }
    
    /**
     * Get an unmodifiable view of this bot's listeners.
     * 
     * @return This bot's listeners
     */
    public Set<Object> getListeners() {
        return Collections.unmodifiableSet(listeners);
    }
    
    /**
     * Add an intent to this bot.
     * 
     * @param intent The intent to add
     * @return {@code true} if this bot did not already have this intent
     */
    public boolean addIntent(GatewayIntent intent) {
        boolean result = intents.add(intent);
        checkRep();
        return result;
    }
    
    /**
     * Get an unmodifiable view of this bot's intents.
     * 
     * @return this bot's intents.
     */
    public Set<GatewayIntent> getIntents() {
        return Collections.unmodifiableSet(intents);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object that) {
        return that instanceof Bot && sameValue((Bot) that);
    }
    
    /**
     * Checks for equality between this bot and another bot instance. 
     * 
     * @param that Another bot instance
     * @return {@code true} if this bot and that bot are observationally equal
     */
    public boolean sameValue(Bot that) {
        return getToken().equals(that.getToken()) 
                && getListeners().equals(that.getListeners())
                && getIntents().equals(that.getIntents());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(token, listeners, intents);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[Bot %s, %s, %s]", token, listeners, intents);
    }
}
