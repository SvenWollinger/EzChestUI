package io.wollinger.ezchestui.uiparts;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class EzUI {
    private Inventory inv;
    private HashMap<Integer, EzUIItem> items = new HashMap<>();

    public EzUI(String title, int size) {
        inv = Bukkit.createInventory(null, size, title);
    }

    public void setItem(int slot, EzUIItem item) {
        inv.setItem(slot, item.getItemStack());
        items.put(slot, item);
    }

    public Inventory getInventory() {
        return inv;
    }

    public EzUIItem getFromSlot(int slot) {
        return items.get(slot);
    }
}
