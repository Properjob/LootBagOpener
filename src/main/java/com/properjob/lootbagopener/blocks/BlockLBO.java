package com.properjob.lootbagopener.blocks;

import com.properjob.lootbagopener.creativetab.CreativeTab;
import com.properjob.lootbagopener.reference.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Created by Danny's on 25/05/2015.
 */
public class BlockLBO extends Block {
        public BlockLBO(Material material)
        {
            super(material);
            this.setCreativeTab(CreativeTab.LBO_TAB);
        }

        public BlockLBO()
        {
            this(Material.rock);
        }

        @Override
        public String getUnlocalizedName()
        {
            return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
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

}
