package org.cjohnson.mcguici.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cjohnson.mcguici.manager.CIInventoryEventManager;
import org.cjohnson.mcguici.menu.CIMenu;

import java.util.ArrayList;

public class CommandMenu implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("menu")) {
            if(sender instanceof Player) {
                Player player = (Player) sender;

                if(args.length < 1) {
                    ArrayList<CIMenu> menus = CIInventoryEventManager.getManagedInventories();
                    for(int i = 0; i < menus.size(); i++) {
                        player.sendMessage(i + ": " + menus.get(i).getName());
                    }

                    return false;
                }

                int menuNumber;
                try {
                    menuNumber = Integer.parseInt(args[0]);
                } catch(NumberFormatException exception) {
                    player.sendMessage("ERROR: Couldn't parse int from " + args[0]);
                    return false;
                }

                CIMenu menu = CIInventoryEventManager.getMenu(menuNumber);
                player.sendMessage("Opening menu: [" + menuNumber + "] " + menu.getName());
                menu.openInventory(player);
            }
        }

        return false;
    }

}
