package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.ui.MComponent;
import mcfx.ui.RenderContext;
import mcfx.ui.event.ActionEvent;
import mcfx.ui.listener.ActionListener;
import mcfx.ui.model.AbstractButtonModel;
import mcfx.ui.model.ButtonModel;

import java.awt.Color;
import java.awt.Insets;

@Named(MCFXDecorator.BUTTON)
public class MButton
extends MComponent {
    public static final String BACKGROUND = "mcfx.button.bg";
    public static final String FOREGROUND = "mcfx.button.fg";

    private final Insets insets = new Insets(5, 5, 5, 5);

    protected AbstractButtonModel model = new ButtonModel();

    private Color background = MCFXDecoratorEngine.get().getProperty(BACKGROUND, Color.BLACK);
    private Color foreground = MCFXDecoratorEngine.get().getProperty(FOREGROUND, Color.WHITE);
    private String label;

    public MButton(){
        this(null);
    }

    public MButton(String label){
        this.label = label;
        this.setPreferredSize(128, 32);
        this.addActionListener(new ActionListener() {
            @Override
            public void on(ActionEvent e) {
                MButton.this.model.setClicked(!e.released);
            }
        });
    }

    protected final void setModel(ButtonModel model){
        this.model = model;
    }

    public final AbstractButtonModel model(){
        return this.model;
    }

    public final void setInsets(int left, int up, int right, int down){
        this.insets.set(left, up, right, down);
    }

    public final Insets getInsets(){
        return this.insets;
    }

    public final void setBackground(Color c){
        this.background = c;
    }

    public final void setForeground(Color c){
        this.foreground = c;
    }

    public final Color getForeground(){
        return this.foreground;
    }

    public final Color getBackground(){
        return this.background;
    }

    public final void setText(String text){
        this.label = text;
    }

    public final String text(){
        return this.label;
    }

    @Override
    public void paint(RenderContext ctx) {
        MCFXDecoratorEngine.get().getDecorator(MCFXDecorator.BUTTON, MButton.class).paint(ctx, this);
    }
}