package com.properjob.lootbagopener.gui;

import com.properjob.lootbagopener.inventory.InventoryHandler;
import com.properjob.lootbagopener.tileentities.TileLootBagOpener;
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
        TileLootBagOpener tileLootBagOpener = (TileLootBagOpener) world.getTileEntity(x, y, z);
        return new InventoryHandler(player.inventory, (TileLootBagOpener) tileLootBagOpener);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
                                      int x, int y, int z) {
        // Create an Object of our TE, so we can give that to our GUI.
        TileLootBagOpener tileLootBagOpener = (TileLootBagOpener) world.getTileEntity(x, y, z);
        return new GUIInventory(player.inventory, tileLootBagOpener);
    }
}


