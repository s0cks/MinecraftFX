package mcfx.ui;

import mcfx.ui.component.MButton;

import java.util.LinkedList;
import java.util.List;

public final class ButtonGroup{
    private final List<MButton> buttons = new LinkedList<>();

    public void onClick(MButton button){
        for(MButton b : this.buttons){
            if(b == button){
                b.model().setClicked(true);
            } else{
                b.model().setClicked(false);
            }
        }
    }
}