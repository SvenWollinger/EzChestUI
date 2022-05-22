package io.wollinger.ezchestui;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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

        Inventory inv = UIManager.createInventory("Demo", 6);

        UIManager.openInventory((Player) sender, inv);

        return true;
    }
}
