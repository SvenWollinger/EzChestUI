package io.wollinger.ezchestui;

import io.wollinger.ezchestui.commands.CMDDemoUI;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EzChestUI extends JavaPlugin {
    private static EzChestUI instance;
    private static Logger logger;

    @Override
    public void onEnable() {
        instance = this;
        UIManager.init(this);
        instance.getCommand("demoui").setExecutor(new CMDDemoUI());
    }

    public static void log(String message, Level level) {
        instance.getLogger().log(level, message);
    }

}
