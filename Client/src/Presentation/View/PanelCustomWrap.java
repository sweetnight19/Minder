package Presentation.View;

import javax.swing.*;
import java.awt.*;

public class PanelCustomWrap extends JPanel implements Scrollable {
    public PanelCustomWrap(LayoutManager layoutManager) {
        super(layoutManager);
    }

    public PanelCustomWrap(){}

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 10;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 100;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
