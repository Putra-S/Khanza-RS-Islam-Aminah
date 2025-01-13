package widget;

import java.awt.Color;
import java.util.logging.Logger;
import usu.widget.glass.TextBoxGlass;

/**
 * @author usu
 */
public class TextBox extends TextBoxGlass {

    /**
     *
     */
    public TextBox() {
        super();
        setFont(new java.awt.Font("Tahoma", 0, 11));
        setSelectionColor(new Color(255, 252, 252));
        setSelectedTextColor(new Color(255, 0, 0));
        setForeground(new Color(50, 50, 50));
        setBackground(new Color(255, 255, 255));
        setHorizontalAlignment(LEFT);
        setSize(WIDTH, 23);
    }

    private static final Logger LOG = Logger.getLogger(TextBox.class.getName());

}
