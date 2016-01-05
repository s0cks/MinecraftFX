package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.ui.MComponent;
import mcfx.ui.RenderContext;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.awt.image.BufferedImage;

@Named(MCFXDecorator.IMAGE)
public final class MImage
extends MComponent{
    private ResourceLocation imageLoc;
    private int width;
    private int height;

    public MImage(BufferedImage image){
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.imageLoc = FMLClientHandler.instance().getClient().getTextureManager().getDynamicTextureLocation("mcfx", new DynamicTexture(image));
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public ResourceLocation image(){
        return this.imageLoc;
    }

    @Override
    public void paint(RenderContext ctx) {
        MCFXDecoratorEngine.get().getDecorator(MCFXDecorator.IMAGE, this.getClass()).paint(ctx, this);
    }
}