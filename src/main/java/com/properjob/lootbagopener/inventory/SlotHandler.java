package com.properjob.lootbagopener.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

/**
 * Created by Danny's on 25/05/2015.
 */
public class SlotHandler extends Slot {

    public SlotHandler(IInventory inventory, int slotIndex, int x, int y) {

        super(inventory, slotIndex, x, y);
    }
}

