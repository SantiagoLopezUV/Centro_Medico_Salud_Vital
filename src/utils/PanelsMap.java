package utils;

import view.InitPanel;
import view.LogInPanel;
import view.R_Menu_Citas;

import javax.swing.JPanel;

enum PanelsMap {

    WELCOME("Welcome", new InitPanel().getPanel()),
    LOGIN("Login", new LogInPanel().getPanel()),
    R_MENU_CITAS("R_Menu_Citas", new R_Menu_Citas().getPanel()),
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
