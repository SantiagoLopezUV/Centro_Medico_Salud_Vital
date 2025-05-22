package utils;

import view.*;

import javax.swing.JPanel;

enum PanelsMap {

    WELCOME("Welcome", new InitPanel().getPanel()),
    LOGIN("Login", new LogInPanel().getPanel()),

    //Director
    D_MENU("Director Menu", new D_MenuPanel().getPanel()),
    D_MED_ADDITIONAL_SERVICES_AND_SPECIALTIES( "Medic Additional Service and Specialties",
            new D_MedicAdditionalServsAndEspecialtiesPanel().getPanel()),
    D_ARRANGEMENT_PATIENT_INFO("Arrangement patent inform", new D_ArrangementPatientInfoPanel().getPanel()),
    D_MONTHLY_ADD_SERVS_INCOME("Monthly additional services income", new D_MonthlyAddServIncome().getPanel())
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
