package io.wollinger.ezchestui;

import io.wollinger.ezchestui.uiparts.EzUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
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
        Inventory clickedInventory = event.getClickedInventory();
        if(clickedInventory == null || openInventories.containsKey(event.getInventory()))
            return;

        boolean isShiftClick = event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT;
        boolean isPlayerInv = clickedInventory.getType() == InventoryType.PLAYER;

        if(isPlayerInv && isShiftClick) {
            event.setCancelled(true);
            return;
        }

        EzUI ui = openInventories.get(event.getInventory());
        boolean success = ui.executeItem(event.getCurrentItem(), event.getWhoClicked(), event);
        if(!success && !isPlayerInv)
            event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if(openInventories.containsKey(event.getInventory())) {
            boolean cancel = false;
            int size = event.getInventory().getSize();
            for(Integer i : event.getRawSlots()) {
                if(i < size) {
                    cancel = true;
                    break;
                }
            }
            event.setCancelled(cancel);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        openInventories.remove(event.getInventory());
    }

}
