package io.wollinger.ezchestui.utils;

import io.wollinger.ezchestui.EzChestUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

public class ItemUtils {
    private static final NamespacedKey ITEM_KEY = new NamespacedKey(EzChestUI.getPluginInstance(), "ezui-item");
    private static final NamespacedKey COC_KEY = new NamespacedKey(EzChestUI.getPluginInstance(), "ezui-coc");
    private static final NamespacedKey FUNC_KEY = new NamespacedKey(EzChestUI.getPluginInstance(), "ezui-func");

    public static ItemStack getHead(String username) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setOwner(username);
        item.setItemMeta(skull);
        return item;
    }

    public static void clearAllKeys(ItemStack item) {
        setEzUIItem(item, false);
        setCloseOnClick(item, false);
        setEzUIFunction(item, null);
    }

    public static void setEzUIFunction(ItemStack item, String key) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer c = meta.getPersistentDataContainer();
        if(key != null && !key.isEmpty())
            c.set(FUNC_KEY, PersistentDataType.STRING, key);
        else
            c.remove(FUNC_KEY);
    }

    public static String getEzUIFunction(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer().get(FUNC_KEY, PersistentDataType.STRING);
    }

    public static void setEzUIItem(ItemStack item, boolean isEzUIItem) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer c = meta.getPersistentDataContainer();
        if(isEzUIItem)
            c.set(ITEM_KEY, PersistentDataType.INTEGER, 1);
        else
            c.remove(ITEM_KEY);
        item.setItemMeta(meta);
    }

    public static boolean isEzUIItem(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer().has(ITEM_KEY, PersistentDataType.INTEGER);
    }

    public static void setCloseOnClick(ItemStack item, boolean closeOnClick) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer c = meta.getPersistentDataContainer();
        if (closeOnClick)
            c.set(COC_KEY, PersistentDataType.INTEGER, 1);
        else
            c.remove(COC_KEY);
        item.setItemMeta(meta);
    }

    public static boolean isCloseOnClick(ItemStack item) {
        return item.getItemMeta().getPersistentDataContainer().has(COC_KEY, PersistentDataType.INTEGER);
    }

    public static void setLore(ItemStack item, String lore) {
        String[] arr = lore.split("\n");
        ItemUtils.setLore(item, Arrays.asList(arr));
    }

    public static void setName(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        if(meta == null)
            return;

        if(name != null && !name.isEmpty())
            meta.setDisplayName(ChatColor.WHITE + name);

        item.setItemMeta(meta);
    }

    public static void setLore(ItemStack item, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        if(meta == null)
            return;

        if(lore != null)
            meta.setLore(lore);

        item.setItemMeta(meta);
    }

    public static ItemStack getStack(Material material) {
        return new ItemStack(material);
    }
}
