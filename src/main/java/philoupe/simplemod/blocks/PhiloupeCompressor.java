package philoupe.simplemod.blocks;

import java.util.Random;

import philoupe.simplemod.PhiloupeMod;
import philoupe.simplemod.lib.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PhiloupeCompressor extends BlockContainer 
{

	private String name = "PhiloupeCompressor";
	public String getName()
	{
		return name;
	}

	private final boolean isActive;
	private static boolean changingState;
	
	private static Random random;
	
	public static final int GUI_ID = 0;
	
	protected PhiloupeCompressor(boolean active) 
	{
		super(Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setBlockName(Constants.MODID +"_"+name);
        this.setBlockTextureName(Constants.MODID +":"+name);
        this.setHardness(2.0f);
        this.setHarvestLevel("pickaxe", 2);
        isActive = active;
        random = new Random();
	}

	public boolean isCompressing()
	{
		return isActive;
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		player.openGui(PhiloupeMod.instance, GUI_ID, world, x, y, z);
		return true;
	}

	public Item getItemDropped(int par1, Random random, int par3)
	{
		return Item.getItemFromBlock(ModBlocks.philoupeCompressor);
	}
	public Item getItem(World world, int x, int y, int z)
	{
		return Item.getItemFromBlock(ModBlocks.philoupeCompressor);
	}
	
	public static void updateBlockState(boolean active, World world, int x, int y, int z)
	{
		changingState = true;/*
		if(active)
		{
			world.setBlock(x,y,z, ModBlocks.philoupeCompressorActive);
		}
		else
		{
			world.setBlock(x,y,z, ModBlocks.philoupeCompressor);
		}*/
		changingState = false;
	}
	
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		if(!changingState) {
			PhiloupeCompressorTileEntity tileEntity = (PhiloupeCompressorTileEntity) world.getTileEntity(x, y, z);
			if(tileEntity != null)
			{
				for(int i=0; i<tileEntity.getSizeInventory(); i++)
				{
					ItemStack itemStack = tileEntity.getStackInSlot(i);
					if(itemStack != null)
					{
						double xDistance = random.nextDouble() * 0.6 + 0.1;
						double yDistance = random.nextDouble() * 0.6 + 0.1;
						double zDistance = random.nextDouble() * 0.6 + 0.1;
						EntityItem entityItem = new EntityItem(world, x+xDistance, y+yDistance, z+zDistance, itemStack); 
						entityItem.motionX = random.nextGaussian() +0.025;
						entityItem.motionY = random.nextGaussian() +0.035;
						entityItem.motionZ = random.nextGaussian() +0.025;
						world.spawnEntityInWorld(entityItem);
					}
				}
				// Update neighbor blocks
				world.func_147453_f(x, y, z, block);
			}
		}
		super.breakBlock(world, x, y, z, block, meta);
		EntityItem entityItem = new EntityItem(world, x, y, y, new ItemStack(block, 1));
		entityItem.motionX = random.nextGaussian() +0.025;
		entityItem.motionY = random.nextGaussian() +0.035;
		entityItem.motionZ = random.nextGaussian() +0.025;
		world.spawnEntityInWorld(entityItem);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new PhiloupeCompressorTileEntity();
	}
	
	
}
