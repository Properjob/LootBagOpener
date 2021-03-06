package com.properjob.lootbagopener.gui;

import com.properjob.lootbagopener.inventory.InventoryHandler;
import com.properjob.lootbagopener.reference.Reference;
import com.properjob.lootbagopener.tileentities.TileLootBagOpener;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Danny's on 25/05/2015.
 */
public class GUIInventory extends GuiContainer {

    private TileLootBagOpener tileLootBagOpener;
    private static final ResourceLocation backgroundimage = new ResourceLocation(Reference.LOWERCASE_MOD_ID + ":" + "textures/gui/GUIOpener.png");

    public GUIInventory(InventoryPlayer inventoryPlayer, TileLootBagOpener tileLootBagOpener)
    {
        super(new InventoryHandler(inventoryPlayer, tileLootBagOpener));
        xSize = 176;
        ySize = 214;
        this.tileLootBagOpener = tileLootBagOpener;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
        //TileLootBagOpener tileLootBagOpener = new TileLootBagOpener();
        String s = tileLootBagOpener.getInventoryName();
        this.fontRendererObj.drawString(s, 6, 6, 4210752);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int arrowU = 177;
        int arrowUDelta = 20;
        int arrowV = 0;
        int arrowVDelta = 20;

        /*
        Draw GUI Background
         */

        //Bind Texture
        this.mc.getTextureManager().bindTexture(backgroundimage);
        // set the x for the texture, Total width - textureSize / 2
        par2 = (this.width - xSize) / 2;
        // set the y for the texture, Total height - textureSize - 30 (up) / 2,
        int j = (this.height - ySize) / 2;
        // draw the texture
        drawTexturedModalRect(par2, j, 0, 0, xSize,  ySize);

        par2 = (this.width - arrowUDelta + 3) /2; //x
        j = (this.height - arrowVDelta) /2 - 54; //y
        drawTexturedModalRect(par2, j, arrowU, arrowVDelta, arrowUDelta, tileLootBagOpener.getProgress() + 1);
    }

}
