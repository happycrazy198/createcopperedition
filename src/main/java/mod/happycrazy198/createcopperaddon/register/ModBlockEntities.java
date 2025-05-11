package mod.happycrazy198.createcopperaddon.register;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import mod.happycrazy198.createcopperaddon.register.blocks.flask.FlaskBlockEntity;
import mod.happycrazy198.createcopperaddon.register.blocks.flask.FlaskRender;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType.Builder;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import mod.happycrazy198.createcopperaddon.register.blocks.flask.*;

import static mod.happycrazy198.createcopperaddon.register.ModBlocks.FLASK;

@Mod.EventBusSubscriber(modid = "createcopperaddon", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "createcopperaddon");

    public static RegistryObject<BlockEntityType<FlaskBlockEntity>> FLASK;

    public static void register() {
        FLASK = BLOCK_ENTITIES.register("flask", () ->
                BlockEntityType.Builder.of(FlaskBlockEntity::new, ModBlocks.FLASK.get()).build(null)
        );

        BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers() {
        BlockEntityRenderers.register(FLASK.get(), FlaskRender::new);
    }
}
