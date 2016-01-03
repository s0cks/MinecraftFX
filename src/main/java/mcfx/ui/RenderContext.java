package mcfx.ui;

import mcfx.MCFXHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

import java.awt.Color;
import java.awt.Rectangle;

public final class RenderContext{
    private final Tessellator tess = Tessellator.getInstance();
    private final WorldRenderer rend = tess.getWorldRenderer();
    private Color color = Color.WHITE;
    private float zLevel = 0.0F;
    private float x, y;

    public RenderContext setZLevel(float z){
        this.zLevel = z;
        return this;
    }

    public RenderContext drawRectangle(Rectangle geom){
        this.rend.startDrawingQuads();
        GlStateManager.disableTexture2D();
        MCFXHelper.bindColor(this.color);
        this.rend.addVertex(this.x + geom.x, this.y + geom.y + geom.height, this.zLevel);
        this.rend.addVertex(this.x + geom.x + geom.width, this.y + geom.y + geom.height, this.zLevel);
        this.rend.addVertex(this.x + geom.x + geom.width, this.y + geom.y, this.zLevel);
        this.rend.addVertex(this.x + geom.x, this.y + geom.y, this.zLevel);
        this.tess.draw();
        GlStateManager.enableTexture2D();
        return this;
    }

    public RenderContext translate(float x, float y){
        this.x += x;
        this.y += y;
        return this;
    }

    public void reset(){
        this.color = Color.WHITE;
        this.x = 0;
        this.y = 0;
        this.zLevel = 0;
    }

    public RenderContext setColor(Color c){
        this.color = c;
        return this;
    }
}