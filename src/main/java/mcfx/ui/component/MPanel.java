package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.ui.MComponent;
import mcfx.ui.MContainer;
import mcfx.ui.MLayout;
import mcfx.ui.RenderContext;
import mcfx.ui.layout.MVBoxLayout;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.awt.Color;
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
        this.layout = new MVBoxLayout(this);
        this.setPreferredSize(FMLClientHandler.instance().getClient().displayWidth / 4, FMLClientHandler.instance().getClient().displayHeight / 4);
    }

    public MPanel(MLayout layout){
        this.setPreferredSize(FMLClientHandler.instance().getClient().displayWidth / 4, FMLClientHandler.instance().getClient().displayHeight / 4);
        this.layout = layout;
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
            comp.paint(ctx);
            ctx.reset();
        }
    }

    @Override
    public void setXY(int x, int y){
        super.setXY(x, y);
        for(MComponent child : this.children){
            child.setPosition(x + child.getX(), y + child.getY());
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
}