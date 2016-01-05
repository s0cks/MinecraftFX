package mcfx.ui.layout;

import mcfx.ui.MComponent;
import mcfx.ui.MContainer;
import mcfx.ui.MLayout;

public final class MHBoxLayout
extends MLayout {
    private MComponent last;
    private int lastX;
    private final int padding;

    public MHBoxLayout(MContainer container){
        super(container);
        this.padding = 1;
    }

    public MHBoxLayout(MContainer container, int padding){
        super(container);
        this.padding = padding;
    }

    @Override
    public void addComponent(MComponent comp){
        comp.setGeometry(this.lastX + (this.last == null ? 0 : this.last.geomentry().width + this.padding), 0, comp.geomentry().width, comp.geomentry().height);
        this.last = comp;
        this.lastX = comp.geomentry().x;
        super.addComponent(comp);
    }
}