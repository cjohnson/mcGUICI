package org.cjohnson.mcguici.mcguici.menu.element;

import org.bukkit.inventory.ItemStack;

public class CIMenuElement implements ICIMenuElementInteraction {
    private ItemStack face;
    private ICIMenuElementInteraction interaction;

    private int inventoryX;
    private int inventoryY;

    public CIMenuElement(ItemStack face, ICIMenuElementInteraction interaction) {
        this.face = face;
        this.interaction = interaction;

        this.inventoryX = -1;
        this.inventoryY = -1;
    }

    public CIMenuElement(ItemStack face, ICIMenuElementInteraction interaction, int inventoryX, int inventoryY) {
        this.face = face;
        this.interaction = interaction;

        this.inventoryX = inventoryX;
        this.inventoryY = inventoryY;
    }

    @Override
    public void onInteract() {
        interaction.onInteract();
    }

    public ItemStack getFace() {
        return face;
    }

    public void setFace(ItemStack face) {
        this.face = face;
    }

    public ICIMenuElementInteraction getInteraction() {
        return interaction;
    }

    public void setInteraction(ICIMenuElementInteraction interaction) {
        this.interaction = interaction;
    }

    public int getInventoryX() {
        return inventoryX;
    }

    public void setInventoryX(int inventoryX) {
        this.inventoryX = inventoryX;
    }

    public int getInventoryY() {
        return inventoryY;
    }

    public void setInventoryY(int inventoryY) {
        this.inventoryY = inventoryY;
    }
}
