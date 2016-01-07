package mcfx.example.gui;

import mcfx.ui.Location;
import mcfx.ui.MFrame;
import mcfx.ui.component.MEntity;
import mcfx.ui.layout.MBorderLayout;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.imageio.ImageIO;
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
            img = new BufferedImage(128, 64, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = (Graphics2D) img.getGraphics();
            g2.drawImage(tmp.getScaledInstance(128, 64, Image.SCALE_SMOOTH), 0, 0, 128, 64, null);
            g2.dispose();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public MCFXExampleUI(){
        this.setTitle("This is a window title");
        this.setLayout(new MBorderLayout(this));
        this.setLocation(Location.CENTER);
        this.add(new MEntity(FMLClientHandler.instance().getClientPlayerEntity()), MBorderLayout.CENTER);
    }
}