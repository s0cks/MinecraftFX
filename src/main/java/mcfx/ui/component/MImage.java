package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.ui.MComponent;
import mcfx.ui.RenderContext;

import java.awt.image.BufferedImage;

@Named(MCFXDecorator.IMAGE)
public final class MImage
extends MComponent{
    private final BufferedImage image;

    public MImage(BufferedImage image){
        this.image = image;
    }

    public BufferedImage image(){
        return this.image;
    }

    @Override
    public void paint(RenderContext ctx) {
        MCFXDecoratorEngine.get().getDecorator(MCFXDecorator.IMAGE, this.getClass()).paint(ctx, this);
    }
}