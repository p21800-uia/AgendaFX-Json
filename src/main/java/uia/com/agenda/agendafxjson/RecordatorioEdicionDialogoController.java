package uia.com.agenda.agendafxjson;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RecordatorioEdicionDialogoController {
    @FXML
    public TextField tipoRecordatorioField;
    @FXML
    public TextField nameRecordatorioField;
    @FXML
    public TextField fechaRecordatorioField;
    @FXML
    public TextField fechaRecordatorioRecordatorioField;
    @FXML
    public Button okRecordatorioBoton;

    private Stage dialogStage;
    private RecordatorioOld recordatorioOld;
    private boolean okClicked = false;

    @FXML
    public void handleRecordatorioOk(ActionEvent actionEvent) {
        if (isInputValid()) {

            this.recordatorioOld.setTipo(tipoRecordatorioField.getText());
            this.recordatorioOld.setname(nameRecordatorioField.getText());
            this.recordatorioOld.setFecha(fechaRecordatorioField.getText());
            this.recordatorioOld.setFechaRecordatorio(fechaRecordatorioRecordatorioField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }


    private boolean isInputValid() {
        String errorMessage = "";

        if (tipoRecordatorioField.getText() == null || tipoRecordatorioField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (nameRecordatorioField.getText() == null || nameRecordatorioField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (fechaRecordatorioField.getText() == null || fechaRecordatorioField.getText().length() == 0) {
            errorMessage += "No valid fecha!\n";
        }

        if (fechaRecordatorioRecordatorioField.getText() == null || fechaRecordatorioRecordatorioField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }



    /**
     * Sets the recordatorioOld to be edited in the dialog.
     *
     * @param recordatorioOld
     */
    public void setRecordatorio(RecordatorioOld recordatorioOld) {
        this.recordatorioOld = recordatorioOld;

        tipoRecordatorioField.setText(recordatorioOld.getTipo());
        nameRecordatorioField.setText(recordatorioOld.getname());
        fechaRecordatorioField.setText(recordatorioOld.getFecha());
        fechaRecordatorioRecordatorioField.setText(recordatorioOld.getFechaRecordatorio());
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }
}
