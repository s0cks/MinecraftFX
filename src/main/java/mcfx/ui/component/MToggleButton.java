package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.ui.RenderContext;
import mcfx.ui.event.ActionEvent;
import mcfx.ui.listener.ActionListener;
import mcfx.ui.model.ToggleButtonModel;

import java.awt.Color;

@Named(MCFXDecorator.TOGGLE_BUTTON)
public class MToggleButton
extends MButton{
    public static final String BACKGROUND = "mcfx.toggle_button.bg";
    public static final String FOREGROUND = "mcfx.toggle_button.fg";

    private Color bg = MCFXDecoratorEngine.get().getProperty(BACKGROUND, Color.BLACK);
    private Color fg = MCFXDecoratorEngine.get().getProperty(FOREGROUND, Color.WHITE);

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