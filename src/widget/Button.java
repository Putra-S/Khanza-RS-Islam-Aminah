package widget;

import java.awt.Color;
import java.awt.Insets;
import java.util.logging.Logger;

/**
 * @author usu
 */
public class Button extends usu.widget.ButtonGlass {

    /*
	 * Serial version UID
     */
    /**
     *
     */
    public Button() {
        super();
        setFont(new java.awt.Font("Tahoma", 1, 11));
        setForeground(new Color(50, 50, 50));
        setGlassColor(new Color(245, 240, 240));
        setMargin(new Insets(2, 7, 2, 7));
        setIconTextGap(4);
        setRoundRect(true);
    }

    private static final Logger LOG = Logger.getLogger(Button.class.getName());

}
