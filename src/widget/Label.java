package widget;

import java.awt.Color;
import java.util.logging.Logger;

/**
 * @author usu
 */
public class Label extends usu.widget.Label {

    /*
	 * Serial version UID
     */
    /**
     *
     */
    public Label() {
        super();
        // setForeground(new Color(90,90,90));
        setForeground(new Color(50, 50, 50));
        setFont(new java.awt.Font("Tahoma", 0, 11));

        setHorizontalAlignment(RIGHT);
        setVerticalAlignment(CENTER);
        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(CENTER);
    }

    private static final Logger LOG = Logger.getLogger(Label.class.getName());

}
