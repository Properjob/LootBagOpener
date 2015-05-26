package com.properjob.lootbagopener.proxy;

import com.properjob.lootbagopener.reference.RenderIDs;
import com.properjob.lootbagopener.tileentities.TileLootBagOpener;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
/**
 * Created by Danny's on 25/05/2015.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void registerRendering()
    {
        RenderIDs.tileLootBagOpener = RenderingRegistry.getNextAvailableRenderId();
        //ClientRegistry.bindTileEntitySpecialRenderer(TileLootBagOpener.class, new RenderProjector());
       // MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.forceProjector), new ItemRendererforceProjector());
    }
}