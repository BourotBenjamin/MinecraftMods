package philoupe.simplemod.handler;

import philoupe.simplemod.blocks.PhiloupeCompressor;
import philoupe.simplemod.blocks.PhiloupeCompressorTileEntity;
import philoupe.simplemod.gui.PhiloupeCompressorGui;
import philoupe.simplemod.inventory.PhiloupeCompressorContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class PhiloupeGuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		if(ID == PhiloupeCompressor.GUI_ID)
		{
			PhiloupeCompressorTileEntity tileEntity = (PhiloupeCompressorTileEntity) world.getTileEntity(x, y, z);
			return new PhiloupeCompressorContainer(player.inventory, tileEntity);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == PhiloupeCompressor.GUI_ID)
		{
			PhiloupeCompressorTileEntity tileEntity = (PhiloupeCompressorTileEntity) world.getTileEntity(x, y, z);
			return new PhiloupeCompressorGui(player.inventory, tileEntity);
		}
		return null;
	}

}
