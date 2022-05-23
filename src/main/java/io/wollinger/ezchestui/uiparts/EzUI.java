package io.wollinger.ezchestui.uiparts;

import io.wollinger.ezchestui.EzChestUI;
import io.wollinger.ezchestui.utils.ItemUtils;
import io.wollinger.ezchestui.utils.RandomUtils;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;
import java.util.HashMap;
import java.util.logging.Level;

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

    public void executeItem(ItemStack item, HumanEntity user, InventoryClickEvent event) {
        if(item == null || !ItemUtils.isEzUIItem(item))
            return;

        event.setCancelled(true);

        String key = ItemUtils.getEzUIFunction(item);
        ClickFunction function = functions.get(key);

        if(function != null)
            function.run();

        if(ItemUtils.isCloseOnClick(item)) {
            user.closeInventory();
            ItemUtils.clearAllKeys(item);
        }
    }

    public Inventory getInventory() {
        return inv;
    }

    public ItemStack getFromSlot(int slot) {
        return inv.getItem(slot);
    }
}
