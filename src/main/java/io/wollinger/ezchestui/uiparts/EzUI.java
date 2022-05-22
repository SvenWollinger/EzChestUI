package io.wollinger.ezchestui.uiparts;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EzUI {
    private Inventory inv;
    public EzUI(String title, int size) {
        inv = Bukkit.createInventory(null, size, title);
    }

    public void setItem(int slot, ItemStack item) {
        inv.setItem(slot, item);
    }

    public Inventory getInventory() {
        return inv;
    }
}
