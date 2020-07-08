package org.cjohnson.mcguici;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.cjohnson.mcguici.command.CommandMenu;
import org.cjohnson.mcguici.manager.CIInventoryEventManager;
import org.cjohnson.mcguici.menu.CIMenu;
import org.cjohnson.mcguici.menu.element.CIMenuElement;

public final class McGUICI extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new CIInventoryEventManager(), this);

        this.getCommand("menu").setExecutor(new CommandMenu());

        // Quick Test Case
        CIMenu ciMenu = new CIMenu.CIMenuBuilder() // ROW 0
                .addRow() // ROW 1
                .addMenuElement(4, new CIMenuElement(new ItemStack(Material.DIRT), (player) -> System.out.println("TEST MESSAGE")))
                .build(); // NEW (empty) ELEMENT at (1, 0)

        CIInventoryEventManager.addMenu(ciMenu);
    }

    @Override
    public void onDisable() { }
}
