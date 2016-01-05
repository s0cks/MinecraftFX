package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.ui.RenderContext;
import mcfx.ui.event.ActionEvent;
import mcfx.ui.listener.ActionListener;
import mcfx.ui.model.ToggleButtonModel;

@Named(MCFXDecorator.TOGGLE_BUTTON)
public class MToggleButton
extends MButton{
    public MToggleButton(String lbl){
        super(lbl);
        this.model = new ToggleButtonModel();
        this.addActionListener(new ActionListener() {
            @Override
            public void on(ActionEvent e) {
                if(!e.released){
                    MToggleButton.this.model.setActive(!MToggleButton.this.model.isActive());
                }
            }
        });
    }

    public MToggleButton(){
        this(null);
    }

    @Override
    public void paint(RenderContext ctx){
        MCFXDecoratorEngine.get().getDecorator(MCFXDecorator.TOGGLE_BUTTON, getClass()).paint(ctx, this);
    }
}