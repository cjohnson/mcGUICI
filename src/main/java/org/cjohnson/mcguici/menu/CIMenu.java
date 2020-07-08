package org.cjohnson.mcguici.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.cjohnson.mcguici.McGUICI;
import org.cjohnson.mcguici.menu.element.CIMenuElement;

import java.util.ArrayList;

public class CIMenu {
    private Inventory bInventory;

    private String name;

    private ArrayList<ArrayList<CIMenuElement>> elementsByRow;

    public CIMenu(CIMenuBuilder ciMenuBuilder) {
        this.name = ciMenuBuilder.getName();

        this.elementsByRow = ciMenuBuilder.getMenuElementsByRow();

        setupBukkitMenu();
    }

    public void openInventory(Player player) {
        player.openInventory(bInventory);
    }

    public boolean checkIsInventory(Inventory checkInventory) {
        return checkInventory.equals(bInventory);
    }

    public CIMenuElement getAssociativeElement(ItemStack itemStack) {
        for(ArrayList<CIMenuElement> row : elementsByRow) {
            for(CIMenuElement menuElement : row) {
                if(menuElement.getFace().equals(itemStack))
                    return menuElement;
            }
        }

        return null;
    }

    private void setupBukkitMenu() {
        this.bInventory = Bukkit.createInventory(null, elementsByRow.size() * 9, name);

        int inventoryIndex = 0;
        for(ArrayList<CIMenuElement> row : elementsByRow) {
            for(CIMenuElement element : row) {
                bInventory.setItem(inventoryIndex, element.getFace());
                inventoryIndex++;
            }
        }
    }

    public Inventory getInventory() {
        return bInventory;
    }

    public void setInventory(Inventory bInventory) {
        this.bInventory = bInventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<CIMenuElement>> getElementsByRow() {
        return elementsByRow;
    }

    public void setElementsByRow(ArrayList<ArrayList<CIMenuElement>> elementsByRow) {
        this.elementsByRow = elementsByRow;
    }

    public static class CIMenuBuilder {
        public static final String DEFAULT_INVENTORY_NAME = "mcGUICI Inventory";

        private String name;

        private ArrayList<ArrayList<CIMenuElement>> menuElementsByRow;

        private int currentRow;

        public CIMenuBuilder() {
            this.name = DEFAULT_INVENTORY_NAME;

            this.menuElementsByRow = new ArrayList<>();

            this.currentRow = 0;

            //Initial Row
            menuElementsByRow.add(new ArrayList<>(9));
        }

        public CIMenu build() {
            return new CIMenu(this);
        }

        public CIMenuBuilder addRow() {
            currentRow++;

            menuElementsByRow.add(new ArrayList<>(9));

            System.out.println("SIZE BEFORE ERROR: " + menuElementsByRow.size());
            ArrayList<CIMenuElement> test1 = menuElementsByRow.get(currentRow);

            test1.add(0, new CIMenuElement());

            for(int i = 0; i < 9; i++)
                menuElementsByRow.get(currentRow).add(i, new CIMenuElement());

            return this;
        }

        public CIMenuBuilder addMenuElement(int column, CIMenuElement ciMenuElement) {
            // Bootleg Clamp Function
            column = Math.max(0, Math.min(8, column));

            menuElementsByRow.get(currentRow).set(column, ciMenuElement);
            return this;
        }

        public String getName() {
            return name;
        }

        public CIMenuBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ArrayList<ArrayList<CIMenuElement>> getMenuElementsByRow() {
            return menuElementsByRow;
        }

        public void setMenuElementsByRow(ArrayList<ArrayList<CIMenuElement>> menuElementsByRow) {
            this.menuElementsByRow = menuElementsByRow;
        }
    }
}
