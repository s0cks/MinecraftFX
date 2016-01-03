package mcfx.dengine.basic.decorator;

import mcfx.MCFXDecorator;
import mcfx.MCFXHelper;
import mcfx.Named;
import mcfx.ui.RenderContext;
import mcfx.ui.component.MButton;
import mcfx.ui.event.ActionEvent;
import mcfx.ui.listener.ActionListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

@Named(MCFXDecorator.BUTTON)
public final class BasicMCFXButtonDecorator
implements MCFXDecorator<MButton>{
    @Override
    public void init(final MButton mButton) {
        mButton.addActionListener(new ActionListener() {
            @Override
            public void on(ActionEvent e) {
                mButton.model().setClicked(!e.released);
            }
        });

        Dimension preferred = mButton.getPreferredSize();
        int x = mButton.geomentry().x;
        int y = mButton.geomentry().y;
        int width = (int) Math.max(preferred.width, mButton.text() != null ? MCFXHelper.fontWidth(mButton.text()) : preferred.width);
        int height = (int) Math.max(preferred.height, MCFXHelper.fontHeight());
        mButton.setGeometry(x, y, width, height);
    }

    @Override
    public void paint(RenderContext ctx, MButton mButton) {
        Rectangle geom = mButton.geomentry();
        ctx.setColor(mButton.model().isClicked() ? Color.WHITE : mButton.getBackground())
           .setZLevel(mButton.getZLevel())
           .drawRectangle(geom);

        if(mButton.text() != null){
            int x = geom.x + mButton.getInsets().left;
            int y = geom.y + mButton.getInsets().top;
            x += ((geom.width - MCFXHelper.fontWidth(mButton.text())) / 2);
            y += ((geom.height - MCFXHelper.fontHeight()) / 2);
            MCFXHelper.drawString(mButton.text(), x, y, mButton.model().isClicked() ? Color.BLACK: mButton.getForeground());
        }
    }
}