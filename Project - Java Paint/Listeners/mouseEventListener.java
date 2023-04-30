package Listeners;

import java.awt.event.MouseEvent;

public interface mouseEventListener {

    void onClick(MouseEvent e);
    void onPress(MouseEvent e);
    void onRelease(MouseEvent e);
    void onDrag(MouseEvent e);
    void onMove(MouseEvent e);
}
