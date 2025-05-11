package mod.happycrazy198.createcopperaddon.register;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import mod.happycrazy198.createcopperaddon.register.blocks.flask.Flask;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraftforge.registries.RegistryObject;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static mod.happycrazy198.createcopperaddon.register.CreateAddon.REGISTRATE;
import static net.minecraftforge.registries.ForgeRegistries.BLOCKS;

public class ModBlocks {
    public static final BlockEntry<Flask> FLASK = REGISTRATE.block("flask", Flask::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p
                    .mapColor(MapColor.METAL)
                    .noOcclusion())
            .item()
            .transform(customItemModel())
            .register();


    public static void register() {}
}
