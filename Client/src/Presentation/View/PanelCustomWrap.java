package Presentation.View;

import javax.swing.*;
import java.awt.*;

/**
 * The type Panel custom wrap.
 */
public class PanelCustomWrap extends JPanel implements Scrollable {
    /**
     * Instantiates a new Panel custom wrap.
     *
     * @param layoutManager the layout manager
     */
    public PanelCustomWrap(LayoutManager layoutManager) {
        super(layoutManager);
    }

    /**
     * Instantiates a new Panel custom wrap.
     */
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
