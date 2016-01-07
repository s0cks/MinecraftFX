package mcfx.ui.layout;

import mcfx.ui.MComponent;
import mcfx.ui.MContainer;
import mcfx.ui.MLayout;

public final class MVBoxLayout
extends MLayout {
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
        super.addComponent(comp);
    }

    @Override
    public void layout(){
        int compCount = this.container.getChildrenCount();
        int y = 0;
        for(int i = 0; i < compCount; i++){
            MComponent comp = this.container.getChild(i);
            comp.setGeometry(this.container.getX(), this.container.getY() + y, comp.getPreferredSize().width, comp.getPreferredSize().height);
            y += comp.getPreferredSize().height + this.padding;
        }
    }
}