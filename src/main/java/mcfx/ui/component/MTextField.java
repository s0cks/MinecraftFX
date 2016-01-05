package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.ui.MComponent;
import mcfx.ui.RenderContext;

@Named(MCFXDecorator.TEXT_FIELD)
public final class MTextField
extends MComponent{
    private String text;
    private int width;

    public MTextField(int width){
        this.text = "";
        this.width = (int) MCFXDecoratorEngine.get().font().getWidth(width);
    }

    public MTextField(String text){
        this.text = text;
        this.width = (int) MCFXDecoratorEngine.get().font().getWidth(text);
    }

    public int width(){
        return this.width;
    }

    public final String text(){
        return this.text;
    }

    public final void setText(String text){
        this.text = text;
    }

    public final void append(String str){
        this.text += str;
    }

    public final void backspace(){
        this.text = this.text.substring(0, this.text.length() - 1);
    }

    @Override
    public void paint(RenderContext ctx) {
        MCFXDecoratorEngine.get().getDecorator(MCFXDecorator.TEXT_FIELD, MTextField.class).paint(ctx, this);
    }
}