// Code by Xemu (https://www.github.com/codebyxemu)
package me.xemu.xemchatprotectionplugin;

import me.xemu.xemchatprotectionplugin.commands.XCPCommand;
import me.xemu.xemchatprotectionplugin.config.Configuration;
import me.xemu.xemchatprotectionplugin.events.ChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import sun.security.krb5.Config;

import java.util.HashMap;

public final class XemChatProtectionPlugin extends JavaPlugin {

    private static XemChatProtectionPlugin instance;
    public static XemChatProtectionPlugin XemChatProtectionPlugin() {
        return instance;
    }

    private static Configuration configuration;
    private static Configuration messages;
    private static Configuration storage;

    @Override
    public void onEnable() {
        instance = this;

        configuration = new Configuration(XemChatProtectionPlugin(), "config.yml");
        messages = new Configuration(XemChatProtectionPlugin(), "messages.yml");
        storage = new Configuration(XemChatProtectionPlugin(), "storage.yml");

        getLogger().info("Plugin Enabled");

        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getCommand("xcp").setExecutor(new XCPCommand());

        configuration.getConfig().options().copyDefaults(true);
        configuration.saveConfig();

        messages.getConfig().options().copyDefaults(true);
        messages.saveConfig();

        storage.getConfig().options().copyDefaults(true);
        storage.saveConfig();
    }

    @Override
    public void onDisable() {
        instance = null;

        getLogger().info("Plugin Disabled");
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static Configuration getMessages() {
        return messages;
    }

    public static Configuration getStorage() {
        return storage;
    }

}
