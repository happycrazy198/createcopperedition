package mod.happycrazy198.createcopperaddon.register;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.ModelGen;

import mod.happycrazy198.createcopperaddon.register.blocks.flask.Flask;
import mod.happycrazy198.createcopperaddon.register.*;
import mod.happycrazy198.createcopperaddon.register.config.ModConfigs;
import com.simibubi.create.content.kinetics.BlockStressValues;
import com.simibubi.create.content.kinetics.BlockStressDefaults;

import net.minecraft.world.level.material.MapColor;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tterrag.registrate.util.entry.BlockEntry;
import mod.happycrazy198.createcopperaddon.ponder.*;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

@Mod(CreateAddon.MODID)
public class CreateAddon {

	public static final String MODID = "createcopperaddon";

	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);
	public static final CreateRegistrate TABBED_REGISTRATE = REGISTRATE.setCreativeTab(Tab.MAIN);

	private static final Logger LOGGER = LogManager.getLogger(MODID);

	// Register your blocks here
	public static final BlockEntry<Flask> FLASK = TABBED_REGISTRATE.block("flask", Flask::new)
			.initialProperties(SharedProperties::copperMetal)
			.properties(p -> p.mapColor(MapColor.METAL))
			.item()
			.transform(customItemModel())
			.register();

	public CreateAddon() {
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () ->
			() -> FMLJavaModLoadingContext.get().getModEventBus().addListener(PonderManager::register)
		);
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		// Must register creative tab before REGISTRATE blocks
		Tab.register(modEventBus);
		REGISTRATE.registerEventListeners(modEventBus);

		// Register blocks and configs
		ModBlocks.register();
		ModConfigs.register();
		ModBlockEntities.register();
	}
}
