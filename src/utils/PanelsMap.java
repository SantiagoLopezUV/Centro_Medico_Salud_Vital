package utils;

import view.*;
import view.director_templates.*;
import view.manager_templates.*;
import view.receptionist_templates.*;

import javax.swing.JPanel;

enum PanelsMap {

    WELCOME("Welcome", new InitPanel().getPanel()),
    LOGIN("Login", new LogInPanel().getPanel()),
    SIGNUP("SignUp", new SignUpPanel().getPanel()),

    //Manager
    M_MENU("M_Menu", new M_Menu().getPanel()),
    M_UPDATE_COSTS("M_Update_Costs", new M_UpdateCosts().getPanel()),
    M_DISCOUNTS_BENEFITS("M_Discounts_Benefits", new M_DiscountsBenefits().getPanel()),

    //Director
    D_MENU("Director Menu", new D_Menu().getPanel()),
    D_MED_ADDITIONAL_SERVICES_AND_SPECIALTIES( "Medic Additional Service and Specialties",
            new D_MedicAdditionalServsAndEspecialtiesPanel().getPanel()),
    D_ARRANGEMENT_PATIENT_INFO("Arrangement patient inform",
            new D_ArrangementPatientInfoPanel().getPanel()),
    D_MONTHLY_ADD_SERVICES_INCOME("Monthly additional services income",
            new D_MonthlyAddServIncome().getPanel()),
    D_STATISTICS("Statistics", new D_Menu_Statistics().getPanel()),
    D_STATISTICS_MEDIC_OCCUPATION_PERCENTAGE("Medic ocupation percentage",
            new D_Statistics_MedicOccupationPercentage().getPanel()),
    D_STATISTICS_ADD_SERV_PERCENTAGE("Additional service percentage",
            new D_Statistics_ServsUsePercentage().getPanel()),
    D_STATISTICS_ARRANGEMENT_ANNULLED_RATE("Arrangement annulled rate",
            new D_Statistics_ArrangementCancelled().getPanel()),

    //Receptionist
    R_MENU("R_Menu",new R_Menu().getPanel()),
    R_MENU_APPOINTMENT("R_Menu_Appointment", new R_Menu_Appointment().getPanel()),
    R_SCHEDULEAPPOINTMENT("R_ScheduleAppointment", new R_ScheduleAppointment().getPanel()),
    R_APPOINTMENTFULFILLMENT("R_AppointmentFulfillment", new R_AppointmentFulfillment().getPanel()),
    R_ADDSERVICE("R_AddService", new R_AddService().getPanel()),
    R_FULFILLMENT_STATUSAPPOINTMENT("R_Fulfillment_StatusAppointment", new R_Fulfillment_StatusAppointment().getPanel()),
    R_MENU_CONSULTATION("R_Menu_Consultation", new R_Menu_Consultation().getPanel()),
    R_COSTSERVICE("R_CostService", new R_CostService().getPanel()),
    R_DISCOUNTCOSTSERVICE("R_DiscountCostService", new R_DiscountCostService().getPanel()),
    R_MENU_INVOICING("R_Menu_Invoicing", new R_Menu_Invoicing().getPanel()),
    R_PENDING_PAYMENT("R_PendingPayment", new R_PendingPayment().getPanel()),
    R_GENERATEINVOICE("R_GenerateInvoice",new R_GenerateInvoice().getPanel())
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
