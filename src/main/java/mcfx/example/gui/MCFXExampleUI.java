package mcfx.example.gui;

import mcfx.ui.MFrame;
import mcfx.ui.component.MButton;
import mcfx.ui.component.MTextField;
import mcfx.ui.event.ActionEvent;
import mcfx.ui.layout.MHBoxLayout;
import mcfx.ui.listener.ActionListener;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class MCFXExampleUI
extends MFrame {
    public MCFXExampleUI(){
        this.setLayout(new MHBoxLayout(this));
        final MTextField field = new MTextField(16);
        MButton button = new MButton("Test");
        button.addActionListener(new ActionListener() {
            @Override
            public void on(ActionEvent e) {
                field.append("Test ");
            }
        });
        this.add(button);
        this.add(field);
    }
}