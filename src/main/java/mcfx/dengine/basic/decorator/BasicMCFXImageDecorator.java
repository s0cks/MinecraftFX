package mcfx.dengine.basic.decorator;

import mcfx.MCFXDecorator;
import mcfx.Named;
import mcfx.ui.RenderContext;
import mcfx.ui.component.MImage;

import java.awt.Color;
import java.awt.Rectangle;

@Named(MCFXDecorator.IMAGE)
public final class BasicMCFXImageDecorator
implements MCFXDecorator<MImage> {
    @Override
    public void init(MImage mImage) {
        int x = mImage.geomentry().x;
        int y = mImage.geomentry().y;
        int width = Math.max(mImage.getWidth(), mImage.geomentry().width);
        int height = Math.max(mImage.getHeight(), mImage.geomentry().height);
        mImage.setGeometry(x, y, width, height);
    }

    @Override
    public void paint(RenderContext ctx, MImage mImage) {
        Rectangle geo = mImage.geomentry();
        ctx.setZLevel(mImage.getZLevel())
           .setColor(Color.WHITE)
           .drawImage(mImage.image(), geo.x, geo.y, geo.width, geo.height);
    }
}