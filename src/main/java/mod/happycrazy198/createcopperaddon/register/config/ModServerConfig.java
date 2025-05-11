package mod.happycrazy198.createcopperaddon.register.config;

import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.kinetics.BlockStressValues;
import com.simibubi.create.foundation.utility.Couple;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ModServerConfig {

    public final StressValues stressValues = new StressValues();

    public void registerAll(ForgeConfigSpec.Builder builder) {
        builder.push("stressValues");
        stressValues.registerAll(builder);
        builder.pop();
    }

    public static class StressValues implements BlockStressValues.IStressValueProvider {
        protected final Map<ResourceLocation, ForgeConfigSpec.ConfigValue<Double>> impacts = new HashMap<>();
        protected final Map<ResourceLocation, ForgeConfigSpec.ConfigValue<Double>> capacities = new HashMap<>();
        protected final Map<ResourceLocation, ForgeConfigSpec.ConfigValue<Supplier<Couple<Integer>>>> rpms = new HashMap<>();

        public void registerAll(ForgeConfigSpec.Builder builder) {
            builder.push("impact");
            BlockStressDefaults.DEFAULT_IMPACTS.forEach((r, i) -> {
                if (r.getNamespace().equals("createcopperaddon")) {
                    impacts.put(r, builder.define(r.getPath(), i));
                }
            });
            builder.pop().push("capacities");
            BlockStressDefaults.DEFAULT_CAPACITIES.forEach((r, i) -> {
                if (r.getNamespace().equals("createcopperaddon")) {
                    capacities.put(r, builder.define(r.getPath(), i));
                }
            });
            builder.pop().push("rpms");
            BlockStressDefaults.GENERATOR_SPEEDS.forEach((r, i) -> {
                if (r.getNamespace().equals("createcopperaddon")) {
                    rpms.put(r, builder.define(r.getPath(), i));
                }
            });
            builder.pop();

            BlockStressValues.registerProvider("createcopperaddon", this);
        }

        @Override
        public double getImpact(Block block) {
            ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
            ForgeConfigSpec.ConfigValue<Double> c = impacts.get(key);
            return c == null ? 0d : c.get();
        }

        @Override
        public double getCapacity(Block block) {
            ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
            ForgeConfigSpec.ConfigValue<Double> c = capacities.get(key);
            return c == null ? 0d : c.get();
        }

        @Override
        public boolean hasImpact(Block block) {
            ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
            return impacts.containsKey(key);
        }

        @Override
        public boolean hasCapacity(Block block) {
            ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
            return capacities.containsKey(key);
        }

        @Override
        @Nullable
        public Couple<Integer> getGeneratedRPM(Block block) {
            ResourceLocation key = BuiltInRegistries.BLOCK.getKey(block);
            ForgeConfigSpec.ConfigValue<Supplier<Couple<Integer>>> c = rpms.get(key);
            return c == null ? null : c.get().get();
        }
    }
}
