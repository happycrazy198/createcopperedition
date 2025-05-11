package mod.happycrazy198.createcopperaddon.register.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = "createcopperaddon", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfigs {

	public static ModServerConfig SERVER;

	private static final Map<Object, ModConfig.Type> CONFIGS = new HashMap<>();
	private static final Map<Object, ForgeConfigSpec> SPECS = new HashMap<>();

	public static void register() {
		SERVER = register(ModServerConfig::new, ModConfig.Type.SERVER);
		// Add other config types if needed (e.g., CLIENT, COMMON)
		for (Map.Entry<Object, ModConfig.Type> entry : CONFIGS.entrySet()) {
			ModLoadingContext.get().registerConfig(entry.getValue(), SPECS.get(entry.getKey()));
		}
	}

	private static <T> T register(Supplier<T> factory, ModConfig.Type type) {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		T config = factory.get();
		if (config instanceof ICustomConfig cc)
			cc.registerAll(builder);
		ForgeConfigSpec spec = builder.build();
		CONFIGS.put(config, type);
		SPECS.put(config, spec);
		return config;
	}

	@SubscribeEvent
	public static void onLoad(ModConfigEvent.Loading event) {
		// Optional: Add callbacks if needed
	}

	@SubscribeEvent
	public static void onReload(ModConfigEvent.Reloading event) {
		// Optional: Add callbacks if needed
	}

	// Interface to be implemented by config classes
	public interface ICustomConfig {
		void registerAll(ForgeConfigSpec.Builder builder);
	}
}
