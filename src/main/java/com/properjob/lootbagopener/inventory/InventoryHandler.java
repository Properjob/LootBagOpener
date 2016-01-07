package com.properjob.lootbagopener.inventory;

import com.properjob.lootbagopener.tileentities.TileLootBagOpener;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by Danny's on 25/05/2015.
 */
public class InventoryHandler extends Container {
    //Create an Object of our TE.
    private TileLootBagOpener tile;
    private int OpenTimeRemaining;

    public InventoryHandler(InventoryPlayer inventory, TileLootBagOpener tileLootBagOpener) {
        this.tile = tileLootBagOpener;
        bindPlayerInventory(inventory);
    }

    /*
    Add slots to our GUI.
    The id's are for the slotnumbers.
    For the rest, the i * 18 and j * 18 is always the same.
    The other numbers can change, depending on your gui.
     */
    private void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        int id = 0;
        int id2 = 0;

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, id, i * 18 + 8, 190)); //Adds player hotbar
            id++;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, id, j * 18 + 8, i * 18 + 132)); //Adds player inventory
                id++;
            }
        }
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                addSlotToContainer(new SlotHandler(tile, id2, j * 18 + 44, i * 18 + 73)); //Adds custom inventory
                id2++;
            }
        }
        addSlotToContainer(new SlotHandler(tile, id2, 80, 18)); //Adds custom input


    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i)
    {
        Slot slot = getSlot(i);

        if (slot != null && slot.getHasStack())
        {
            ItemStack stack = slot.getStack();
            ItemStack result = stack.copy();

            if (i >= 5){
                if (!mergeItemStack(stack, 5, 36, false)){
                    return null;
                }
            }else if (!isStackValidForInventory(stack, 5) || !mergeItemStack(stack, 36, 36 + tile.getSizeInventory(), false)){
                return null;
            }

            if (stack.stackSize == 0) {
                slot.putStack(null);
            }else{
                slot.onSlotChanged();
            }

            slot.onPickupFromSlot(player, stack);

            return result;
        }

        return null;
    }

    private boolean isStackValidForInventory(ItemStack stack, int slot){
        System.out.println(stack.getUnlocalizedName());
        if(stack.getUnlocalizedName().contains("item.stick") && slot == 5) {
            return true;
        }
        return false;
    }

    @Override
    public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, 1, this.tile.getOpenTimeRemaining());
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);
            if (this.OpenTimeRemaining != this.tile.getOpenTimeRemaining()) {
                icrafting.sendProgressBarUpdate(this, 1, this.tile.getOpenTimeRemaining());
            }
        }
        this.OpenTimeRemaining = this.tile.openTimeRemaining;

    }
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int par1, int par2)
    {
            tile.openTimeRemaining = par2;
    }
}