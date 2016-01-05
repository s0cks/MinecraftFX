package mcfx.ui.model;

public abstract class AbstractButtonModel{
    protected boolean clicked;
    protected boolean active;

    public final boolean isClicked() {
        return clicked;
    }

    public final AbstractButtonModel setClicked(boolean clicked) {
        this.clicked = clicked;
        return this;
    }

    public final boolean isActive(){
        return this.active;
    }

    public final AbstractButtonModel setActive(boolean active){
        this.active = active;
        return this;
    }
}