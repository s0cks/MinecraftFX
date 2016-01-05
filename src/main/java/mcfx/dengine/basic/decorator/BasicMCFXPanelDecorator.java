package mcfx.dengine.basic.decorator;

import mcfx.MCFXDecorator;
import mcfx.Named;
import mcfx.ui.RenderContext;
import mcfx.ui.component.MPanel;

@Named(MCFXDecorator.PANEL)
public final class BasicMCFXPanelDecorator
implements MCFXDecorator<MPanel>{
    @Override
    public void init(MPanel mPanel) {

    }

    @Override
    public void paint(RenderContext ctx, MPanel mPanel) {

    }
}