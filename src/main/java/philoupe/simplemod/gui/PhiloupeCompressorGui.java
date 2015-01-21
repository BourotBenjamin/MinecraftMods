package philoupe.simplemod.gui;

import philoupe.simplemod.blocks.PhiloupeCompressorTileEntity;
import philoupe.simplemod.inventory.PhiloupeCompressorContainer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PhiloupeCompressorGui extends GuiContainer {

	private static final ResourceLocation compressorGuiTexture = new ResourceLocation("simplemod:textures/gui/container/compressor_gui.png");
	private PhiloupeCompressorTileEntity tileEntity;
	
	
	public PhiloupeCompressorGui(InventoryPlayer player, PhiloupeCompressorTileEntity tileEntity) {
		super(new PhiloupeCompressorContainer(player, tileEntity));
		this.tileEntity = tileEntity;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float a, int b, int c) {
		this.mc.getTextureManager().bindTexture(compressorGuiTexture);
		int xPosition = (width - xSize) /2;
		int yPosition = (height - ySize) /2;
		this.drawTexturedModalRect(xPosition, yPosition, 0, 0, xSize, ySize);
		if(tileEntity.currentItemCompressTime >0)
		{
			float compressionScale = tileEntity.currentItemCompressTime / (tileEntity.compressTimePerBlocks * 1.0f);
			this.drawTexturedModalRect(xPosition + 56, yPosition + 36, 176, 0, 14, (int) (compressionScale * 12));
		}
	}

}
