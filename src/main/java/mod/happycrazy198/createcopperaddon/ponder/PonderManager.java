package mod.happycrazy198.createcopperaddon.ponder;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class PonderManager {

    public static void register(final FMLClientSetupEvent event) {
        event.enqueueWork(Flask_Ponder::register);
    }
}
