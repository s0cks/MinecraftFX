package mcfx.ui.event;

public final class ActionEvent{
    public final int x;
    public final int y;
    public final int button;
    public final boolean released;

    public ActionEvent(int x, int y, int button, boolean released) {
        this.x = x;
        this.y = y;
        this.button = button;
        this.released = released;
    }
}