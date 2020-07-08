package org.cjohnson.mcguici.mcguici;

import org.bukkit.plugin.java.JavaPlugin;
import org.cjohnson.mcguici.mcguici.manager.CIInventoryEventManager;
import org.cjohnson.mcguici.mcguici.menu.CIMenu;
import org.cjohnson.mcguici.mcguici.menu.element.CIMenuElement;

public final class McGUICI extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new CIInventoryEventManager(), this);

        // Quick Test Case
        CIMenu ciMenu = new CIMenu.CIMenuBuilder() // ROW 0
                .addRow() // ROW 1
                .addMenuElement(0, new CIMenuElement())
                .build(); // NEW (empty) ELEMENT at (1, 0)

        CIInventoryEventManager.addMenu(ciMenu);
    }

    @Override
    public void onDisable() { }
}
