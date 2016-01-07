package com.properjob.lootbagopener.render;

import com.properjob.lootbagopener.reference.Reference;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

/**
 * Created by Danny's on 03/06/2015.
 */
@SideOnly(Side.CLIENT)
public class ItemRenderLootBagOpener implements IItemRenderer {

    private final IModelCustom model;
    private static final ResourceLocation texture = new ResourceLocation(Reference.LOWERCASE_MOD_ID, "textures/models/LootBagOpener.png");

    public ItemRenderLootBagOpener()
    {
        this.model = AdvancedModelLoader.loadModel(new ResourceLocation(Reference.LOWERCASE_MOD_ID, "models/Projector2.obj"));
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
                                         ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... data)
    {
        renderLootBagOpener(0.0F ,0.0F ,0.0F);
    }

    private void renderLootBagOpener(float x, float y, float z)
    {
        GL11.glPushMatrix();

        // Scale, Translate, Rotate
        GL11.glScalef(1F, 1F, 1F);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 0.0F);

        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);

        // Render
        model.renderAll();
        GL11.glPopMatrix();
    }
}