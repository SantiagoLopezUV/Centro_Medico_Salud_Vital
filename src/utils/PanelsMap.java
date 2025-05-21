package utils;

import view.InitPanel;
import view.LogInPanel;
import view.M_Menu;
import view.M_UpdateCosts;

import javax.swing.JPanel;

enum PanelsMap {

    WELCOME("Welcome", new InitPanel().getPanel()),
    LOGIN("Login", new LogInPanel().getPanel()),
    M_MENU("M_Menu", new M_Menu().getPanel()),
    M_UPDATE_COSTS("M_Update_Costs", new M_UpdateCosts().getPanel()),
    //M_DISCOUNTS_BENEFITS("M_Discounts_Benefits", new ().getPanel()),

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
