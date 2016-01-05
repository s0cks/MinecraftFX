package mcfx.ui.model;

import mcfx.ui.ButtonGroup;

public final class ToggleButtonModel
extends AbstractButtonModel{
    protected ButtonGroup group;

    public ToggleButtonModel setGroup(ButtonGroup group){
        this.group = group;
        return this;
    }

    public ButtonGroup getGroup(){
        return this.group;
    }
}