package widget;

import java.awt.Color;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 * @author usu
 */
public class Table extends JTable {

    /*
	 * Serial version UID
     */
    /**
     *
     */
    public Table() {
        super();
        // setBackground(new Color(255,235,255));
        // setGridColor(new Color(245,170,245));
        // setForeground(new Color(90,90,90));
        setBackground(new Color(255, 255, 255));
        setGridColor(new Color(226, 231, 221));
        setForeground(new Color(50, 50, 50));
        setFont(new java.awt.Font("Tahoma", 0, 11));
        setRowHeight(22);
        setSelectionBackground(new Color(255, 255, 255));
        setSelectionForeground(new Color(255, 0, 0));
        // setAutoCreateRowSorter(true);
        getTableHeader().setForeground(new Color(50, 50, 50));
        getTableHeader().setBackground(new Color(255, 250, 250));
        getTableHeader().setBorder(javax.swing.BorderFactory.createLineBorder(new Color(255, 250, 250)));
        getTableHeader().setFont(new java.awt.Font("Tahoma", 0, 11));
    }

    private static final Logger LOG = Logger.getLogger(Table.class.getName());

}
