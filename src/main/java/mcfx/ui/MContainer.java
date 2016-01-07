package mcfx.ui;

import java.awt.Dimension;

public interface MContainer
extends MWidget{
    public void add(MComponent comp);
    public void add(MComponent comp, Object... flags);
    public void addComponent(MComponent comp);
    public Dimension getSize();
    public void layout();
    public int getChildrenCount();
    public MComponent getChild(int i);
}