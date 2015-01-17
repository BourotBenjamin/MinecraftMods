package philoupe.simplemod.items;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;

public class ModItems 
{

	public static Item philoupeDust;
	public static Item philoupeOre;
	public static Item philoupePickaxe;
	
	public static void init() 
	{
		philoupeOre = new PhiloupeOre();
		philoupeOre = new PhiloupeDust();
		philoupePickaxe = new PhiloupePickaxe(ToolMaterial.EMERALD);
	}
}
