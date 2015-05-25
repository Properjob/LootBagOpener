package com.properjob.lootbagopener.init;

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
        //GameRegistry.addRecipe(new ItemStack(ModBlocks.blockLootBagOpener, 1), "iii", "iii", "iii", 'i', new ItemStack(Item.getItemFromBlock(Blocks.), 1));
        System.out.println(GameRegistry.findItem("lootbags", "itemlootbag"));


    }

}

