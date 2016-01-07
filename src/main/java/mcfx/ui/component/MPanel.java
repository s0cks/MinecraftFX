package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.MCFXHelper;
import mcfx.Named;
import mcfx.ui.MComponent;
import mcfx.ui.MContainer;
import mcfx.ui.MLayout;
import mcfx.ui.RenderContext;
import mcfx.ui.event.ActionEvent;
import mcfx.ui.layout.MBorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

@Named(MCFXDecorator.PANEL)
public final class MPanel
extends MComponent
implements MContainer{
    public static final String BACKGROUND = "mcfx.panel.bg";

    private final List<MComponent> children = new LinkedList<>();
    private MLayout layout;
    private Color bg = MCFXDecoratorEngine.get().getProperty(BACKGROUND, Color.DARK_GRAY);

    public MPanel(){
        this.layout = new MBorderLayout(this);
        Dimension prefDim = MCFXHelper.getPreferredMaxSize();
        this.setPreferredSize(prefDim.width, prefDim.height);
    }

    public MPanel(MLayout layout){
        this.layout = layout;
        Dimension prefDim = MCFXHelper.getPreferredMaxSize();
        this.setPreferredSize(prefDim.width, prefDim.height);
    }

    @Override
    public void onAction(ActionEvent e) {
        for(MComponent comp : this.children){
            if(comp.geomentry().contains(e.x, e.y)){
                comp.onAction(e);
            }
        }
    }

    public void setLayout(MLayout layout){
        this.layout = layout;
    }

    @Override
    public void paint(RenderContext ctx) {
        ctx.setColor(this.bg)
                .setZLevel(this.zLevel)
                .drawRectangle(this.geomentry());
        ctx.reset();
        for(MComponent comp : this.children){
            ctx.setZLevel(this.getZLevel() + 1);
            comp.paint(ctx);
            ctx.reset();
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
        comp.setZLevel(this.zLevel + 1);
        comp.setParent(this);
        this.children.add(comp);
    }

    @Override
    public void layout() {
        this.layout.layout();
    }

    @Override
    public int getChildrenCount() {
        return this.children.size();
    }

    @Override
    public MComponent getChild(int i) {
        return this.children.get(i);
    }
}