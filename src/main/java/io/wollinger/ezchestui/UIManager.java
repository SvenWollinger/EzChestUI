package io.wollinger.ezchestui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.logging.Level;

public class UIManager implements Listener {
    private static UIManager instance;
    private static ArrayList<Inventory> openInventories = new ArrayList<>();

    public static void init(EzChestUI plugin) {
        if (instance == null) {
            instance = new UIManager();
        } else {
            EzChestUI.log("UIManager.init() called. This is not needed!", Level.WARNING);
            return;
        }

        plugin.getServer().getPluginManager().registerEvents(instance, plugin);
    }

    public static Inventory createInventory(String title, int rows) {
        int size = rows * 9;
        if(rows < 0 || rows > 6) {
            EzChestUI.log("CreateInventory failed! 'rows' must be between 1 and 6!", Level.SEVERE);
            return null;
        }
        return Bukkit.createInventory(null, size, title);
    }

    public static void openInventory(Player player, Inventory inv) {
        player.openInventory(inv);
        openInventories.add(inv);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        openInventories.remove(event.getInventory());
    }

}
