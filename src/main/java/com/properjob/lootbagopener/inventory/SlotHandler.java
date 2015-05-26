package com.properjob.lootbagopener.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Danny's on 25/05/2015.
 */
public class SlotHandler extends Slot {

    public SlotHandler(IInventory inventory, int slotIndex, int x, int y) {

        super(inventory, slotIndex, x, y);
    }

    @Override
    public boolean isItemValid(ItemStack par1ItemStack)
    {
        System.out.println(par1ItemStack.getUnlocalizedName());
        return par1ItemStack.getUnlocalizedName() == "lootbags:itemlootbag";
    }
}

