package philoupe.simplemod.items;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

public class ModItems 
{

	public static Item philoupeDust;
	public static Item philoupeOre;
	public static Item philoupePickaxe;
	
	public static ItemStack philoupeDustStack;
	public static ItemStack philoupeOreStack;
	
	public static void init() 
	{
		philoupeOre = new PhiloupeOre();
		philoupeDust = new PhiloupeDust();
		philoupeDustStack = new ItemStack(philoupeDust, 1);
		philoupeOreStack = new ItemStack(philoupeOre, 1);
		philoupePickaxe = new PhiloupePickaxe(ToolMaterial.EMERALD);
	}
}
