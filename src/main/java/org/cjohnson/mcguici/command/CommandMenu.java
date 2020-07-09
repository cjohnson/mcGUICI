package org.cjohnson.mcguici.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;
import org.cjohnson.mcguici.manager.CIInventoryEventManager;
import org.cjohnson.mcguici.menu.CIMenu;

import java.util.ArrayList;

@CommandAlias("menu|guici|m")
public class CommandMenu extends BaseCommand {
    
    @SuppressWarnings("unused")
    @Default
    @Subcommand("list|l")
    @Description("Get a list of all the currently active menus.")
    public static void onListMenuCommand(Player player, String[] args) {
        ArrayList<CIMenu> menus = CIInventoryEventManager.getManagedInventories();
        for(int i = 0; i < menus.size(); i++) {
            player.sendMessage(i + ": " + menus.get(i).getName());
        }
    }

    @SuppressWarnings("unused")
    @Subcommand("open")
    @Description("Open a menu based on the given menu ID.")
    public static void onOpenMenuCommand(Player player, String[] args) {
        if(args.length < 1) {
            player.sendMessage("Here is a list of possible menus...");
            onListMenuCommand(player, args);
            return;
        }

        int menuNumber;
        try {
            menuNumber = Integer.parseInt(args[0]);
        } catch(NumberFormatException exception) {
            player.sendMessage("ERROR: Couldn't parse int from " + args[0]);
            return;
        }

        CIMenu menu = CIInventoryEventManager.getMenu(menuNumber);
        player.sendMessage("Opening menu: [" + menuNumber + "] " + menu.getName());
        menu.openInventory(player);
    }
}
