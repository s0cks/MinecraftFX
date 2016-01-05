package mcfx;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import truetyper.FontHelper;

import java.awt.Color;
import java.awt.Rectangle;

public final class MCFXHelper{
    public static void drawString(String str, int x, int y, Color c){
        float[] color = {
            c.getRed(),
            c.getGreen(),
            c.getBlue(),
            c.getAlpha()
        };

        FontHelper.drawString(str, x, y, MCFXDecoratorEngine.get().font(), 1.0F, 1.0F, color);
    }

    public static void bindColor(Color c){
        GlStateManager.color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
    }

    public static float fontWidth(String str){
        return MCFXDecoratorEngine.get().font().getWidth(str);
    }

    public static float fontHeight(){
        return MCFXDecoratorEngine.get().font().getHeight();
    }

    public static void drawQuad(Rectangle geom, float zlevel, Color c){
        Tessellator tess = Tessellator.getInstance();
        WorldRenderer rend = tess.getWorldRenderer();
        rend.startDrawingQuads();
        GlStateManager.disableTexture2D();
        MCFXHelper.bindColor(c);
        rend.addVertex(geom.x, geom.y + geom.height, zlevel);
        rend.addVertex(geom.x + geom.width, geom.y + geom.height, zlevel);
        rend.addVertex(geom.x + geom.width, geom.y, zlevel);
        rend.addVertex(geom.x, geom.y, zlevel);
        tess.draw();
        GlStateManager.enableTexture2D();
    }

    public static Color complement(Color c){
        return new Color(255 - c.getRed(),
                         255 - c.getGreen(),
                         255 - c.getBlue(),
                         c.getAlpha());
    }
}