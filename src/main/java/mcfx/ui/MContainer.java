package mcfx.ui;

import java.awt.Dimension;

public interface MContainer{
    public void add(MComponent comp);
    public void add(MComponent comp, Object... flags);
    public void addComponent(MComponent comp);
    public Dimension getSize();
}