package com.properjob.lootbagopener.render;

import com.properjob.lootbagopener.reference.Reference;
import com.properjob.lootbagopener.tileentities.TileLootBagOpener;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

/**
 * Created by Danny's on 03/06/2015.
 */
public class RenderLootBagOpener extends TileEntitySpecialRenderer {

    private static final ResourceLocation texture = new ResourceLocation(Reference.LOWERCASE_MOD_ID, "textures/models/LootBagOpener.png");
    private IModelCustom model;
    private TileLootBagOpener tile;

    public RenderLootBagOpener()
    {
        this.model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.LOWERCASE_MOD_ID, "models/Projector2.obj"));
    }

    @Override
    public void renderTileEntityAt(TileEntity p_147500_1_, double x, double y, double z, float p_147500_8_)
    {
        //'new matrix'
        GL11.glPushMatrix();
        //Translate, set center
        GL11.glTranslated(x, y, z);
        //Scale. Size = 1
        GL11.glScalef(1f, 1f, 1f);
        //Bind our texture
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
        //Rotate 0 degrees
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);
        //Render Model
        model.renderAll();
        //Pop the matrix
        GL11.glPopMatrix();
        //
        // Render Item
        //
        int slot = 5; //Input Slot
        EntityItem ItemEntity = null;
        TileLootBagOpener tile = (TileLootBagOpener) p_147500_1_;
        ItemStack stack = tile.getStackInSlot(slot);
        if (stack != null) {
            //System.out.print(stack.getItem().getUnlocalizedName());
            ItemEntity = new EntityItem(tile.getWorldObj(), 0, 0, 0, stack);
            GL11.glPushMatrix();
            ItemEntity.hoverStart = 0.0F;
            RenderItem.renderInFrame = true;
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.02F, (float) z + 0.5F);
            GL11.glRotatef(180, 180, 1, 1);
            RenderManager.instance.renderEntityWithPosYaw(ItemEntity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
            RenderItem.renderInFrame = false;
            GL11.glPopMatrix();

        }
    }

}
