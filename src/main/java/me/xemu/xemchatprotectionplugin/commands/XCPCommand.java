// Code by Xemu (https://www.github.com/codebyxemu)
package me.xemu.xemchatprotectionplugin.commands;

import me.xemu.xemchatprotectionplugin.XemChatProtectionPlugin;
import me.xemu.xemchatprotectionplugin.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class XCPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Utils.colorize(XemChatProtectionPlugin.getMessages().getConfig().getString("non-player")));
            return true;
        }

        Player p = (Player) sender;

        if(args.length == 0) {
            if(!p.hasPermission("xcp.admin")) {
                for (String s : XemChatProtectionPlugin.getMessages().getConfig().getStringList("help-message-default")) {
                    p.sendMessage(Utils.colorize(s));
                }
                return true;
            }

            for (String s : XemChatProtectionPlugin.getMessages().getConfig().getStringList("help-message-admin")) {
                p.sendMessage(Utils.colorize(s));
            }

            return true;
        }

        if(args.length > 0) {

            if(args[0].equalsIgnoreCase("reload")) {
                XemChatProtectionPlugin.getConfiguration().reloadConfig();
                XemChatProtectionPlugin.getMessages().reloadConfig();
                XemChatProtectionPlugin.getStorage().reloadConfig();
                p.sendMessage(Utils.colorize(XemChatProtectionPlugin.getMessages().getConfig().getString("reload")));
                return true;
            } else if (args[0].equalsIgnoreCase("version")) {
                p.sendMessage(Utils.colorize(XemChatProtectionPlugin.getMessages().getConfig().getString("version").replaceAll("<version>", XemChatProtectionPlugin.XemChatProtectionPlugin().getDescription().getVersion())));
                return true;
            } else {
                p.sendMessage(Utils.colorize(XemChatProtectionPlugin.getMessages().getConfig().getString("invalid-subcommand")));
            }

        }

        return true;
    }

}