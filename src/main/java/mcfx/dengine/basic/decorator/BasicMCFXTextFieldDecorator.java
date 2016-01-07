package mcfx.dengine.basic.decorator;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.MCFXHelper;
import mcfx.Named;
import mcfx.ui.RenderContext;
import mcfx.ui.component.MTextField;

import java.awt.Color;
import java.awt.Rectangle;

@Named(MCFXDecorator.TEXT_FIELD)
public final class BasicMCFXTextFieldDecorator
implements MCFXDecorator<MTextField>{
    @Override
    public void init(MTextField mTextField) {
        mTextField.setPreferredSize(Math.max(mTextField.width(), mTextField.getPreferredSize().width), Math.max((int) MCFXDecoratorEngine.get().font().getHeight(), mTextField.getPreferredSize().height));
    }

    @Override
    public void paint(RenderContext ctx, MTextField mTextField) {
        Rectangle geo = mTextField.geomentry();
        ctx.setColor(Color.BLACK)
           .setZLevel(mTextField.getZLevel())
           .drawRectangle(geo);

        if(mTextField.text() != null){
            Rectangle bounds = mTextField.geomentry();
            int pad = (bounds.height / 4);
            MCFXHelper.drawString(mTextField.text(), bounds.x + 2, (int) (bounds.y + pad), Color.WHITE);
        }
    }
}