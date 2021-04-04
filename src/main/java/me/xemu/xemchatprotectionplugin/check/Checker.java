// Code by Xemu (https://www.github.com/codebyxemu)
package me.xemu.xemchatprotectionplugin.check;

import me.xemu.xemchatprotectionplugin.XemChatProtectionPlugin;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {

    public static CheckResult checkMessage(String string) {

        // Swearing
        String s = string.toLowerCase();
        for (String str : XemChatProtectionPlugin.getConfiguration().getConfig().getStringList("blocked-words")) {
            if(s.contains(str)) {
                return CheckResult.FOUND_SWEARING;
            }
        }

        // Unicode
        Pattern pattern = Pattern.compile("^[\\u0000-\\u007F  \\p{Sc}]*$");
        Matcher matcher = pattern.matcher(s);
        if(matcher.find())
        {
            return CheckResult.FOUND_UNICODE;
        };

        // Advertising
        List<String> allowed = XemChatProtectionPlugin.getConfiguration().getConfig().getStringList("advertising-allowed");
        List<String> disallowed = XemChatProtectionPlugin.getConfiguration().getConfig().getStringList("advertising-disallowed");
        for (String str : disallowed) {
            if(s.contains(str) && !allowed.contains(str)) {
                return CheckResult.FOUND_ADVERTISING;
            }
        }

        return CheckResult.CLEAR;
    }

}