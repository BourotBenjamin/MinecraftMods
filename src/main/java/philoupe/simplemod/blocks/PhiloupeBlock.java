package philoupe.simplemod.blocks;

import philoupe.simplemod.items.ModItems;
import philoupe.simplemod.lib.Constants;
import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class PhiloupeBlock extends Block 
{

	private IIcon[] icons = new IIcon[6];
	private String name = "PhiloupeBlock";
	
	protected PhiloupeBlock() 
	{
		super(Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockName(Constants.MODID +"_"+name);
        GameRegistry.registerBlock(this, name);
        this.setBlockTextureName(Constants.MODID +":"+name);
        this.setHardness(2.0f);
        this.setHarvestLevel("pickaxe", 2);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
	    for (int i = 0; i < icons.length; i++) {
	        icons[i] = iconRegister.registerIcon(Constants.MODID + ":" + name);
	    }
	}

	@Override
	public IIcon getIcon(int side, int meta) {
	    return icons[side];
	}
	
    //If the block's drop is an item.
    @Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        return ModItems.philoupeDust;
    }
}
