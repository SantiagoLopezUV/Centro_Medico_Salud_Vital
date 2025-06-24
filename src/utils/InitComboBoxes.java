package utils;

import javax.swing.*;
import java.util.List;

public class InitComboBoxes <E>{

    public static void InitComboBoxes(JComboBox box, String... boxData) {

        try {
            for (String entry : boxData) {
                    box.addItem(entry);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getCause() + " at InitComboBoxes");
        }

    }

    public void InitComboBoxesWithArrayList(JComboBox box, List<E> boxData) {
        try {
            for (E entry : boxData) {
                box.addItem(entry);
            }
        } catch (NullPointerException e) {
            System.out.println(e.getCause() + " at InitComboBoxes");
        }

    }
}
