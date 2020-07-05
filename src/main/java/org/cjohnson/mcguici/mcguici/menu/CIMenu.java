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

    private ArrayList<CIMenuElement> elements;

    private CIMenuAlign align;

    public CIMenu(CIMenuBuilder ciMenuBuilder) {
        this.name = ciMenuBuilder.getName();
        this.rows = ciMenuBuilder.getRows();

        this.elements = ciMenuBuilder.getMenuElements();

        this.align = ciMenuBuilder.getAlign();

        setupBukkitMenu();
    }

    private void setupBukkitMenu() {
        this.bInventory = Bukkit.createInventory(null, rows * 9, name);

        for(CIMenuElement element : elements) {

        }
    }

    public static class CIMenuBuilder {
        public static final String DEFAULT_INVENTORY_NAME = "mcGUICI Inventory";
        public static final int DEFAULT_ROWS = 1;
        public static final CIMenuAlign DEFAULT_ALIGN = CIMenuAlign.LEFT_ALIGN;

        private String name;
        private int rows;

        private ArrayList<CIMenuElement> menuElements;

        private CIMenuAlign align;

        public CIMenuBuilder() {
            this.name = DEFAULT_INVENTORY_NAME;
            this.rows = DEFAULT_ROWS;

            this.menuElements = new ArrayList<>();

            this.align = DEFAULT_ALIGN;
        }

        public CIMenu build() {
            return new CIMenu(this);
        }

        public boolean addMenuElement(CIMenuElement ciMenuElement) {
            return menuElements.add(ciMenuElement);
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

        public ArrayList<CIMenuElement> getMenuElements() {
            return menuElements;
        }

        public void setMenuElements(ArrayList<CIMenuElement> menuElements) {
            this.menuElements = menuElements;
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
