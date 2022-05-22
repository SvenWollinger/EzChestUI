package io.wollinger.ezchestui.commands;

import io.wollinger.ezchestui.EzChestUI;
import io.wollinger.ezchestui.uiparts.EzUI;
import io.wollinger.ezchestui.utils.ItemUtils;
import io.wollinger.ezchestui.UIManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
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

        ArrayList<String> desc = new ArrayList<>();
        desc.add("Cool head with cool description!");
        desc.add(ChatColor.RED + "Yeah!");
        desc.add(ChatColor.WHITE + ":)");
        inv.setItem(0, ItemUtils.setInfo(ItemUtils.getHead("SvenWollinger"), "Cool head!", desc));

        UIManager.openInventory((Player) sender, inv);

        return true;
    }
}
