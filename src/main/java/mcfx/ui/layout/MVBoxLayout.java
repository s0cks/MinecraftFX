package mcfx.ui.layout;

import mcfx.ui.MComponent;
import mcfx.ui.MContainer;
import mcfx.ui.MLayout;

public final class MVBoxLayout
extends MLayout {
    private MComponent last;
    private int lastY;
    private final int padding;

    public MVBoxLayout(MContainer container, int padding){
        super(container);
        this.padding = padding;
    }

    public MVBoxLayout(MContainer container) {
        super(container);
        this.padding = 1;
    }

    @Override
    public void addComponent(MComponent comp) {
        comp.setGeometry(0, this.lastY + (this.last == null ? 0 : this.last.geomentry().height + this.padding), comp.geomentry().width, comp.geomentry().height);
        this.lastY = comp.geomentry().y;
        this.last = comp;
        super.addComponent(comp);
    }
}