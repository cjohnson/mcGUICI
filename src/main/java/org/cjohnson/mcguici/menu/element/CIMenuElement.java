package org.cjohnson.mcguici.menu.element;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CIMenuElement implements ICIMenuElementInteraction {
    private ItemStack face;
    private ICIMenuElementInteraction interaction;

    public CIMenuElement() {
        this.face = new ItemStack(Material.AIR);
        this.interaction = (player) -> {};
    }

    public CIMenuElement(ItemStack face) {
        this.face = face;
        this.interaction = (player) -> {};
    }

    public CIMenuElement(ItemStack face, ICIMenuElementInteraction interaction) {
        this.face = face;
        this.interaction = interaction;
    }

    @Override
    public void onInteract(Player player) {
        interaction.onInteract(player);
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
