package mcfx.ui.model;

import mcfx.ui.ButtonGroup;

public final class ButtonModel{
    private boolean clicked;
    private ButtonGroup group;

    public boolean isClicked() {
        return clicked;
    }

    public ButtonModel setClicked(boolean clicked) {
        this.clicked = clicked;
        return this;
    }

    public ButtonGroup getGroup() {
        return group;
    }

    public ButtonModel setGroup(ButtonGroup group) {
        this.group = group;
        return this;
    }
}