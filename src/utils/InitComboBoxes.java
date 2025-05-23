package utils;

import javax.swing.*;

public class InitComboBoxes {
    private final JComboBox box;
    private final String[] boxData;

    public InitComboBoxes(JComboBox box, String... boxData) {
        this.box = box;
        this.boxData = boxData;

        try {
            for (String entry : boxData) {
                    this.box.addItem(entry);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getCause() + " at InitComboBoxes");
        }

    }


}
