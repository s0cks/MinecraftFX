package mcfx;

import mcfx.ui.MComponent;
import mcfx.ui.RenderContext;

public interface MCFXDecorator<T extends MComponent>{
    public static final String BUTTON = "mcfx.button";
    public static final String TOGGLE_BUTTON = "mcfx.toggle_button";
    public static final String TEXT_FIELD = "mcfx.text_field";
    public static final String IMAGE = "mcfx.image";
    public static final String PANEL = "mcfx.panel";
    public static final String ITEM_STACK = "mcfx.item_stack";
    public static final String ENTITY = "mcfx.entity";

    public void init(T t);
    public void paint(RenderContext ctx, T t);
}