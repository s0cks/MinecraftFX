package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.ui.MComponent;
import mcfx.ui.RenderContext;
import mcfx.ui.model.ButtonModel;

import java.awt.Color;
import java.awt.Insets;

public final class MButton
extends MComponent {
    public static final String BG_COLOR = "mcfx.button.bg";
    public static final String FG_COLOR = "mcfx.button.fg";

    private final Insets insets = new Insets(5, 5, 5, 5);

    private ButtonModel model = new ButtonModel();
    private Color background = MCFXDecoratorEngine.get().getProperty(BG_COLOR);
    private Color foreground = MCFXDecoratorEngine.get().getProperty(FG_COLOR);
    private String label;

    public MButton(){
        this(null);
    }

    public MButton(String label){
        this.label = label;
        this.setPreferredSize(128, 32);
        MCFXDecoratorEngine.get().getDecorator(MCFXDecorator.BUTTON, MButton.class).init(this);
    }

    protected final void setModel(ButtonModel model){
        this.model = model;
    }

    public final ButtonModel model(){
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