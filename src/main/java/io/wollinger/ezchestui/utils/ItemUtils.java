package io.wollinger.ezchestui.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class ItemUtils {
    public static ItemStack getHead(String username) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setOwner(username);
        item.setItemMeta(skull);
        return item;
    }

    public static ItemStack setInfo(ItemStack item, String name, List<String> desc) {
        ItemMeta meta = item.getItemMeta();

        if(name != null && !name.isEmpty())
            meta.setDisplayName(ChatColor.WHITE + name);
        if(desc != null && !desc.isEmpty())
            meta.setLore(desc);
        item.setItemMeta(meta);
        return item;
    }
}
