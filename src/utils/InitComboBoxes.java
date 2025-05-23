package utils;

import javax.swing.*;

public class InitComboBoxes {
    private final JComboBox box;

    public InitComboBoxes(JComboBox box, String... boxData) {
        this.box = box;

        try {
            for (String entry : boxData) {
                    this.box.addItem(entry);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getCause() + " at InitComboBoxes");
        }

    }


}
