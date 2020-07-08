package org.cjohnson.mcguici.mcguici.manager;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.cjohnson.mcguici.mcguici.menu.CIMenu;
import org.cjohnson.mcguici.mcguici.menu.element.CIMenuElement;

import java.util.ArrayList;

public class CIInventoryEventManager implements Listener {
    private static ArrayList<CIMenu> managedInventories;

    static {
        managedInventories = new ArrayList<>();
    }

    public CIInventoryEventManager() {}

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        for(CIMenu menu : CIInventoryEventManager.managedInventories) {
            if(menu.checkIsInventory(inventory)) {
                HumanEntity player = event.getWhoClicked();
                ItemStack clicked = event.getCurrentItem();

                CIMenuElement menuElement = menu.getAssociativeElement(event.getCurrentItem());

                if(menuElement == null)
                    return;

                menuElement.onInteract((Player) player);
            }
        }
    }

    public static CIMenu getMenu(int index) {
        return managedInventories.get(index);
    }

    public static boolean addMenu(CIMenu ciMenu) {
        return managedInventories.add(ciMenu);
    }
}
