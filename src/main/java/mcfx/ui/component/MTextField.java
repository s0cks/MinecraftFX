package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.ui.MComponent;
import mcfx.ui.RenderContext;

public final class MTextField
extends MComponent{
    private String text;

    public MTextField(){
        this("");
    }

    public MTextField(String text){
        this.text = text;
        MCFXDecoratorEngine.get().getDecorator(MCFXDecorator.TEXT_FIELD, MTextField.class).init(this);
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