package com.properjob.lootbagopener.proxy;

import com.properjob.lootbagopener.tileentities.TileLootBagOpener;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Danny's on 25/05/2015.
 */
public abstract class CommonProxy implements IProxy {

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileLootBagOpener.class, "LootBagOpener");
    }

    public void registerRendering(){}
}
