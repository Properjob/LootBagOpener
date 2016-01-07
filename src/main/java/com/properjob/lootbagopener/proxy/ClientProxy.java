package com.properjob.lootbagopener.proxy;

import com.properjob.lootbagopener.init.ModBlocks;
import com.properjob.lootbagopener.reference.RenderIDs;
import com.properjob.lootbagopener.render.ItemRenderLootBagOpener;
import com.properjob.lootbagopener.render.RenderLootBagOpener;
import com.properjob.lootbagopener.tileentities.TileLootBagOpener;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created by Danny's on 25/05/2015.
 */
public class ClientProxy extends CommonProxy{

    @Override
    public void registerRendering()
    {
        RenderIDs.tileLootBagOpener = RenderingRegistry.getNextAvailableRenderId();
        ClientRegistry.bindTileEntitySpecialRenderer(TileLootBagOpener.class, new RenderLootBagOpener());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.LOOT_BAG_OPENER), new ItemRenderLootBagOpener());
    }
}