package io.wollinger.ezchestui.uiparts;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class EzUIItem {
    private ItemStack itemStack;
    private ClickFunction function;

    public EzUIItem(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setName(String name) {
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null)
            return;

        if(name != null && !name.isEmpty())
            meta.setDisplayName(ChatColor.WHITE + name);

        itemStack.setItemMeta(meta);
    }

    public void setLore(String lore) {
        String[] arr = lore.split("\n");
        setLore(Arrays.asList(arr));
    }

    public void setLore(List<String> lore) {
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null)
            return;

        if(lore != null)
            meta.setLore(lore);

        itemStack.setItemMeta(meta);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setClickFunction(ClickFunction function) {
        this.function = function;
    }

    public void runClickFunction() {
        function.run();
    }
}
