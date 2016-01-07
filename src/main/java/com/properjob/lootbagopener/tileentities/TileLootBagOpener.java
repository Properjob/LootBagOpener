package com.properjob.lootbagopener.tileentities;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.FakePlayerFactory;
import java.util.UUID;

/**
 * Created by Danny's on 25/05/2015.
 */

public class TileLootBagOpener extends TileEntity implements ISidedInventory {

    private ItemStack[] inventory;
    private int INVENTORY_SIZE = 6;
    private int INPUT_SLOT = 5;
    private static final String FAKE_PLAYER_NAME = "[LBO_PLAYER]";
    private static final UUID FAKE_PLAYER_ID = null;
    private boolean IS_OPENING = false;
    public int openTime = 20;
    public int openTimeRemaining;
    EntityPlayerMP FakePlayer;

    public TileLootBagOpener() {
        inventory = new ItemStack[INVENTORY_SIZE];
        this.openTimeRemaining = 20;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }
        nbtTagCompound.setTag("Items", nbttaglist);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        NBTTagList nbttaglist = nbtTagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND); //ID for compounds
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot");

            if (j >= 0 && j < this.inventory.length) {
                this.inventory[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }


    @Override
    public ItemStack getStackInSlot(int slotIndex) {
        return inventory[slotIndex];
    }


    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) {
            if (itemStack.stackSize <= decrementAmount) {
                setInventorySlotContents(slotIndex, null);
            } else {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0) {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }
        return itemStack;
    }


    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex) {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null) {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }


    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack) {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "Loot Bag Opener";
    }


    @Override
    public boolean hasCustomInventoryName() {
        return true;
    }


    @Override
    public int getInventoryStackLimit() {
        return 64;
    }


    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        // TODO Auto-generated method stub
        return true;
    }


    @Override
    public void openInventory() {
    }


    @Override
    public void closeInventory() {
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        if (slot == 5 && itemStack.getUnlocalizedName().contains("item.lootbag")){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean canStack() {
        for (int i = 0; i < 5; ++i) {
            if (inventory[i] !=null){
                return false;
            }
        }
        return true;
    }

    public boolean getItems(ItemStack itemStack) {
        if (itemStack.stackTagCompound != null) {
            if (itemStack.stackTagCompound.getBoolean("generated")) {
                ItemStack[] Slots = new ItemStack[5];
                NBTTagList Items = itemStack.stackTagCompound.getTagList("inventory", 10);

                if (canStack()) {
                    for (int i = 0; i < Items.tagCount(); ++i) {
                        NBTTagCompound item = (NBTTagCompound) Items.getCompoundTagAt(i);
                        int slot = item.getInteger("Slot");

                        if (slot >= 0 && slot < Slots.length) {
                            ItemStack Item = ItemStack.loadItemStackFromNBT(item);
                            inventory[i] = Item;
                        }
                    }
                    return true;
                } else return false;
            } else return false;
        }
        else return false;
    }
    public boolean openBag(ItemStack itemStack){
        if (FakePlayer == null) {
            FakePlayer = FakePlayerFactory.get((WorldServer) worldObj, new GameProfile(FAKE_PLAYER_ID, FAKE_PLAYER_NAME));
        }
            FakePlayer.inventory.clearInventory(null, -1);
            FakePlayer.inventory.changeCurrentItem(0);
            FakePlayer.inventory.setInventorySlotContents(0, itemStack);
            ItemStack ResultIS = itemStack.useItemRightClick(worldObj, FakePlayer);
            FakePlayer.inventory.clearInventory(null, -1);
            return getItems(ResultIS);
    }
    public boolean canOpen(){
        return true;
    }

    @Override
    public void updateEntity() {
        if(!worldObj.isRemote){

            if(IS_OPENING){

                if((inventory[INPUT_SLOT] != null) && (isItemValidForSlot(INPUT_SLOT, inventory[INPUT_SLOT]))){

                    if (openTimeRemaining == 0){

                        if (openBag(inventory[INPUT_SLOT])){
                            decrStackSize(INPUT_SLOT ,1);
                            openTimeRemaining = 20;
                        }
                        this.markDirty();
                    }
                    else{
                        --openTimeRemaining;
                    }
                }
                else{
                    IS_OPENING = false;
                }
            }
            else if(inventory[INPUT_SLOT] != null && canStack()){
                IS_OPENING = true;
            }
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        if (side == 1) {
            return new int[]{5};
        }
        else{
            return new int[]{0,1,2,3,4};
        }
    }

    @Override
    public boolean canInsertItem(int Slot, ItemStack Item, int Side) {
        return this.isItemValidForSlot(Slot, Item);
    }

    @Override
    public boolean canExtractItem(int Slot, ItemStack Item, int Side) {
        if (Slot == 5){
            return false;
        }
        else{
            return true;
        }
    }
    public int getOpenTime()
    {
        return openTime;
    }

    public int getOpenTimeRemaining()
    {
        return openTimeRemaining;
    }
    @SideOnly(Side.CLIENT)
    public int getProgress()
    {
        return (openTime - openTimeRemaining);    //(total amount of time to open - remaining before open)
    }
}
