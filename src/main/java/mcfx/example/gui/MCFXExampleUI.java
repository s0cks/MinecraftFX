package mcfx.example.gui;

import mcfx.ui.MFrame;
import mcfx.ui.component.MButton;
import mcfx.ui.event.ActionEvent;
import mcfx.ui.listener.ActionListener;

public final class MCFXExampleUI
extends MFrame {
    public MCFXExampleUI(){
        MButton button = new MButton("Hello World");
        button.addActionListener(new ActionListener() {
            @Override
            public void on(ActionEvent e) {
                System.out.println("Clicked");
            }
        });
        this.add(button);
    }
}