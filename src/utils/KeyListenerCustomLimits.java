package utils;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyListenerCustomLimits implements KeyListener {
    private final Pattern regexPattern;

    public KeyListenerCustomLimits(String regex) {
        this.regexPattern = Pattern.compile(regex);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (!validarCaracter(c)) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private boolean validarCaracter(char c) {
        Matcher matcher = regexPattern.matcher(String.valueOf(c));
        return matcher.matches();
    }
}
