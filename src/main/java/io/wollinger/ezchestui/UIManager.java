package io.wollinger.ezchestui;

import io.wollinger.ezchestui.uiparts.EzUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.logging.Level;

public class UIManager implements Listener {
    private static UIManager instance;

    private static final HashMap<Inventory, EzUI> openInventories = new HashMap<>();

    public static void init(EzChestUI plugin) {
        if (instance == null) {
            instance = new UIManager();
        } else {
            EzChestUI.log("UIManager.init() called. This is not needed!", Level.WARNING);
            return;
        }

        plugin.getServer().getPluginManager().registerEvents(instance, plugin);
    }

    public static EzUI createInventory(String title, int rows) {
        int size = rows * 9;
        if(rows < 0 || rows > 6) {
            EzChestUI.log("CreateInventory failed! 'rows' must be between 1 and 6!", Level.SEVERE);
            return null;
        }
        return new EzUI(title, size);
    }

    public static void openInventory(Player player, EzUI ui) {
        player.openInventory(ui.getInventory());
        openInventories.put(ui.getInventory(), ui);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(openInventories.containsKey(event.getInventory())) {
            EzUI ui = openInventories.get(event.getInventory());
            ui.executeItem(event.getCurrentItem(), event.getWhoClicked(), event);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        openInventories.remove(event.getInventory());
    }

}
