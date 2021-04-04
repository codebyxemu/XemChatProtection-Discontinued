// Code by Xemu (https://www.github.com/codebyxemu)
package me.xemu.xemchatprotectionplugin.events;

import me.xemu.xemchatprotectionplugin.XemChatProtectionPlugin;
import me.xemu.xemchatprotectionplugin.check.CheckResult;
import me.xemu.xemchatprotectionplugin.check.Checker;
import me.xemu.xemchatprotectionplugin.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    @EventHandler public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();

        if(Checker.checkMessage(message) == CheckResult.CLEAR) return;
        else if (Checker.checkMessage(message) == CheckResult.FOUND_ADVERTISING) {
            for (String s : XemChatProtectionPlugin.getMessages().getConfig().getStringList("blocked.advertising")) {
                player.sendMessage(Utils.colorize(s).replaceAll("<player>", player.getName()));
            }
            event.setCancelled(true);
            return;
        } else if (Checker.checkMessage(message) == CheckResult.FOUND_SWEARING) {
            for (String s : XemChatProtectionPlugin.getMessages().getConfig().getStringList("blocked.swearing")) {
                player.sendMessage(Utils.colorize(s).replaceAll("<player>", player.getName()));
            }
            event.setCancelled(true);
            return;
        } else if (Checker.checkMessage(message) == CheckResult.CLEAR){
            event.setCancelled(false);
            return;
        }
    }
}