package utils;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceHoldersAction implements FocusListener {
    private final JTextField inField;
    private final String placeholderMsg;

    public PlaceHoldersAction(JTextField inField, String placeholderMsg) {
        this.inField = inField;
        this.placeholderMsg = placeholderMsg;
        this.inField.setText(placeholderMsg);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (inField.getText().equals(placeholderMsg)) {
            inField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (inField.getText().isEmpty()) {
            inField.setText(placeholderMsg);
        }
    }
}
