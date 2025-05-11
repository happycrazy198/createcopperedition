package mod.happycrazy198.createcopperaddon.utils;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.*;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.awt.*;

public class RenderUtils {

    public static void renderTranslucentCuboid(PoseStack poseStack, MultiBufferSource bufferSource, AABB box, Color color) {
        VertexConsumer builder = bufferSource.getBuffer(RenderType.translucent());

        RenderType.translucent();
        float r = color.getRed() / 255f;
        float g = color.getGreen() / 255f;
        float b = color.getBlue() / 255f;
        float a = color.getAlpha() / 255f;

        Matrix4f pose = poseStack.last().pose();

        // Draw each face of the box (simplified example: just one quad)
        builder.vertex(pose, (float) box.minX, (float) box.minY, (float) box.minZ).color(r, g, b, a).endVertex();
        builder.vertex(pose, (float) box.maxX, (float) box.minY, (float) box.minZ).color(r, g, b, a).endVertex();
        builder.vertex(pose, (float) box.maxX, (float) box.maxY, (float) box.minZ).color(r, g, b, a).endVertex();
        builder.vertex(pose, (float) box.minX, (float) box.maxY, (float) box.minZ).color(r, g, b, a).endVertex();

        // Ideally you would render all 6 faces of the cuboid for a full box.
    }
}
