package philoupe.simplemod.items;

import cpw.mods.fml.common.registry.GameRegistry;
import philoupe.simplemod.lib.Constants;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemPickaxe;

public class PhiloupePickaxe extends ItemPickaxe {

	private String name = "PhiloupePickaxe";
	protected PhiloupePickaxe(ToolMaterial p_i45347_1_) {
		super(p_i45347_1_);
		setUnlocalizedName(Constants.MODID + "_" + name);
		GameRegistry.registerItem(this, name);
		setCreativeTab(CreativeTabs.tabMaterials);
        setTextureName(Constants.MODID +":"+name);
	}

}
