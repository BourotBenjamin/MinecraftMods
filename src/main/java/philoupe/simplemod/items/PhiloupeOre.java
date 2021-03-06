package philoupe.simplemod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import philoupe.simplemod.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class PhiloupeOre extends Item {
	
	private String name = "PhiloupeOre";
	public PhiloupeOre() {
		setUnlocalizedName(Constants.MODID + "_" + name);
		GameRegistry.registerItem(this, name);
		setCreativeTab(CreativeTabs.tabMaterials);
        setTextureName(Constants.MODID +":"+name);
	}

}
