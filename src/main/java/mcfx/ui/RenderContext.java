package mcfx.ui;

import mcfx.MCFXDecoratorEngine;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import truetyper.FontHelper;

import java.awt.Color;
import java.awt.Rectangle;

public final class RenderContext{
    private final Tessellator tess = Tessellator.getInstance();
    private final WorldRenderer rend = tess.getWorldRenderer();
    private Color color;
    private float zLevel = 0.0F;
    private float alpha = 1.0F;
    private float x, y;

    public RenderContext setZLevel(float z){
        this.zLevel = z;
        return this;
    }

    public RenderContext drawRectangle(Rectangle geom){
        this.rend.startDrawingQuads();
        GlStateManager.disableTexture2D();
        this.bindColor();
        this.rend.addVertex(this.x + geom.x, this.y + geom.y + geom.height, this.zLevel);
        this.rend.addVertex(this.x + geom.x + geom.width, this.y + geom.y + geom.height, this.zLevel);
        this.rend.addVertex(this.x + geom.x + geom.width, this.y + geom.y, this.zLevel);
        this.rend.addVertex(this.x + geom.x, this.y + geom.y, this.zLevel);
        this.tess.draw();
        GlStateManager.enableTexture2D();
        return this;
    }

    private void bindColor(){
        if(this.color != null){
            int color = this.color.getRGB();
            int r = (color >> 16 & 0xFF);
            int g = (color >> 8 & 0xFF);
            int b = (color & 0xFF);
            this.rend.setColorRGBA(r, g, b, (int) (this.alpha * 255));
        }
    }

    public RenderContext drawImage(ResourceLocation loc, int x, int y, int width, int height){
        this.rend.startDrawingQuads();
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(loc);
        this.bindColor();
        this.rend.addVertexWithUV(this.x + x, this.y + y + height, this.zLevel, 0, 1);
        this.rend.addVertexWithUV(this.x + x + width, this.y + y + height, this.zLevel, 1, 1);
        this.rend.addVertexWithUV(this.x + x + width, this.y + y, this.zLevel, 1, 0);
        this.rend.addVertexWithUV(this.x + x, this.y + y, this.zLevel, 0, 0);
        this.tess.draw();
        return this;
    }

    public RenderContext translate(float x, float y){
        this.x += x;
        this.y += y;
        return this;
    }

    public void reset(){
        this.color = null;
        this.x = 0;
        this.y = 0;
        this.zLevel = 0;
        this.alpha = 1.0F;
    }

    public RenderContext setAlpha(float alpha){
        this.alpha = alpha;
        return this;
    }

    public RenderContext setColor(Color c){
        this.color = c;
        return this;
    }

    public RenderContext drawString(String str){
        float[] color;
        if(this.color != null){
            color = new float[]{
                    this.color.getRed() / 255,
                    this.color.getGreen() / 255,
                    this.color.getBlue() / 255,
                    this.color.getAlpha() / 255
            };
        } else{
            color = new float[]{
                    1.0F,
                    1.0F,
                    1.0F,
                    1.0F
            };
        }

        FontHelper.drawString(str, this.x, this.y, MCFXDecoratorEngine.get().font(), 1.0F, 1.0F, color);
        return this;
    }
}