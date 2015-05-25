package com.properjob.lootbagopener.proxy;

/**
 * Created by Danny's on 25/05/2015.
 */
public abstract class CommonProxy implements IProxy {

    public void registerTileEntities() {
        //GameRegistry.registerTileEntity(TileProjector.class, "ForceProjector");
    }

    public void registerRendering(){}
}
