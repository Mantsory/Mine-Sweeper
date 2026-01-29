/*
 * Author: Mantsory
 * Version updated: 2.1.6
 */

package LauncherGUI;

import javax.swing.*;
import java.awt.*;

public class TextFields {
    public static JTextField[] initCustomDifFields(JPanel panel) {
        JTextField[] customDifFields = new JTextField[3];
        customDifFields[0] = initColsField(); panel.add(customDifFields[0]);
        customDifFields[1] = initRowsField(); panel.add(customDifFields[1]);
        customDifFields[2] = initMinesField(); panel.add(customDifFields[2]);
        return customDifFields;
    }

    public static void setFieldTips(JTextField[] fields, boolean active) {

        if (active) {
            fields[0].setToolTipText("Number of columns. 2-50");
            fields[1].setToolTipText("Number of rows. 2-50");
            fields[2].setToolTipText("Number of mines. 1-2499");
        } else {
            for (JTextField field : fields) {
                field.setToolTipText("Only for custom difficulty.");
            }
        }
    }

    private static JTextField initColsField() {
        JTextField field = createField();
        field.setToolTipText("Only for custom difficulty.");
        field.setEnabled(false);
        return field;
    }

    private static JTextField initRowsField() {
        JTextField field = createField();
        field.setToolTipText("Only for custom difficulty.");
        field.setEnabled(false);
        return field;
    }

    private static JTextField initMinesField() {
        JTextField field = createField();
        field.setToolTipText("Only for custom difficulty.");
        field.setEnabled(false);
        return field;
    }

    private static JTextField createField() {
        JTextField field = new JTextField();
        field.setPreferredSize(new Dimension(100, 40));
        field.setFont(new Font(null, Font.PLAIN, 24));
        return field;
    }
}