package com.properjob.lootbagopener.item;

import com.properjob.lootbagopener.LootBagOpener;
import com.properjob.lootbagopener.reference.Reference;
import com.properjob.lootbagopener.reference.RenderIDs;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ibxm.Player;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import org.lwjgl.Sys;

/**
 * Created by Danny's on 08/01/2016.
 */
public class ItemExtender extends Item {

    public ItemExtender()
    {
        super();
        this.setUnlocalizedName("ItemExtender");
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Reference.LOWERCASE_MOD_ID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Reference.LOWERCASE_MOD_ID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        System.out.println("Looking for: " + this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {

        player.openGui(LootBagOpener.instance, RenderIDs.tileLootBagOpener, world, (int) player.posX, (int)player.posY, (int)player.posZ);
        return stack;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        Block block;
        Item item;
        String dir;
        //
        block = world.getBlock(x, y, z);
        item = Item.getItemFromBlock(block);
        //
        if(!world.isRemote) {
            System.out.print("Called");
            System.out.print(item.getUnlocalizedName());
            if (player.inventory.hasItem(item)) {
                System.out.print("Has Item");
                setBlockAtCoord(world, x, y, z, p_77648_7_, block);
                System.out.print("I Tried");
                if (!player.capabilities.isCreativeMode) {
                    int slot = getNextStackSlot(player, item);
                    if (slot != -1) {
                        player.inventory.decrStackSize(slot, 1);

                    }
                    return true;
                }
                return true;

            }
        }
        return true;
    }

    public int getNextStackSlot(EntityPlayer player, Item item){

        for (int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            if (player.inventory.getStackInSlot(i).getItem() == item){
                return i;
            }
        }

        return -1;
    }

    public boolean setBlockAtCoord(World world, int blockX, int blockY, int blockZ, int face, Block block) {

        int setBlock = 0;
        int end = 0;
        int X = blockX;
        int Y = blockY;
        int Z = blockZ;
        Block testBlock;
        int inte = 0;
        //
        System.out.print(face);
        for (int i = 0; i < 64; ++i) {

            switch (face) {
                case 0:
                    // UP
                    testBlock = world.getBlock(blockX, blockY + i, blockZ);
                    if (testBlock.isAir(world, blockX, blockY + i, blockZ) && (!testBlock.canPlaceBlockAt(world, blockX, blockY + i, blockZ))){
                        Y = Y + i;
                        setBlock = 1;
                    }
                    else if (testBlock != block) end = 1;
                    break;
                case 1:
                    // DOWN
                    testBlock = world.getBlock(blockX, blockY - i, blockZ);
                    if (testBlock.isAir(world, blockX, blockY - i, blockZ) && (!testBlock.canPlaceBlockAt(world, blockX, blockY - i, blockZ))){
                        Y = Y - i;
                        setBlock = 1;
                    }
                    else if (testBlock != block) end = 1;
                    break;
                case 2:
                    //SOUTH
                    testBlock = world.getBlock(blockX, blockY, blockZ + i);
                    if (testBlock.isAir(world, blockX, blockY, blockZ + i) && (!testBlock.canPlaceBlockAt(world, blockX, blockY, blockZ + i))){
                        Z = Z + i;
                        setBlock = 1;
                    }
                    else if (testBlock != block) end = 1;
                    break;
                case 3:
                    //NORTH
                    testBlock = world.getBlock(blockX, blockY, blockZ - i);
                    if (testBlock.isAir(world, blockX, blockY, blockZ - i) && (!testBlock.canPlaceBlockAt(world, blockX, blockY, blockZ - i))){
                        Z = Z - i;
                        setBlock = 1;
                    }
                    else if (testBlock != block) end = 1;
                    break;
                case 4:
                    //EAST
                    testBlock = world.getBlock(blockX + i, blockY, blockZ);
                    if (testBlock.isAir(world, blockX + i, blockY, blockZ) && (!testBlock.canPlaceBlockAt(world, blockX + i, blockY, blockZ))){
                        X = X + i;
                        setBlock = 1;
                    }
                    else if (testBlock != block) end = 1;
                    break;
                case 5:
                    //WEST
                    testBlock = world.getBlock(blockX - i, blockY, blockZ);
                    if (testBlock.isAir(world, blockX - i, blockY, blockZ) && (!testBlock.canPlaceBlockAt(world, blockX - i, blockY, blockZ))){
                        X = X - i;
                        setBlock = 1;
                    }
                    else if (testBlock != block) end = 1;
                    break;
                default:
                    break;
            }
            inte = i;
            if (end == 1) break;
            if (setBlock == 1) break;
        }
        if (setBlock == 1) {
            System.out.print(inte);
            world.setBlock(X, Y, Z, block);
            return true;
        }
        return false;
    }

}