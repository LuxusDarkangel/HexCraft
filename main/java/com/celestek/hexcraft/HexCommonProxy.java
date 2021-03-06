package com.celestek.hexcraft;

import com.celestek.hexcraft.client.HexClientProxy;
import com.celestek.hexcraft.init.*;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Thorinair   <celestek@openmailbox.org>
 * @version 0.5.1
 * @since 2015-04-14
 */

public class HexCommonProxy {

    /**
     * Handles preInit.
     */
    public void preInit(FMLPreInitializationEvent e)
    {
        // Set up all ingame blocks, items and recipes.
        HexBlocks.initializeBlocks();
        HexItems.initializeItems();
        HexRecipes.initializeRecipes();
        HexTileEntities.initializeTileEntities();
    }

    /**
     * Handles init.
     */
    public void init(FMLInitializationEvent e)
    {
        GameRegistry.registerWorldGenerator(new HexWorldGen(), 0);
        NetworkRegistry.INSTANCE.registerGuiHandler(HexCraft.instance, new HexGui());
    }

    /**
     * Handles postInit.
     */
    public void postInit(FMLPostInitializationEvent e)
    {

    }
}
