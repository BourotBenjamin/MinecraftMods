package philoupe.simplemod.blocks;

import philoupe.simplemod.items.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class PhiloupeCompressorTileEntity extends TileEntity implements ISidedInventory{

	private final int[] slotsTop = new int[]{0};
	private final int[] slotsBottom = new int[]{1};
	private final int[] slotsSide = new int[]{1};
	private ItemStack[] compressorItemStacks = new ItemStack[2];

	public final int compressTimePerBlocks = 200;
	public int currentItemCompressTime = 0;
	public boolean compressing;
	
	public String name = "Philoupe Compressor";
	
	@Override
	public int getSizeInventory() {
		return compressorItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return compressorItemStacks[index];
	}

	@Override
	public ItemStack decrStackSize(int stack, int nbItems)
	{
		ItemStack itemStack = null;
		if(compressorItemStacks[stack] != null)
		{
			if(compressorItemStacks[stack].stackSize <= nbItems)
			{
				currentItemCompressTime = 0;
				itemStack = compressorItemStacks[stack];
				compressorItemStacks[stack] = null;
				this.compressing = false;
				PhiloupeCompressor.updateBlockState(false, worldObj, xCoord, yCoord, zCoord);
			}
			else
			{
				itemStack = compressorItemStacks[stack].splitStack(nbItems);
			}
		}
		this.markDirty();
		return itemStack;
	}

	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		if(tag.hasKey("stackTop"))
		{
			compressorItemStacks[0] = ItemStack.loadItemStackFromNBT((NBTTagCompound) tag.getTag("stackTop"));
		}
		if(tag.hasKey("stackBottom"))
		{
			compressorItemStacks[1] = ItemStack.loadItemStackFromNBT((NBTTagCompound) tag.getTag("stackBottom"));
		}
		currentItemCompressTime = tag.getInteger("currentItemCompressTime");
		name = tag.getString("name");
		compressing = tag.getBoolean("compressing");
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		if(compressorItemStacks[0] != null)
		{
			NBTTagCompound stackTop = new NBTTagCompound();
			compressorItemStacks[0].writeToNBT(stackTop);
			tag.setTag("stackTop", stackTop);
		}
		if(compressorItemStacks[1] != null)
		{
			NBTTagCompound stackBottom = new NBTTagCompound();
			compressorItemStacks[1].writeToNBT(stackBottom);
			tag.setTag("stackBottom", stackBottom);
		}
		tag.setInteger("currentItemCompressTime", currentItemCompressTime);
		tag.setString("name", name);
		tag.setBoolean("compressing", compressing);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int index) 
	{
		currentItemCompressTime = 0;
		ItemStack itemStack = compressorItemStacks[index];
		compressorItemStacks[index] = null;
		this.compressing = false;
		PhiloupeCompressor.updateBlockState(false, worldObj, xCoord, yCoord, zCoord);
		this.markDirty();
		return itemStack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		compressorItemStacks[index] =  stack;
		this.compressing = true;
		PhiloupeCompressor.updateBlockState(true, worldObj, xCoord, yCoord, zCoord);
		this.markDirty();
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.name : "Philoupe Compressor";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.name != null && this.name.length() >0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public void updateEntity()
	{
		if(this.compressing && this.canCompress())
		{
			this.markDirty();
			currentItemCompressTime++;
			if(currentItemCompressTime == compressTimePerBlocks)
			{
				currentItemCompressTime = 0;
				if(compressorItemStacks[1] == null)
					compressorItemStacks[1] = this.getCompressingResult(compressorItemStacks[0]);
				else
					++compressorItemStacks[1].stackSize;
				compressorItemStacks[0].stackSize--;
			}
			if(compressorItemStacks[0].stackSize == 0)
			{
				compressorItemStacks[0] = null;
				this.compressing = false;
				PhiloupeCompressor.updateBlockState(false, worldObj, xCoord, yCoord, zCoord);
			}
		}
	}

	private boolean canCompress()
	{
		return compressorItemStacks[0] != null && compressorItemStacks[0].isItemEqual(ModItems.philoupeDustStack) && (compressorItemStacks[1] == null || compressorItemStacks[1].stackSize < this.getInventoryStackLimit());
	}

	private ItemStack getCompressingResult(ItemStack stack)
	{
			if(stack.isItemEqual(ModItems.philoupeDustStack))
				return ModItems.philoupeOreStack.copy();
			else
				return null;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord +0.5, yCoord+0.5, zCoord+0.5) < 64.0;
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index == 1)
			return false;	
		else
			return compressorItemStacks[0] == null || compressorItemStacks[0].isItemEqual(ModItems.philoupeDustStack);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int side) {
		// TODO Auto-generated method stub
		return side == 0?this.slotsBottom:side == 1?this.slotsTop:this.slotsSide;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack stack, int nbItems) {
		return isItemValidForSlot(index, stack);
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, int nbItems) {
		return false;
	}

}
