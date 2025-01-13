package widget;

import java.awt.Color;
import java.awt.Font;
import java.util.logging.Logger;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import usu.util.StringUtil;
import usu.widget.glass.FormatedTextBox;
import usu.widget.text.DefaultDocument;

/**
 * @author usu
 */
public class FormatterBox extends FormatedTextBox {

    /*
	 * Serial version UID
     */
    public FormatterBox() {
        super();
        setFont(getFont().deriveFont(Font.BOLD));
        setForeground(Color.WHITE);
        setSelectionColor(Color.BLUE.brighter());
        setCaretColor(Color.white);
        setHorizontalAlignment(LEFT);
        setDocument(new DefaultDocument() {

            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if (StringUtil.containQuote(str)) {
                    return;
                }
                super.insertString(offs, str, a);
            }
        });
    }

    private static final Logger LOG = Logger.getLogger(FormatterBox.class.getName());

}
