package me.pineapple.opponent.client.module.modules.visual;

import me.pineapple.opponent.api.utils.RenderUtil;
import me.pineapple.opponent.client.module.Module;
import me.pineapple.opponent.client.module.ModuleManifest;
import me.pineapple.opponent.client.other.ColorHandler;
import net.minecraft.util.math.RayTraceResult;

@ModuleManifest(label = "BlockHighlight", category = Module.Category.VISUAL)
public class BlockHighlight extends Module {

    @Override
    public void onRender3D() {
        if (mc.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
            RenderUtil.drawOutline(mc.objectMouseOver.getBlockPos(), ColorHandler.getColor());
        }
    }

}
