package mod.happycrazy198.createcopperaddon.ponder;

import com.simibubi.create.foundation.ponder.*;

import mod.happycrazy198.createcopperaddon.register.ModBlocks;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class Flask_Ponder {
    static final PonderRegistrationHelper HELPER = new PonderRegistrationHelper("createcopperaddon");

    public static final PonderTag FLASK_TAG = new PonderTag(new ResourceLocation("createcopperaddon", "flask"))
            .item(ModBlocks.FLASK.get().asItem())
            .defaultLang("Flasks", "Flask");

    public static void register() {
        PonderRegistry.TAGS.forTag(FLASK_TAG)
                .add(ModBlocks.FLASK.get());
        HELPER.forComponents(ModBlocks.FLASK)
                .addStoryBoard("flask_demo", Flask_Ponder::flaskBasic);
    }

    public static void flaskBasic(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("flask_demo", "Processing liquids in the flask");

        // Baseplate is placed at Y=0, centered at (2, 0, 2), size 5x5
        scene.configureBasePlate(1, 1, 5);
        scene.world.showSection(util.select.layer(0), Direction.UP);
        scene.showBasePlate();
        scene.scaleSceneView(0.9f);
        scene.idle(15);

        // Place the Flask block at (2, 1, 2) — one block above baseplate
        scene.world.setBlock(util.grid.at(2, 1, 2), ModBlocks.FLASK.get().defaultBlockState(), false);
        scene.world.showSection(util.select.position(2, 1, 2), Direction.UP);

        // Display overlay text pointing to the Flask
        scene.overlay.showText(60)
                .pointAt(util.vector.topOf(2, 1, 2))
                .placeNearTarget()
                .text("The Flask can hold liquids for processing");
        scene.idle(80);
        scene.markAsFinished();
    }
}
