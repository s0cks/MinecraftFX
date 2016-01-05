package mcfx.ui;

import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.ui.event.ActionEvent;
import mcfx.ui.layout.MAbsoluteLayout;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.awt.Dimension;
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
    private int x;
    private int y;
    private Dimension size = new Dimension(500, 500);

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
    public void initGui(){
        super.initGui();
        this.adjust();
        this.layout.layout();
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

    public void setSize(int width, int height){
        this.size.setSize(width, height);
    }

    private void adjust(){
        this.x = ((FMLClientHandler.instance().getClient().displayWidth - width) / 2);
        this.y = ((FMLClientHandler.instance().getClient().displayHeight - height) / 2);
        for(MComponent comp : this.children){
            comp.setPosition(x + comp.getX(), y + comp.getY());
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
        if(!comp.getClass().isAnnotationPresent(Named.class)){
            throw new IllegalStateException("MComponents need @Named above them");
        }
        MCFXDecoratorEngine.get().getDecorator(comp.getClass().getAnnotation(Named.class).value(), comp.getClass()).init(comp);
        this.layout.addComponent(comp);
    }

    @Override
    public void add(MComponent comp, Object... flags) {
        if(!comp.getClass().isAnnotationPresent(Named.class)){
            throw new IllegalStateException("MComponents need @Named above them");
        }
        MCFXDecoratorEngine.get().getDecorator(comp.getClass().getAnnotation(Named.class).value(), comp.getClass()).init(comp);
        this.layout.addComponent(comp, flags);
    }

    @Override
    public void addComponent(MComponent comp) {
        comp.setParent(this);
        comp.setZLevel(this.zLevel + 1);
        this.children.add(comp);
    }

    @Override
    public Dimension getSize() {
        return this.size;
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