package mcfx.example.gui;

import mcfx.ui.MFrame;
import mcfx.ui.component.MButton;
import mcfx.ui.component.MImage;
import mcfx.ui.component.MToggleButton;
import mcfx.ui.layout.MVBoxLayout;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

@SideOnly(Side.CLIENT)
public final class MCFXExampleUI
extends MFrame {
    private static final BufferedImage img;
    static{
        try{
            BufferedImage tmp = ImageIO.read(new URL("http://screenshots.en.sftcdn.net/en/scrn/189000/189271/minecraft-10-700x393.jpg"));
            img = new BufferedImage(128, 64, BufferedImage.TYPE_INT_ARGB);
            Graphics g = img.getGraphics();
            g.drawImage(tmp.getScaledInstance(128, 64, Image.SCALE_SMOOTH), 0, 0, 128, 64, null);
        } catch(Exception e){
            e.printStackTrace(System.err);
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