package org.cjohnson.mcguici.mcguici.menu;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.cjohnson.mcguici.mcguici.menu.configuration.CIMenuAlign;
import org.cjohnson.mcguici.mcguici.menu.element.CIMenuElement;

import java.util.ArrayList;

public class CIMenu {
    private Inventory bInventory;

    private String name;
    private int rows;

    private ArrayList<ArrayList<CIMenuElement>> elementsByRow;

    private CIMenuAlign align;

    public CIMenu(CIMenuBuilder ciMenuBuilder) {
        this.name = ciMenuBuilder.getName();
        this.rows = ciMenuBuilder.getRows();

        this.elementsByRow = ciMenuBuilder.getMenuElementsByRow();

        this.align = ciMenuBuilder.getAlign();

        setupBukkitMenu();
    }

    private void setupBukkitMenu() {
        this.bInventory = Bukkit.createInventory(null, rows * 9, name);

        placeMenuElements();
    }

    private void placeMenuElements() {
        switch(align) {
            case LEFT_ALIGN:
                placeLeftAlignMenuElements();
                break;
            case CENTER_ALIGN:
                break;
            case RIGHT_ALIGN:
                break;
        }
    }

    private void placeLeftAlignMenuElements() {
        int currentSizeRowElements;
        ArrayList<CIMenuElement> currentRowElements;

        for(int i = 0; i < rows; i++) {
            currentRowElements = elementsByRow.get(i);
            currentSizeRowElements = currentRowElements.size();

            for(int j = 0; j < currentSizeRowElements; j++) {
                bInventory.setItem(j, currentRowElements.get(j).getFace());
            }
        }
    }

    private void placeCenterAlignMenuElements() {
        int currentSizeRowElements;
        ArrayList<CIMenuElement> currentRowElements;

        int rowCenterBegin;
        int rowDisplacement;

        for(int i = 0; i < rows; i++) {
            currentRowElements = elementsByRow.get(i);
            currentSizeRowElements = currentRowElements.size();

            rowCenterBegin = (9 - currentSizeRowElements) / 2;

            for(int j = rowCenterBegin; j < currentSizeRowElements + rowCenterBegin; j++) {
                bInventory.setItem(j, currentRowElements.get(j).getFace());
            }
        }
    }

    private void placeRightAlignMenuElements() {
        int currentSizeRowElements;
        ArrayList<CIMenuElement> currentRowElements;

        for(int i = 0; i < rows; i++) {
            currentRowElements = elementsByRow.get(i);
            currentSizeRowElements = currentRowElements.size();

            for(int j = 8; j > 9 - currentSizeRowElements; j--) {
                bInventory.setItem(j, currentRowElements.get(8 - j).getFace());
            }
        }
    }

    public static class CIMenuBuilder {
        public static final String DEFAULT_INVENTORY_NAME = "mcGUICI Inventory";
        public static final int DEFAULT_ROWS = 1;
        public static final CIMenuAlign DEFAULT_ALIGN = CIMenuAlign.LEFT_ALIGN;

        private String name;
        private int rows;

        private ArrayList<ArrayList<CIMenuElement>> menuElementsByRow;

        private CIMenuAlign align;

        public CIMenuBuilder() {
            this.name = DEFAULT_INVENTORY_NAME;
            this.rows = DEFAULT_ROWS;

            this.menuElementsByRow = new ArrayList<>(this.rows);

            this.align = DEFAULT_ALIGN;
        }

        public CIMenu build() {
            return new CIMenu(this);
        }

        public CIMenuBuilder addMenuElement(int row, CIMenuElement ciMenuElement) {
            menuElementsByRow.get(row).add(ciMenuElement);
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

        public CIMenuAlign getAlign() {
            return align;
        }

        public CIMenuBuilder setAlign(CIMenuAlign align) {
            this.align = align;
            return this;
        }
    }
}
