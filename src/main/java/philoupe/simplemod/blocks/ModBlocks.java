package philoupe.simplemod.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public final class ModBlocks 
{
    public static Block philoupeBlock;
    public static Block philoupeCompressor;
    public static Block philoupeCompressorActive;
    
    public static void init()
    {
    	philoupeBlock = new PhiloupeBlock();
    	philoupeCompressor = new PhiloupeCompressor(false);
    	philoupeCompressorActive = new PhiloupeCompressor(true);
        GameRegistry.registerBlock(philoupeCompressor, ((PhiloupeCompressor) philoupeCompressor).getName());
        GameRegistry.registerTileEntity(PhiloupeCompressorTileEntity.class, "PhiloupeCompressorTileEntity");
    }
}