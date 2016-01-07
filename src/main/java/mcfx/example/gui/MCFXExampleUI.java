package mcfx.example.gui;

import mcfx.ui.Location;
import mcfx.ui.MFrame;
import mcfx.ui.RenderContext;
import mcfx.ui.component.MButton;
import mcfx.ui.component.MImage;
import mcfx.ui.component.MPanel;
import mcfx.ui.component.MTextField;
import mcfx.ui.layout.MBorderLayout;
import mcfx.ui.layout.MVBoxLayout;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

@SideOnly(Side.CLIENT)
public final class MCFXExampleUI
extends MFrame {
    private static final BufferedImage img;
    static{
        try{
            BufferedImage tmp = ImageIO.read(new URL("https://images-eds-ssl.xboxlive.com/image?url=8Oaj9Ryq1G1_p3lLnXlsaZgGzAie6Mnu24_PawYuDYIoH77pJ.X5Z.MqQPibUVTcRY.yavzo7nYP0X88I63UeJxs_ICOvM1iX20FQwMAmM8GnLpv96ekMcvK4NGchKuL_D1rExrzwNPwBHn6H6bde1B6Q7_n3RxOxLdpXSYv0EE0y0vIFmUfHDkw9mU7THqKeEdzBxFp_X_eDX9mJA2KMw1w3wr29w.7tkyPlNeHQ9s-&format=jpg"));
            img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D) img.getGraphics();
            g2.drawImage(tmp.getScaledInstance(100, 100, Image.SCALE_SMOOTH), 0, 0, 128, 64, null);
            g2.dispose();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public MCFXExampleUI(){
        this.setLocation(Location.CENTER);
        this.setLayout(new MBorderLayout(this));

        MPanel panel = new MPanel();
        panel.setLayout(new MVBoxLayout(panel));
        panel.add(new MTextField(16));
        panel.add(new MImage(img));
        panel.add(new MButton("Close"));
        this.add(panel, MBorderLayout.CENTER);
    }

    @Override
    public void paint(RenderContext ctx){
        ctx.setColor(Color.RED)
           .setZLevel(this.zLevel)
           .drawRectangle(this.geometry());
    }
}