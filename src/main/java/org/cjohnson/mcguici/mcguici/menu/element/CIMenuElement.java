package org.cjohnson.mcguici.mcguici.menu.element;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CIMenuElement implements ICIMenuElementInteraction {
    private ItemStack face;
    private ICIMenuElementInteraction interaction;

    public CIMenuElement() {
        this.face = new ItemStack(Material.AIR);
        this.interaction = () -> {};
    }

    public CIMenuElement(ItemStack face, ICIMenuElementInteraction interaction) {
        this.face = face;
        this.interaction = interaction;
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
}
