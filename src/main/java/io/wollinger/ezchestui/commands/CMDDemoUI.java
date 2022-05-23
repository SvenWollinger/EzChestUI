package io.wollinger.ezchestui.commands;

import io.wollinger.ezchestui.EzChestUI;
import io.wollinger.ezchestui.uiparts.EzUI;
import io.wollinger.ezchestui.utils.ItemUtils;
import io.wollinger.ezchestui.UIManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class CMDDemoUI implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("ezchestui.demoui"))
            return true;

        if(!(sender instanceof Player)) {
            EzChestUI.log("The demoui command can only be run by players!", Level.INFO);
            return true;
        }

        EzUI inv = UIManager.createInventory("Demo", 6);

        ItemStack headItem = ItemUtils.getHead("SvenWollinger");
        ItemUtils.setName(headItem, "Cool head!");
        ItemUtils.setLore(headItem, "Line 1!\n" + ChatColor.WHITE + "Line 2!\nLine 3!");
        inv.setItem(0, headItem, true, () -> sender.sendMessage("Cool !"));

        ItemStack funkyDirt = ItemUtils.getStack(Material.DIRT);
        ItemUtils.setName(funkyDirt, "FUNKY dirt!");
        ItemUtils.setLore(funkyDirt, "Note: Do not consume.");
        inv.setItem(10, funkyDirt, false, () -> sender.sendMessage("Munch munch..."));

        UIManager.openInventory((Player) sender, inv);

        return true;
    }
}
