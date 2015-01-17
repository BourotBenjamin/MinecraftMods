package philoupe.simplemod.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import philoupe.simplemod.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class PhiloupeDust extends Item {

	
	private String name = "PhiloupeDust";
	public PhiloupeDust() {
		setUnlocalizedName(Constants.MODID + "_" + name);
		GameRegistry.registerItem(this, name);
		setCreativeTab(CreativeTabs.tabMaterials);
        setTextureName(Constants.MODID +":"+name);
	}
}
