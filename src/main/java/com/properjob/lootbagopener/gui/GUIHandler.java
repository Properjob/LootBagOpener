package com.properjob.lootbagopener.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by Danny's on 25/05/2015.
 */
public class GUIHandler implements IGuiHandler {
    public GUIHandler (){

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
                                      int x, int y, int z) {
        // Create an Object of our TE, so we can give that to our inventory.
        //TileProjector tileProjector = (TileProjector) world.getTileEntity(x, y, z);
        //return new InventoryTest(player.inventory, (TileProjector) tileProjector);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
                                      int x, int y, int z) {
        // Create an Object of our TE, so we can give that to our GUI.
        //TileProjector tileProjector = (TileProjector) world.getTileEntity(x, y, z);
        //new GUIInventory(player.inventory, tileProjector);
    }
}
