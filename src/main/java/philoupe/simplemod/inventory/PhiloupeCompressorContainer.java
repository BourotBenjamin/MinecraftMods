package philoupe.simplemod.inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import philoupe.simplemod.blocks.PhiloupeCompressorTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class PhiloupeCompressorContainer extends Container {

	private PhiloupeCompressorTileEntity tileEntity;
	private int currentItemCompressTime;
	
	public PhiloupeCompressorContainer(InventoryPlayer inventory, PhiloupeCompressorTileEntity tileEntity)
	{
		this.tileEntity = tileEntity;
		this.addSlotToContainer(new Slot(tileEntity, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileEntity, 1, 56, 53));
		for (int i = 1; i < 4; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(inventory, i*9+j, j*18+8, i*18+66));
			}
		}
		for (int j = 0; j < 9; ++j) {
			this.addSlotToContainer(new Slot(inventory, j, j*18+8, 142));
		}
	}
	
	public void addCraftingToCrafters(ICrafting crafting)
	{
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.tileEntity.currentItemCompressTime);
		currentItemCompressTime = tileEntity.currentItemCompressTime;
	}

	@SideOnly(Side.SERVER)
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		if(currentItemCompressTime != this.tileEntity.currentItemCompressTime)
		{
			for (Object crafting : crafters) {
				((ICrafting) crafting).sendProgressBarUpdate(this, 0, this.tileEntity.currentItemCompressTime);
				currentItemCompressTime = tileEntity.currentItemCompressTime;
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int type, int value)
	{
		if(type == 0)
		{
			this.tileEntity.currentItemCompressTime = value;
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return this.tileEntity.isUseableByPlayer(player);
	}

	// Slots : 0-1 : Compressor / 2+ : Player 
	public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
	{
		ItemStack originalStack = null;
		Slot slot = (Slot) this.inventorySlots.get(slotIndex);
		if(slot!=null)
		{
			originalStack = slot.getStack();
			if(originalStack != null)
			{
				ItemStack result = originalStack.copy();
				// mergeItemStack
				// stack : Shift Clicked Stack  
				// 2 : Index of the first stack  
				// 38 : Index of the last stack   
				// true : Merge in multiple stacks
				System.out.println(result.getItem().toString());
				if(slotIndex < 2)
				{
					if(!this.mergeItemStack(result, 2, 38, true))
						return null;
				}
				else if(this.tileEntity.canInsertItem(0, result, 1))
				{
					if(!this.mergeItemStack(result, 0, 1, false))
						return null;
					else
						System.out.println("Merge ?");
				}
				if(result.stackSize == 0 )
					slot.putStack(null);
				else if(result.stackSize == originalStack.stackSize)
					return null;
				else
					slot.onSlotChange(result, originalStack);
			}
		}
		return originalStack;
	}
	
}
