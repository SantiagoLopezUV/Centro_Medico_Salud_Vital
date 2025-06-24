package view;

import dao.ReceptionistDao;
import model.Arrangement;
import model.Patient;
import utils.*;

import javax.swing.*;
import java.sql.SQLException;

public class SignUpPanel implements AccessPanel {
    private JPanel SignUpPanelBG;
    private JLabel SignUp_SubTitleRecep;
    private JLabel SignUp_RecepcionistTitle;
    private JLabel SignUp_lblFirstName;
    private JLabel SignUp_lbl_ID;
    private JFormattedTextField SignUp_FirstNameField;
    private JFormattedTextField SignUp_IDField;
    private JButton SignUp_ReturnBttn;
    private JButton SignUp_RegisterBttn;
    private JRadioButton SignUp_radioButtonAgreement_NOT;
    private JRadioButton SignUp_radioButtonAgreement_YES;
    private JLabel SignUp_lblAgreement;
    private JLabel SignUp_lblGender;
    private JRadioButton SignUp_radioButtonSex_Female;
    private JRadioButton SignUp_radioButtonSex_Male;
    private JLabel SignUp_lblLastName;
    private JFormattedTextField SignUp_LastNameField;
    private JLabel SignUp_lblPhone;
    private JLabel SignUp_lblAddress;
    private JLabel SignUp_lblCity;
    private JFormattedTextField SignUp_PhoneField;
    private JLabel SignUp_lblEmail;
    private JFormattedTextField SignUp_EmailField;
    private JFormattedTextField SignUp_AddressField;
    private JFormattedTextField SignUp_CityField;
    private JComboBox SignUp_CompanyComBox;
    private JLabel SignUp_lblCompany;

    private static final String ADDRESS_FIELD_PLACE_HOLDER = "Recuerda el formato de dirección";
    private static final String EMAIL_FIELD_PLACE_HOLDER = "ejemplo.correo@ejemplo.com";

    public SignUpPanel() {
        SignUp_radioButtonAgreement_YES.addActionListener(e -> {
            SignUp_lblCompany.setVisible(true);
            SignUp_CompanyComBox.setVisible(true);
        });
        SignUp_radioButtonAgreement_NOT.addActionListener(e -> {
            SignUp_lblCompany.setVisible(false);
            SignUp_CompanyComBox.setVisible(false);
        });
        this.SignUp_FirstNameField.addKeyListener(new KeyListenerForLetters(this.SignUp_FirstNameField));
        this.SignUp_LastNameField.addKeyListener(new KeyListenerForLetters(this.SignUp_LastNameField));
        this.SignUp_IDField.addKeyListener(new KeyListenerParaInt());
        this.SignUp_AddressField.addKeyListener(new KeyListenerCustomLimits("[a-zA-Z0-9 áéíóú#,-]"));
        this.SignUp_AddressField.addFocusListener(new PlaceHoldersAction(this.SignUp_AddressField, ADDRESS_FIELD_PLACE_HOLDER));

        this.SignUp_PhoneField.addKeyListener(new KeyListenerParaInt());
        this.SignUp_EmailField.addKeyListener(new KeyListenerCustomLimits("[a-zA-z0-9.-_@]"));
        this.SignUp_EmailField.addFocusListener(new PlaceHoldersAction(this.SignUp_EmailField, EMAIL_FIELD_PLACE_HOLDER));
        this.SignUp_CityField.addKeyListener(new KeyListenerForLetters(this.SignUp_CityField));

        try {
            ReceptionistDao receptionistDao = new ReceptionistDao();
            InitComboBoxes<Arrangement> iCombo = new InitComboBoxes<>();
            iCombo.InitComboBoxesWithArrayList(this.SignUp_CompanyComBox, receptionistDao.getArrangements());

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Un error se ha presentado al tratar de consultar la base de datos," +
                            "contacta al administrador de la misma",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
        }


        this.SignUp_RegisterBttn.addActionListener(e ->{
            if(checkSingUpForm()){
                destroyData();
                AccessPanel.changeContent("R_ScheduleAppointment");
            }
        });
        this.SignUp_ReturnBttn.addActionListener(e -> {
            destroyData();
            AccessPanel.changeContent("R_ScheduleAppointment");
        });

    }

    private boolean checkSingUpForm() {
        StringBuilder stNotValidDataFormatFieldsFind = new StringBuilder("Se han encontrado el siguiente error:\n");
        boolean canPass = false;
        if(this.SignUp_FirstNameField.getText().isEmpty()
                || this.SignUp_FirstNameField.getText().length() > 50 ){
            stNotValidDataFormatFieldsFind.append("falta nombre o excede los 50 caracteres\n");
        }else if(this.SignUp_LastNameField.getText().isEmpty()
                || this.SignUp_LastNameField.getText().length() > 50 ){
            stNotValidDataFormatFieldsFind.append("falta apellido o excede los 50 caracteres\n");
        }else if(this.SignUp_IDField.getText().isEmpty()
                || this.SignUp_IDField.getText().length() < 8){
            stNotValidDataFormatFieldsFind.append("falta Identificación o es menor a 8 digitos\n");
        }else if(this.SignUp_AddressField.getText().equals(ADDRESS_FIELD_PLACE_HOLDER)
                || !this.SignUp_AddressField.getText()
                .matches("^(Av|Cl|Cra|Mz) [a-zA-Z0-9 áéíóú]+ # [a-zA-Z0-9 áéíóú]+ - [0-9]{1,4}$")){
            stNotValidDataFormatFieldsFind.append("""
                    formato de dirección no válido debe iniciar con la vía\s
                        Av, Mz, Cl o Cra, seguido de su nomenclatura alfanumerica\s
                        luego # con la nomenclatura de la vía que intersecciona\s
                        el - y el número de la casa (máximo 4 digitos).\s
                        Ej: Av 23 Oeste # 2 - 33
                    """);
        }else if((this.SignUp_radioButtonSex_Female.isSelected() == this.SignUp_radioButtonSex_Male.isSelected())){
            stNotValidDataFormatFieldsFind.append("- falta seleccionar sexo\n");
        }else if(this.SignUp_EmailField.getText().equals(EMAIL_FIELD_PLACE_HOLDER)
                || !this.SignUp_EmailField.getText()
                    .matches("^[a-z0-9-_]([.]?[a-z0-9-_]+[.]?)+@(outlook|hotmail|gmail|yahoo).com$")){
            stNotValidDataFormatFieldsFind.append("""
                    formato de email no válido, solo minúsculas cuentas de\s
                        gmail, hotmail, outlook o yahoo, terminadas en .com\s
                    """);
        }else if(this.SignUp_CityField.getText().isEmpty()){
            stNotValidDataFormatFieldsFind.append("Campo de ciudad vacío");
        }else{
            canPass = true;
        }

        if(!canPass){

            JOptionPane.showMessageDialog(null,
                            stNotValidDataFormatFieldsFind.toString(),
                            "Formato no válido",
                            JOptionPane.INFORMATION_MESSAGE);
            return false;
        }


        int confirm = JOptionPane.showConfirmDialog(this.SignUpPanelBG,
                "¿Esta seguro que todos los datos del paciente\n" +
                         "son correctos?");
        if (confirm == JOptionPane.NO_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
            return false;
        }



        try{
            return insertToBDAction() ;

        }catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Un error se ha presentado al tratar de consultar la base de datos,\n" +
                            "contacta al administrador de la misma.\n" +
                            "O verifica que el documento de identidad sea válido.",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
            destroyData();
            return false;
        }catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Verifica que el documento de identidad sea válido",
                    "Error al consultar la base de datos",
                    JOptionPane.ERROR_MESSAGE);
            destroyData();
            return false;
        }

    }


    private boolean insertToBDAction() throws SQLException {
        ReceptionistDao receptionistDao = new ReceptionistDao();
        Patient newPatient = new Patient(Long.parseLong(this.SignUp_IDField.getText()),
                this.SignUp_FirstNameField.getText(),
                this.SignUp_LastNameField.getText(),
                this.SignUp_PhoneField.getText(),
                this.SignUp_EmailField.getText(),
                this.SignUp_AddressField.getText() + ", " + this.SignUp_CityField.getText(),
                ((this.SignUp_radioButtonSex_Female.isSelected())? SexType.FEMALE: SexType.MALE),
                (this.SignUp_radioButtonAgreement_YES.isSelected()) ? (Arrangement) this.SignUp_CompanyComBox.getSelectedItem()
                        : null);

        return receptionistDao.insertNewPatient(newPatient);

    }


    private void destroyData() {
        this.SignUp_FirstNameField.setText("");
        this.SignUp_LastNameField.setText("");
        this.SignUp_IDField.setText("");
        this.SignUp_AddressField.setText(ADDRESS_FIELD_PLACE_HOLDER);
        this.SignUp_radioButtonAgreement_NOT.setSelected(false);
        this.SignUp_radioButtonAgreement_YES.setSelected(true);
        this.SignUp_radioButtonSex_Male.setSelected(false);
        this.SignUp_radioButtonSex_Female.setSelected(false);
        this.SignUp_PhoneField.setText("");
        this.SignUp_EmailField.setText(EMAIL_FIELD_PLACE_HOLDER);
        this.SignUp_CityField.setText("");
        this.SignUp_CompanyComBox.setSelectedIndex(0);
    }

    @Override
    public JPanel getPanel() {
        return SignUpPanelBG;
    }
}
