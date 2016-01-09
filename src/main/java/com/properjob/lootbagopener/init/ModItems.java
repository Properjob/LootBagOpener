package com.properjob.lootbagopener.init;

import com.properjob.lootbagopener.item.ItemExtender;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by Danny's on 08/01/2016.
 */
public class ModItems {

    public static final ItemExtender ITEM_EXTENDER = new ItemExtender();

    public static void init() {

        GameRegistry.registerItem(ITEM_EXTENDER, "ItemExtender");

    }
}
