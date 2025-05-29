package utils;

import view.*;

import javax.swing.JPanel;

enum PanelsMap {

    WELCOME("Welcome", new InitPanel().getPanel()),
    LOGINPANEL("Login", new R_LoginPanel().getPanel()),
    LOGIN("Login", new LogInPanel().getPanel()),
    SIGNUP("SignUp", new SignUpPanel().getPanel()),
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
