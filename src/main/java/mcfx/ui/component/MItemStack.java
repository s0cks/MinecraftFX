package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.ui.MComponent;
import mcfx.ui.RenderContext;
import net.minecraft.item.ItemStack;

@Named(MCFXDecorator.ITEM_STACK)
public final class MItemStack
extends MComponent{
    private final ItemStack stack;

    public MItemStack(ItemStack stack){
        this.stack = stack;
    }

    public ItemStack getStack(){
        return this.stack;
    }

    @Override
    public void paint(RenderContext ctx) {
        MCFXDecoratorEngine.get().getDecorator(MCFXDecorator.ITEM_STACK, getClass())
                .paint(ctx, this);
    }
}