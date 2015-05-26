package com.properjob.lootbagopener.tileentities;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.FakePlayerFactory;

import java.util.UUID;

/**
 * Created by Danny's on 25/05/2015.
 */

public class TileLootBagOpener extends TileEntity implements IInventory {

    private ItemStack[] inventory;
    private int INVENTORY_SIZE = 28;
    private static final String FAKE_PLAYER_NAME = "[LBO_PLAYER]";
    private static final UUID FAKE_PLAYER_ID = null;
    public int OpenTime = 2;
    public int OpenTimeRemaining;
    private boolean isOpening = false;

    public TileLootBagOpener()
    {
        inventory = new ItemStack[INVENTORY_SIZE];
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.inventory.length; ++i)
        {
            if (this.inventory[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                this.inventory[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }
        nbtTagCompound.setTag("Items", nbttaglist);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        NBTTagList nbttaglist = nbtTagCompound.getTagList("Items", Constants.NBT.TAG_COMPOUND); //ID for compounds
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getByte("Slot");

            if (j >= 0 && j < this.inventory.length)
            {
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
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            }
            else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }
        return itemStack;
    }


    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }


    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        inventory[slotIndex] = itemStack;
        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
        {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName()
    {
        return "Loot Bag Opener";
    }


    @Override
    public boolean hasCustomInventoryName()
    {
        return true;
    }


    @Override
    public int getInventoryStackLimit()
    {
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
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack IS) {

        return false;
    }

    public void openBag(ItemStack itemStack){

        EntityPlayerMP player = FakePlayerFactory.get((WorldServer) worldObj, new GameProfile(FAKE_PLAYER_ID, FAKE_PLAYER_NAME));
        player.inventory.clearInventory(null, -1);
        player.inventory.currentItem = 0;
        player.inventory.setInventorySlotContents(0, itemStack);
        ItemStack bag = itemStack.useItemRightClick(worldObj, player);
        player.inventory.setInventorySlotContents(0, null);
        setInventorySlotContents(0, bag);
        decrStackSize(27, 1);
    }
    public boolean canOpen(){


        return true;
    }

    @Override
    public void updateEntity()
    {
        //If on server side
        if(!worldObj.isRemote)
        {
            System.out.println("Not Remote");
            //If the machine is already smashing
            if(isOpening)
            {
                System.out.println("Open");
                //And the te is done smashing
                if(OpenTimeRemaining == 0)
                {
                    System.out.println("Open time");
                    //Null pointer blocker
                    if(inventory[27] != null)
                    {
                        System.out.println("Opening");
                        openBag(inventory[27]);
                    }
                    //done smashing, so set isSmashing to false
                    isOpening = false;
                    OpenTime = 2;
                }
                //reduce smash time
                OpenTimeRemaining--;
            }
            //If not smashing, check if can smash
            else if(canOpen()) {
                //turn smashing on
                isOpening = true;
                OpenTimeRemaining = 2;
                OpenTime = 2;
            }
        }
    }

    public int getSmashTimeRemaining()
    {
        return OpenTimeRemaining;
    }

    public int getSmashTime()
    {
        return OpenTime;
    }
}
