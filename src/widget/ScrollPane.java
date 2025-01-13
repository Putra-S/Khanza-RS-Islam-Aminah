package widget;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Objects;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.LineBorder;
import usu.widget.glass.ViewPortGlass;

/**
 * @author usu
 */
public class ScrollPane extends JScrollPane {

    /**
     *
     */
    public ScrollPane() {
        super();
        setViewport(new ViewPortGlass());
        setOpaque(false);
        // setBorder(new LineBorder(new Color(235,140,235)));
        // setBackground(new Color(255,235,255));
        setBorder(new LineBorder(new Color(239, 244, 234)));
        setBackground(new Color(255, 255, 255));
//		getVerticalScrollBar().setUI(new ScrollBarWin11UI());
//        getHorizontalScrollBar().setUI(new ScrollBarWin11UI());
//        setLayout(new ScrollLayout());

    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    @Override
    public void updateUI() {
        super.updateUI();
        EventQueue.invokeLater(() -> {
            setComponentZOrder(getVerticalScrollBar(), 0);
            setComponentZOrder(getHorizontalScrollBar(), 1);
            setComponentZOrder(getViewport(), 2);
            getVerticalScrollBar().setOpaque(false);
            getHorizontalScrollBar().setOpaque(false);
        });
    }

    private class ScrollLayout extends ScrollPaneLayout {

        @Override
        public void layoutContainer(Container parent) {
            super.layoutContainer(parent);
            if (parent instanceof JScrollPane) {
                JScrollPane scroll = (JScrollPane) parent;
                Rectangle rec = scroll.getViewport().getBounds();
                Insets insets = parent.getInsets();
                int rhHeight = 0;
                if (scroll.getColumnHeader() != null) {
                    Rectangle rh = scroll.getColumnHeader().getBounds();
                    rhHeight = rh.height;
                }
                rec.width = scroll.getBounds().width - (insets.left + insets.right);
                rec.height = scroll.getBounds().height - (insets.top + insets.bottom) - rhHeight;
                if (Objects.nonNull(viewport)) {
                    viewport.setBounds(rec);
                }
                if (!Objects.isNull(hsb)) {
                    Rectangle hrc = hsb.getBounds();
                    hrc.width = rec.width;
                    hsb.setBounds(hrc);
                }
            }
        }
    }

    private static final Logger LOG = Logger.getLogger(ScrollPane.class.getName());

}
