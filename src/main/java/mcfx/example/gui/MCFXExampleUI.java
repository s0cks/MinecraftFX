package mcfx.example.gui;

import mcfx.ui.MFrame;
import mcfx.ui.component.MButton;
import mcfx.ui.component.MImage;
import mcfx.ui.component.MToggleButton;
import mcfx.ui.layout.MVBoxLayout;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

@SideOnly(Side.CLIENT)
public final class MCFXExampleUI
extends MFrame {
    private static final BufferedImage img;
    static{
        try{
            img = ImageIO.read(new URL("http://screenshots.en.sftcdn.net/en/scrn/189000/189271/minecraft-10-700x393.jpg"));
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public MCFXExampleUI(){
        this.setLayout(new MVBoxLayout(this));
        this.add(new MButton("Hello World"));
        this.add(new MToggleButton("Test"));
        this.add(new MImage(img));
    }
}