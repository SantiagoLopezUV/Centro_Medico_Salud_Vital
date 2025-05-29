package utils;

import view.*;
import view.director_templates.*;
import view.manager_templates.M_DiscountsBenefits;
import view.manager_templates.M_Menu;
import view.manager_templates.M_UpdateCosts;

import javax.swing.JPanel;

enum PanelsMap {

    WELCOME("Welcome", new InitPanel().getPanel()),
    LOGIN("Login", new LogInPanel().getPanel()),

    //Manager
    M_MENU("M_Menu", new M_Menu().getPanel()),
    M_UPDATE_COSTS("M_Update_Costs", new M_UpdateCosts().getPanel()),
    M_DISCOUNTS_BENEFITS("M_Discounts_Benefits", new M_DiscountsBenefits().getPanel()),

    //Director
    D_MENU("Director Menu", new D_MenuPanel().getPanel()),
    D_MED_ADDITIONAL_SERVICES_AND_SPECIALTIES( "Medic Additional Service and Specialties",
            new D_MedicAdditionalServsAndEspecialtiesPanel().getPanel()),
    D_ARRANGEMENT_PATIENT_INFO("Arrangement patient inform",
            new D_ArrangementPatientInfoPanel().getPanel()),
    D_MONTHLY_ADD_SERVICES_INCOME("Monthly additional services income",
            new D_MonthlyAddServIncome().getPanel()),
    D_STATISTICS("Statistics", new D_Statistics().getPanel()),
    D_STATISTICS_MEDIC_OCCUPATION_PERCENTAGE("Medic ocupation percentage",
            new D_Statistics_MedicOccupationPercentage().getPanel()),
    D_STATISTICS_ADD_SERV_PERCENTAGE("Additional service percentage",
            new D_Statistics_AddServsUsePercentage().getPanel()),
    D_STATISTICS_ARRANGEMENT_ANNULLED_RATE("Arrangement annulled rate",
            new D_Statistics_ArrangementAnnulledRate().getPanel())
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
