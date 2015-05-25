package com.properjob.lootbagopener.creativetab;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import com.properjob.lootbagopener.reference.Reference;
import net.minecraft.item.Item;

public class CreativeTab {

    public static final CreativeTabs LBO_TAB = new CreativeTabs(Reference.LOWERCASE_MOD_ID)
    {
        @Override
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(Block.getBlockFromName("LootBagOpener"));
        }

        @Override
        public String getTranslatedTabLabel()
        {
            return Reference.MOD_NAME;

        }
    };

}
