package mod.happycrazy198.createcopperaddon.register.blocks.flask;

import com.simibubi.create.foundation.fluid.SmartFluidTank;
import mod.happycrazy198.createcopperaddon.register.CreateAddon;
import mod.happycrazy198.createcopperaddon.register.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import javax.annotation.Nullable;


public class FlaskBlockEntity extends BlockEntity {
    public static final int TANK_CAPACITY = 1000;

    private final SmartFluidTank fluidTank;
    private LazyOptional<IFluidHandler> fluidCapability;

    public FlaskBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.FLASK.get(), pos, state);
        this.fluidTank = new SmartFluidTank(TANK_CAPACITY, fluidStack -> this.setChanged());
        this.fluidCapability = LazyOptional.of(() -> fluidTank);
    }

    public float getFluidLevel() {
        return (float) fluidTank.getFluidAmount() / fluidTank.getCapacity();
    }

    public SmartFluidTank getFluidTank() {
        return fluidTank;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        fluidTank.writeToNBT(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        fluidTank.readFromNBT(tag);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.FLUID_HANDLER) {
            return fluidCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        fluidCapability.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        fluidCapability = LazyOptional.of(() -> fluidTank);
    }
}

