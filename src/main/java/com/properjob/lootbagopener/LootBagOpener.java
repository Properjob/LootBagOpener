package com.properjob.lootbagopener;

import com.properjob.lootbagopener.init.ModBlocks;
import com.properjob.lootbagopener.configuration.ConfigurationHandler;
import com.properjob.lootbagopener.gui.GUIHandler;
import com.properjob.lootbagopener.proxy.CommonProxy;
import com.properjob.lootbagopener.init.Recipes;
import com.properjob.lootbagopener.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

/**
 * Created by Danny's on 25/05/2015.
 */
@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.MOD_VERSION)
public class LootBagOpener {

    @Mod.Instance(Reference.MOD_ID)
    public static LootBagOpener instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.init();
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        proxy.registerTileEntities();
        proxy.registerRendering();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        Recipes.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GUIHandler());
    }


}