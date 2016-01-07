package mcfx.dengine.basic.decorator;

import mcfx.MCFXDecorator;
import mcfx.Named;
import mcfx.ui.RenderContext;
import mcfx.ui.component.MEntity;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.awt.Color;

@Named(MCFXDecorator.ENTITY)
public final class BasicMCFXEntityDecorator
implements MCFXDecorator<MEntity> {
    @Override
    public void init(MEntity mEntity) {

    }

    @Override
    public void paint(RenderContext ctx, MEntity mEntity) {
        ctx.setColor(Color.DARK_GRAY)
            .setZLevel(mEntity.getZLevel())
            .drawRectangle(mEntity.geomentry());

        int x = mEntity.geomentry().width / 2;
        int y = mEntity.geomentry().height / 2;

        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate(mEntity.getX() + x, mEntity.getY() + y + (mEntity.getPreferredSize().height / 4), 50.0F);
        GlStateManager.scale(-30, 30, 30);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager manager = FMLClientHandler.instance().getClient().getRenderManager();
        manager.renderEntitySimple(mEntity.entity(), 1.0F);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}