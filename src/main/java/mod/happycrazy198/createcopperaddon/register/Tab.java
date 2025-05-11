package mod.happycrazy198.createcopperaddon.register;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

import static mod.happycrazy198.createcopperaddon.register.CreateAddon.MODID;
import static mod.happycrazy198.createcopperaddon.register.ModBlocks.FLASK;

public class Tab {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> MAIN =
            TABS.register("main", () ->
                    CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.createcopperaddon.main"))
                            .icon(() -> new ItemStack(FLASK.get()))
                            .displayItems((parameters, output) -> {
                                output.accept(FLASK.get());
                            })
                            .build() // <-- Proper placement
            ); // <-- Semicolon here ends the statement

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }
}

