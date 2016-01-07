package mcfx.ui;

import mcfx.ui.event.ActionEvent;
import mcfx.ui.listener.ActionListener;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

public abstract class MComponent
implements MWidget{
    private final List<ActionListener> listeners = new LinkedList<>();

    protected MWidget parent;
    protected Rectangle bounds = new Rectangle();
    protected Dimension preferredSize = new Dimension(128, 16);
    protected float zLevel = 0;
    protected boolean focused = false;

    protected MComponent(){
        this.bounds.setBounds(0, 0, this.preferredSize.width, this.preferredSize.height);
    }

    public final void addActionListener(ActionListener listener){
        this.listeners.add(listener);
    }

    public void onAction(ActionEvent e){
        for(ActionListener listener : this.listeners){
            listener.on(e);
        }
    }

    public final void setZLevel(float z){
        this.zLevel = z;
    }

    public final float getZLevel(){
        return this.zLevel;
    }

    public final void focus(){
        this.focused = true;
    }

    public final void unfocus(){
        this.focused = false;
    }

    public final boolean focused(){
        return this.focused;
    }

    public final Dimension getPreferredSize(){
        return this.preferredSize;
    }

    public final void setPreferredSize(int width, int height){
        this.preferredSize.setSize(width, height);
    }

    public final void setGeometry(int x, int y, int width, int height){
        this.bounds.setBounds(x, y, width, height);
    }

    public final Rectangle geomentry(){
        return this.bounds;
    }

    public final void setSize(int width, int height){
        this.setGeometry(this.geomentry().x, this.geomentry().y, width, height);
    }

    public final Dimension getSize(){
        return new Dimension(this.geomentry().width, this.geomentry().height);
    }

    public void setPosition(int x, int y){
        this.setGeometry(x, y, this.geomentry().width, this.geomentry().height);
    }

    @Override
    public final int getX(){
        return this.bounds.x;
    }

    @Override
    public final int getY(){
        return this.bounds.y;
    }

    @Override
    public MWidget parent(){
        return this.parent;
    }

    @Override
    public void setParent(MWidget widget){
        this.parent = widget;
    }

    public abstract void paint(RenderContext ctx);
}