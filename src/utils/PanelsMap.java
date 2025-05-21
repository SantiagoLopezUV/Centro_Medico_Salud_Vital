package utils;

import view.InitPanel;
import view.LogInPanel;
import view.M_Menu;

import javax.swing.JPanel;

enum PanelsMap {

    WELCOME("Welcome", new InitPanel().getPanel()),
    LOGIN("Login", new LogInPanel().getPanel()),
    M_MENU("M_Menu", new M_Menu().getPanel()),
    M_UPDATE_COSTS("M_UpdateCosts", new JPanel()),
    M_DISCOUNTS_BENEFITS("M_DiscountsBenefits", new JPanel()),

    ;


    private final String name;
    private final JPanel panel;

    PanelsMap(String name, JPanel panel) {
        this.name = name;
        this.panel = panel;
    }

    JPanel getPanelToRender(){
        return panel;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
