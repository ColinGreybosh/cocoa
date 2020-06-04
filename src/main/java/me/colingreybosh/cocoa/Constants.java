package me.colingreybosh.cocoa;

import java.util.regex.Pattern;

/**
 * Constants required for this bot to operate properly.
 */
public final class Constants {
    public static final Pattern COCOA_REGEX = Pattern.compile("[cC]+[oO]+[cC]+[oO]+[aA]+");
    // Fill in with path to file holding guild member data
    public static final String PATH_TO_DATA = "./src/main/java/me/colingreybosh/cocoa/tables/members.dt"; 
}
