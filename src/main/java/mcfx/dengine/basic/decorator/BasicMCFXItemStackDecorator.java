package mcfx.dengine.basic.decorator;

import mcfx.MCFXDecorator;
import mcfx.MCFXHelper;
import mcfx.Named;
import mcfx.ui.RenderContext;
import mcfx.ui.component.MItemStack;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;

import java.awt.Color;

@Named(MCFXDecorator.ITEM_STACK)
public final class BasicMCFXItemStackDecorator
implements MCFXDecorator<MItemStack>{
    @Override
    public void init(MItemStack mItemStack) {
        mItemStack.setPreferredSize(32, 32);
    }

    @Override
    public void paint(RenderContext ctx, MItemStack mItemStack) {
        ctx.setColor(Color.DARK_GRAY)
            .setZLevel(mItemStack.getZLevel())
            .drawRectangle(mItemStack.geomentry());
        GlStateManager.pushMatrix();
        int x = (mItemStack.geomentry().width - 16) / 2;
        int y = (mItemStack.geomentry().height - 16) / 2;
        GlStateManager.translate(mItemStack.getX() + x, mItemStack.getY() + y, mItemStack.getZLevel());
        RenderHelper.enableGUIStandardItemLighting();
        MCFXHelper.getRenderItem().renderItemAndEffectIntoGUI(mItemStack.getStack(), 0, 0);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }
}