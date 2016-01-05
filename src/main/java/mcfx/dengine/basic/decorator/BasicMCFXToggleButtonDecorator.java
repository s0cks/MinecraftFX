package mcfx.dengine.basic.decorator;

import mcfx.MCFXDecorator;
import mcfx.MCFXHelper;
import mcfx.Named;
import mcfx.ui.RenderContext;
import mcfx.ui.component.MToggleButton;

import java.awt.Dimension;
import java.awt.Rectangle;

@Named(MCFXDecorator.TOGGLE_BUTTON)
public final class BasicMCFXToggleButtonDecorator
implements MCFXDecorator<MToggleButton>{
    @Override
    public void init(MToggleButton mToggleButton) {
        Dimension preferred = mToggleButton.getPreferredSize();
        int x = mToggleButton.geomentry().x;
        int y = mToggleButton.geomentry().y;
        int width = (int) Math.max(preferred.width, mToggleButton.text() != null ? MCFXHelper.fontWidth(mToggleButton.text()) : preferred.width);
        int height = (int) Math.max(preferred.height, MCFXHelper.fontHeight());
        mToggleButton.setGeometry(x, y, width, height);
    }

    @Override
    public void paint(RenderContext ctx, MToggleButton mToggleButton) {
        Rectangle geom = mToggleButton.geomentry();
        ctx.setColor(mToggleButton.model().isActive() ? MCFXHelper.complement(mToggleButton.getBackground()) : mToggleButton.getBackground())
           .setZLevel(mToggleButton.getZLevel())
           .drawRectangle(geom);

        if(mToggleButton.text() != null){
            int x = geom.x + mToggleButton.getInsets().left;
            int y = geom.y + mToggleButton.getInsets().top;
            x += ((geom.width - MCFXHelper.fontWidth(mToggleButton.text())) / 2);
            y += ((geom.height - MCFXHelper.fontHeight()) / 2);
            MCFXHelper.drawString(mToggleButton.text(), x, y, mToggleButton.model().isActive() ? MCFXHelper.complement(mToggleButton.getForeground()) : mToggleButton.getForeground());
        }
    }
}