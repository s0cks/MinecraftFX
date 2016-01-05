package mcfx.ui.layout;

import mcfx.ui.MComponent;
import mcfx.ui.MContainer;
import mcfx.ui.MLayout;

import java.awt.Dimension;

public final class MBorderLayout
extends MLayout {
    public static final int LEFT = 0x0;
    public static final int RIGHT = 0x1;
    public static final int TOP = 0x2;
    public static final int BOTTOM = 0x3;
    public static final int CENTER = 0x4;

    private MComponent top;
    private MComponent bottom;
    private MComponent right;
    private MComponent left;
    private MComponent center;

    public MBorderLayout(MContainer container) {
        super(container);
    }

    @Override
    public void addComponent(MComponent comp, Object... flags) {
        int flag;
        if (flags.length == 0) {
            flag = CENTER;
        } else {
            flag = (int) flags[0];
        }

        switch (flag) {
            case LEFT: {
                this.left = comp;
                break;
            }
            case RIGHT: {
                this.right = comp;
                break;
            }
            case BOTTOM: {
                this.bottom = comp;
                break;
            }
            case TOP: {
                this.top = comp;
                break;
            }
            case CENTER: {
                this.center = comp;
                break;
            }
        }
        super.addComponent(comp);
    }

    private MComponent getChild(int flag) {
        switch (flag) {
            case LEFT:
                return this.left;
            case RIGHT:
                return this.right;
            case CENTER:
                return this.center;
            case TOP:
                return this.top;
            case BOTTOM:
                return this.bottom;
            default:
                return null;
        }
    }

    @Override
    public void layout(){
        int var4 = 0;
        int var5 = this.container.getSize().height;
        int var6 = 0;
        int var7 = this.container.getSize().width;

        MComponent var9;
        Dimension var10;
        if((var9 = this.getChild(TOP)) != null){
            var9.setSize(var7 - var6, var9.geomentry().height);
            var10 = var9.getPreferredSize();
            var9.setGeometry(var6, var4, var7 - var6, var10.height);
            var4 += var10.height + 1;
        }

        if((var9 = this.getChild(BOTTOM)) != null){
            var9.setSize(var7 - var6, var9.geomentry().height);
            var10 = var9.getPreferredSize();
            var9.setGeometry(var6, var5 - var10.height, var7 - var6, var10.height);
            var5 -= var10.height + 1;
        }

        if((var9 = this.getChild(RIGHT)) != null){
            var9.setSize(var9.geomentry().width, var5 - var4);
            var10 = var9.getPreferredSize();
            var9.setGeometry(var7 - var10.width, var4, var10.width, var5 - var4);
            var7 -= var10.width + 1;
        }

        if((var9 = this.getChild(LEFT)) != null){
            var9.setSize(var9.geomentry().width, var5 - var4);
            var10 = var9.getPreferredSize();
            var9.setGeometry(var6, var4, var10.width, var5 - var4);
            var6 += var10.width + 1;
        }

        if((var9 = this.getChild(CENTER)) != null){
            var9.setGeometry(var6, var4, var7 - var6, var5 - var4);
        }
    }
}