package com.properjob.lootbagopener.init;

import com.properjob.lootbagopener.blocks.BlockLootBagOpener;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by Danny's on 25/05/2015.
 */
public class Recipes {

    public static void init()
    {
        initModRecipes();
    }

    private static void initModRecipes()

    {
        // Shaped
        GameRegistry.addRecipe(new ItemStack(ModBlocks.LOOT_BAG_OPENER, 1), "ihi", "p p", "ihi", 'i', new ItemStack(Item.getItemFromBlock(Blocks.stone), 1), 'h', new ItemStack(Item.getItemFromBlock(Blocks.hopper)), 'p', new ItemStack(Item.getItemFromBlock(Blocks.sticky_piston)));
    }

}

