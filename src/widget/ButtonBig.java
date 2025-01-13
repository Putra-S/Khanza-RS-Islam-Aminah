package widget;

import java.awt.Color;
import java.util.logging.Logger;
import usu.widget.glass.ButtonImageReflection;

/**
 * @author usu
 */
public class ButtonBig extends ButtonImageReflection {

    /*
	 * Serial version UID
     */
    /**
     *
     */
    public ButtonBig() {
        super();
        setForeground(new Color(50, 50, 50));
        setFont(new java.awt.Font("Tahoma", 0, 11));
    }

    private static final Logger LOG = Logger.getLogger(ButtonBig.class.getName());

}
