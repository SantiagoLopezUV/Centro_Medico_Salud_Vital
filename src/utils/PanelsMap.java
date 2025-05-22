package utils;

import view.*;

import javax.swing.JPanel;

enum PanelsMap {

    WELCOME("Welcome", new InitPanel().getPanel()),
    LOGIN("Login", new LogInPanel().getPanel()),
    R_MENU("R_Menu",new R_Menu().getPanel()),
    R_MENU_APPOINTMENT("R_Menu_Appointment", new R_Menu_Appointment().getPanel()),
    R_SCHEDULEAPPOINTMENT("R_ScheduleAppointment", new R_ScheduleAppointment().getPanel()),
    R_APPOINTMENTFULFILLMENT("R_AppointmentFulfillment", new R_AppointmentFulfillment().getPanel()),
    R_MENU_CONSULTATION("R_Menu_Consultation", new R_Menu_Consultation().getPanel()),
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
