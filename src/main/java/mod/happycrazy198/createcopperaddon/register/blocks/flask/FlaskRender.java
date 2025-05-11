package mod.happycrazy198.createcopperaddon.register.blocks.flask;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.ComponentRenderUtils;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.phys.AABB;
import mod.happycrazy198.createcopperaddon.utils.*;

import java.awt.*;

public class FlaskRender implements BlockEntityRenderer<FlaskBlockEntity> {
    public FlaskRender(BlockEntityRendererProvider.Context context) {
        super();
        // use context for rendering setup
    }

    @Override
    public void render(FlaskBlockEntity entity, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        float level = entity.getFluidLevel(); // 0.0 to 1.0
        if (level <= 0f) return;

        // Calculate height of the water cube (0 to 0.875 for realism)
        float height = level * 0.875f;

        // Start rendering
        poseStack.pushPose();

        // Slightly smaller than block bounds
        AABB box = new AABB(2/16f, 0, 2/16f, 14/16f, height, 14/16f);
        RenderUtils.renderTranslucentCuboid(poseStack, bufferSource, box, new Color(64, 128, 255, 128));

        poseStack.popPose();
    }
}

