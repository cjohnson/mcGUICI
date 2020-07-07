package org.cjohnson.mcguici.mcguici.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.cjohnson.mcguici.mcguici.menu.element.CIMenuElement;

import java.util.ArrayList;

public class CIMenu {
    private Inventory bInventory;

    private String name;
    private int rows;

    private ArrayList<ArrayList<CIMenuElement>> elementsByRow;

    public CIMenu(CIMenuBuilder ciMenuBuilder) {
        this.name = ciMenuBuilder.getName();
        this.rows = ciMenuBuilder.getRows();

        this.elementsByRow = ciMenuBuilder.getMenuElementsByRow();

        setupBukkitMenu();
    }

    private void setupBukkitMenu() {
        this.bInventory = Bukkit.createInventory(null, rows * 9, name);

        int inventoryIndex;
        CIMenuElement menuElement;
        for(int row = 0; row < rows; row++) {
            for(int column = 0; column < 9; column++) {
                inventoryIndex = (9 * (row + 1)) + column;
                menuElement = elementsByRow.get(row).get(column);

                bInventory.setItem(inventoryIndex, menuElement.getFace());
            }
        }
    }

    public static class CIMenuBuilder {
        public static final String DEFAULT_INVENTORY_NAME = "mcGUICI Inventory";
        public static final int DEFAULT_ROWS = 1;

        private String name;
        private int rows;

        private ArrayList<ArrayList<CIMenuElement>> menuElementsByRow;

        public CIMenuBuilder() {
            this.name = DEFAULT_INVENTORY_NAME;
            this.rows = DEFAULT_ROWS;

            this.menuElementsByRow = new ArrayList<>(this.rows);
            for(ArrayList<CIMenuElement> row : menuElementsByRow)
                for(int i = 0; i < 9; i++)
                    row.set(i, new CIMenuElement());
        }

        public CIMenu build() {
            return new CIMenu(this);
        }

        public CIMenuBuilder addMenuElement(int row, int column, CIMenuElement ciMenuElement) {
            // Bootleg Clamp Function
            row = Math.max(0, Math.min(rows, row));
            column = Math.max(0, Math.min(8, row));

            menuElementsByRow.get(row).set(column, ciMenuElement);
            return this;
        }

        public String getName() {
            return name;
        }

        public CIMenuBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public int getRows() {
            return rows;
        }

        public CIMenuBuilder setRows(int rows) {
            this.rows = rows;
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
