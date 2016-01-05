package mcfx.ui;

import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.ui.event.ActionEvent;
import mcfx.ui.layout.MAbsoluteLayout;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MFrame
extends GuiScreen
implements MContainer,
           MWidget{

    private final List<MComponent> children = new LinkedList<>();
    private MLayout layout = new MAbsoluteLayout(this);
    private RenderContext ctx = new RenderContext();

    public void setLayout(MLayout layout){
        this.layout = layout;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        for(MComponent comp : this.children){
            comp.paint(this.ctx);
            this.ctx.reset();
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
    throws IOException {
        ActionEvent e = new ActionEvent(mouseX, mouseY, mouseButton, false);
        for(MComponent comp : this.children){
            if(comp.geomentry().contains(mouseX, mouseY)){
                comp.onAction(e);
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        ActionEvent e = new ActionEvent(mouseX, mouseY, state, true);
        for(MComponent comp : this.children){
            if(comp.geomentry().contains(mouseX, mouseY)){
                comp.onAction(e);
            }
        }
    }

    @Override
    public void add(MComponent comp) {
        this.layout.addComponent(comp);
    }

    @Override
    public void addComponent(MComponent comp) {
        comp.setParent(this);
        comp.setZLevel(this.zLevel + 1);
        if(!comp.getClass().isAnnotationPresent(Named.class)){
            throw new IllegalStateException("MComponents need @Named above them");
        }
        MCFXDecoratorEngine.get().getDecorator(comp.getClass().getAnnotation(Named.class).value(), comp.getClass()).init(comp);
        this.children.add(comp);
    }

    @Override
    public MWidget parent() {
        return null;
    }

    @Override
    public void setParent(MWidget widget) {
        throw new IllegalStateException("Cannot set parent of root screen");
    }
}