package mcfx.example.gui;

import mcfx.ui.MFrame;
import mcfx.ui.component.MButton;
import mcfx.ui.component.MToggleButton;
import mcfx.ui.event.ActionEvent;
import mcfx.ui.layout.MVBoxLayout;
import mcfx.ui.listener.ActionListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class MCFXExampleUI
extends MFrame {
    public MCFXExampleUI(){
        this.setLayout(new MVBoxLayout(this));
        MButton button = new MButton("Hello World");
        button.addActionListener(new ActionListener() {
            @Override
            public void on(ActionEvent e) {
                System.out.println("Clicked");
            }
        });
        this.add(button);
        MToggleButton toggle = new MToggleButton("Hello World");
        toggle.setGeometry(0, button.geomentry().height + 10, 0, 0);
        this.add(toggle);
    }
}