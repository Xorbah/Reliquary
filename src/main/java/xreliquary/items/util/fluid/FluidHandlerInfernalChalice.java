package xreliquary.items.util.fluid;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import xreliquary.init.ModItems;
import xreliquary.reference.Settings;

public class FluidHandlerInfernalChalice extends FluidHandlerItemStack {
	public FluidHandlerInfernalChalice(ItemStack chalice) {
		super(chalice, Settings.Items.InfernalChalice.fluidLimit);
	}

	@Override
	public boolean canDrainFluidType(FluidStack fluid) {
		return !ModItems.infernalChalice.isEnabled(container) && fluid.getFluid() == FluidRegistry.LAVA && getFluid().amount >= fluid.amount;
	}

	@Override
	protected void setContainerToEmpty() {
		setFluid(new FluidStack(FluidRegistry.LAVA, 0));
	}

	@Override
	public boolean canFillFluidType(FluidStack fluid) {
		return ModItems.infernalChalice.isEnabled(container) && fluid.getFluid() == FluidRegistry.LAVA;
	}

	@Override
	protected void setFluid(FluidStack fluid) {
		if (!container.hasTagCompound())
		{
			container.setTagCompound(new NBTTagCompound());
		}

		//noinspection ConstantConditions
		container.getTagCompound().setInteger("fluidStacks", fluid.amount);
	}

	@Override
	public FluidStack getFluid() {
		NBTTagCompound tagCompound = container.getTagCompound();
		if(tagCompound == null || !tagCompound.hasKey("fluidStacks")) {
			return new FluidStack(FluidRegistry.LAVA, 0);
		}
		return new FluidStack(FluidRegistry.LAVA, tagCompound.getInteger("fluidStacks"));
	}
}
