import utils.AccessPanel;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
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
        //AccessPanel.changeContent("Welcome");
        //AccessPanel.changeContent("M_Update_Costs");
        AccessPanel.changeContent("M_Discounts_Benefits");

    }
// icon credits
    //Designed by macrovector / Freepik
    //Designed by macrovector_official / Freepik"


}