package com.properjob.lootbagopener.blocks;

import com.properjob.lootbagopener.LootBagOpener;
import com.properjob.lootbagopener.reference.Reference;
import com.properjob.lootbagopener.reference.RenderIDs;
import com.properjob.lootbagopener.tileentities.TileLootBagOpener;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Danny's on 25/05/2015.
 */
public class BlockLootBagOpener extends BlockContainer {

    public BlockLootBagOpener(){
        super(Material.iron);
        this.setBlockName("LootBagOpener");
        this.setCreativeTab(CreativeTabs.tabDecorations);
        GameRegistry.registerBlock(this, "LootBagOpener");
        this.setHardness(8.0F);
    }

    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
        return true;
    }

    @Override
    public int getRenderType()
    {
        return -1;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean hasTileEntity(int meta)
    {
        return true;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int par1, float par2, float par3, float par4)
    {
        entityPlayer.openGui(LootBagOpener.instance, 0, world, x ,y, z);
        return true;
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Reference.LOWERCASE_MOD_ID + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileLootBagOpener();
    }
}

