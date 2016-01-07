package mcfx.ui;

public interface MWidget{
    public MWidget parent();
    public void setParent(MWidget widget);
    public int getX();
    public int getY();
}
