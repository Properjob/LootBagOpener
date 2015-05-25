package com.properjob.lootbagopener.init;

import com.properjob.lootbagopener.LootBagOpener;
import com.properjob.lootbagopener.blocks.BlockLootBagOpener;
import com.properjob.lootbagopener.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Danny's on 25/05/2015.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockLootBagOpener blockLootBagOpener = new BlockLootBagOpener();

    public static void init()
    {

    }
}

