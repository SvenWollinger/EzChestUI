package io.wollinger.ezchestui.uiparts;

import io.wollinger.ezchestui.EzChestUI;
import io.wollinger.ezchestui.utils.ItemUtils;
import io.wollinger.ezchestui.utils.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;
import java.util.HashMap;

public class EzUI {
    private final Inventory inv;
    private final HashMap<String, ClickFunction> functions = new HashMap<>();

    public EzUI(String title, int size) {
        inv = Bukkit.createInventory(null, size, title);
    }

    public void setItem(int slot, ItemStack item, boolean closeOnClick, ClickFunction function) {
        ItemUtils.setEzUIItem(item, true);
        ItemUtils.setCloseOnClick(item, closeOnClick);
        String funcKey = RandomUtils.randomKey();
        ItemUtils.setEzUIFunction(item, funcKey);
        functions.put(funcKey, function);
        inv.setItem(slot, item);
    }

    public Inventory getInventory() {
        return inv;
    }

    public ItemStack getFromSlot(int slot) {
        return inv.getItem(slot);
    }
}
