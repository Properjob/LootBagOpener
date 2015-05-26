package com.properjob.lootbagopener.proxy;

/**
 * Created by Danny's on 25/05/2015.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void registerRendering()
    {
        //RenderIds.Projector = RenderingRegistry.getNextAvailableRenderId();
        //ClientRegistry.bindTileEntitySpecialRenderer(TileProjector.class, new RenderProjector());
       // MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.forceProjector), new ItemRendererforceProjector());
    }
}