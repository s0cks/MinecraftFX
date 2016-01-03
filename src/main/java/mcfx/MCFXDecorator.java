package mcfx;

import mcfx.ui.MComponent;
import mcfx.ui.RenderContext;

public interface MCFXDecorator<T extends MComponent>{
    public static final String BUTTON = "mcfx.button";
    public static final String TEXT_FIELD = "mcfx.text_field";

    public void init(T t);
    public void paint(RenderContext ctx, T t);
}