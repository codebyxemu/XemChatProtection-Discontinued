// Code by Xemu (https://www.github.com/codebyxemu)
package me.xemu.xemchatprotectionplugin.utils;

import org.bukkit.ChatColor;

public class Utils {

    public static String colorize(final String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
    
}