package mcfx.ui;

public abstract class MLayout{
    protected final MContainer container;

    protected MLayout(MContainer container){
        this.container = container;
    }

    public void addComponent(MComponent comp){
        this.container.addComponent(comp);
    }
}