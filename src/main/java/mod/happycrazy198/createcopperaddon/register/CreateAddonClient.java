package mod.happycrazy198.createcopperaddon.register;

import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import mod.happycrazy198.createcopperaddon.ponder.*;
import mod.happycrazy198.createcopperaddon.register.blocks.flask.*;
@Mod.EventBusSubscriber(modid = "createcopperaddon", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreateAddonClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FLASK.get(), RenderType.translucent());
            ModBlockEntities.registerRenderers();
            BlockEntityRenderers.register(ModBlockEntities.FLASK.get(), FlaskRender::new);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(PonderManager::register);
        });
    }
}
