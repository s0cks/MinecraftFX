package mcfx.ui.component;

import mcfx.MCFXDecorator;
import mcfx.MCFXDecoratorEngine;
import mcfx.Named;
import mcfx.ui.MComponent;
import mcfx.ui.RenderContext;
import net.minecraft.entity.Entity;

@Named(MCFXDecorator.ENTITY)
public final class MEntity
extends MComponent {
    private final Entity entity;

    public MEntity(Entity entity){
        this.entity = entity;
        this.setPreferredSize(64, 128);
    }

    public Entity entity(){
        return this.entity;
    }

    @Override
    public void paint(RenderContext ctx) {
        MCFXDecoratorEngine.get().getDecorator(MCFXDecorator.ENTITY, getClass())
                .paint(ctx, this);
    }
}