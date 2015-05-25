package com.properjob.lootbagopener.configuration;

import java.io.File;

import com.properjob.lootbagopener.reference.Settings;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler
{
    public static void init(File configFile)
    {
        Configuration configuration = new Configuration(configFile);

        //Default values for ore generation
        try
        {
            configuration.load();

            //Read and Write Values to config file
            Settings.General.enabledLootBagOpener = configuration.getBoolean("Enable Loot Bag Opener", "General", true,"");
        }
        catch (Exception e)
        {

        }
        finally
        {
            configuration.save();
        }
    }
}