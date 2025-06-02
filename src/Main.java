import utils.AccessPanel;
import utils.db.ConnectionSource;
import utils.security.HashGen;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

public class Main{

    public static void main(String[] args) {
        Enumeration<Object> UIkeys = UIManager.getDefaults().keys();
        FontUIResource f = new FontUIResource(new Font("Arial", Font.PLAIN, 14));
        while (UIkeys.hasMoreElements()) {
            Object key = UIkeys.nextElement();
            Object value = UIManager.get (key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put (key, f);
        }
        AccessPanel.changeContent("welcome");
    }
// icon credits
    //Designed by macrovector / Freepik
    //Designed by macrovector_official / Freepik


}