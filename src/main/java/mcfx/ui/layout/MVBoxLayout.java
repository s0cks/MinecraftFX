package mcfx.ui.layout;

import mcfx.ui.Alignment;
import mcfx.ui.MComponent;
import mcfx.ui.MContainer;
import mcfx.ui.MLayout;

public final class MVBoxLayout
extends MLayout {
    private final int padding;
    private final Alignment alignment;

    public MVBoxLayout(MContainer container, int padding, Alignment align){
        super(container);
        this.padding = padding;
        this.alignment = align;
    }

    public MVBoxLayout(MContainer container) {
        super(container);
        this.padding = 1;
        this.alignment = Alignment.CENTER;
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
            int x;
            switch(this.alignment){
                case CENTER:{
                    x = (this.container.getSize().width - comp.getPreferredSize().width) / 2;
                    break;
                }
                case LEFT:{
                    x = 0;
                    break;
                }
                case RIGHT:{
                    x = (this.container.getSize().width - comp.getPreferredSize().width);
                    break;
                }
                default:{
                    x = 0;
                    break;
                }
            }
            comp.setGeometry(this.container.getX() + x, this.container.getY() + y, comp.getPreferredSize().width, comp.getPreferredSize().height);
            y += comp.getPreferredSize().height + this.padding;
        }
    }
}