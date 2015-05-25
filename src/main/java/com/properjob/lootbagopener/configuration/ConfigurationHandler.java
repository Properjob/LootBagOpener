package com.properjob.lootbagopener.configuration;

import java.io.File;
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
            //Block and items
            /*Settings.General.enabledForceBlock = configuration.getBoolean("Enable Force Block", "General", true,"");
            Settings.General.enabledForceField = configuration.getBoolean("Enable Force Field", "General", true,"");
            // World generation
            Settings.Ores.ForceOre.enabledForceOreGen = configuration.getBoolean("Enable Force Ore Gen", "World Generation", true, null);
            Settings.Ores.ForceOre.forceOreMaxVeinSize = configuration.get("World Generation", "forceOreMaxVeinSize", 8).getInt();
            Settings.Ores.ForceOre.forceOreMinVeinSize = configuration.get("World Generation", "forceOreMinVeinSize", 3).getInt();
            Settings.Ores.ForceOre.forceOreMaxY = configuration.get("World Generation", "forceOreMaxY", 35).getInt();
            Settings.Ores.ForceOre.forceOreMinY = configuration.get("World Generation", "forceOreMinY", 1).getInt();
            Settings.Ores.ForceOre.forceOreRarity = configuration.get("World Generation", "forceOreRarity", 4).getInt();*/
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