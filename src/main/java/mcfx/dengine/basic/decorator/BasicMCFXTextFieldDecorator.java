package mcfx.dengine.basic.decorator;

import mcfx.MCFXDecorator;
import mcfx.Named;
import mcfx.ui.RenderContext;
import mcfx.ui.component.MTextField;

@Named(MCFXDecorator.TEXT_FIELD)
public final class BasicMCFXTextFieldDecorator
implements MCFXDecorator<MTextField>{
    @Override
    public void init(MTextField mTextField) {

    }

    @Override
    public void paint(RenderContext ctx, MTextField mTextField) {

    }
}