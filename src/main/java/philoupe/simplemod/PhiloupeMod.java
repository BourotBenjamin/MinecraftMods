package philoupe.simplemod;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import philoupe.simplemod.blocks.ModBlocks;
import philoupe.simplemod.items.ModItems;
import philoupe.simplemod.lib.Constants;
import philoupe.simplemod.world.PhiloupeWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "simplemod", name = "Philoupe Mod", version = "1.0")
public class PhiloupeMod 
{ 
	
	@Mod.Instance(Constants.MODID)
	public static PhiloupeMod instance;
		
	
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	ModBlocks.init();
    	ModItems.init();
    	GameRegistry.registerWorldGenerator(new PhiloupeWorldGenerator(), 1);
    	GameRegistry.addRecipe(new ItemStack(ModItems.philoupePickaxe), "xxx", " y ", " y ",
    	        'x', new ItemStack(ModItems.philoupeOre), 'y', new ItemStack(Items.stick));
    }
 
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) 
    {
    }
 
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
 
    }

}
