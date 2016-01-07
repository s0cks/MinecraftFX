package mcfx.ui;

import mcfx.MCFXDecoratorEngine;
import mcfx.MCFXHelper;
import mcfx.Named;
import mcfx.ui.event.ActionEvent;
import mcfx.ui.layout.MAbsoluteLayout;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
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
    private Rectangle bounds = new Rectangle(0, 0, MCFXHelper.getMinecraftWidth() / 3, MCFXHelper.getMinecraftHeight() / 3);
    private Insets insets = new Insets(0, 0, 0, 0);

    public void setLayout(MLayout layout){
        this.layout = layout;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.paint(this.ctx);
        this.ctx.reset();
        for(MComponent comp : this.children){
            comp.paint(this.ctx);
            this.ctx.reset();
        }
    }

    @Override
    public void initGui(){
        super.initGui();
        this.layout();
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

    public void setInsets(int top, int left, int bottom, int right){
        this.insets.set(top, left, bottom, right);
    }

    public void setGeometry(int x, int y, int width, int height){
        this.bounds.setBounds(x, y, width, height);
    }

    public void setLocation(int x, int y){
        this.bounds.setLocation(x, y);
    }

    public void setLocation(Location loc){
        switch(loc){
            case CENTER:{
                ScaledResolution res = MCFXHelper.getScaledResolutionFromMinecraft(FMLClientHandler.instance().getClient());
                int x = (res.getScaledWidth() - this.bounds.width) / 2;
                int y = (res.getScaledHeight() - this.bounds.height) / 2;
                this.setLocation(x, y);
                break;
            }
            default:{
                throw new IllegalStateException("");
            }
        }
    }

    public void setSize(int width, int height){
        this.bounds.setSize(width, height);
    }

    @Override
    public void layout(){
        this.layout.layout();
        for(MComponent comp : this.children){
            comp.setPosition(this.bounds.x + this.insets.left + comp.getX(), this.bounds.y + this.insets.top + comp.getY());
            if(comp instanceof MContainer){
                ((MContainer) comp).layout();
            }
        }
    }

    @Override
    public int getChildrenCount() {
        return this.children.size();
    }

    @Override
    public MComponent getChild(int i) {
        return this.children.get(i);
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
        return this.bounds.getSize();
    }

    @Override
    public MWidget parent() {
        return null;
    }

    @Override
    public void setParent(MWidget widget) {
        throw new IllegalStateException("Cannot set parent of root screen");
    }

    @Override
    public int getX() {
        return this.bounds.x + this.insets.left;
    }

    @Override
    public int getY() {
        return this.bounds.y + this.insets.top;
    }

    public void paint(RenderContext ctx){}

    public final Rectangle geometry(){
        return this.bounds;
    }
}