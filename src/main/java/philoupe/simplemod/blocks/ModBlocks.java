package philoupe.simplemod.blocks;

import net.minecraft.block.Block;

public final class ModBlocks 
{
    public static Block philoupeBlock;
    public static Block compressor;
    
    public static void init()
    {
    	philoupeBlock = new PhiloupeBlock();
    }
}