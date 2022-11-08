package uia.com.agenda.agendafxjson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AgendaFXController implements Initializable {

    @FXML private TableView<ContactoDTO> table;
    @FXML private TableColumn<ContactoDTO, String> tipo;
    @FXML private TableColumn<ContactoDTO, String> name;
    @FXML private TableColumn<ContactoDTO, String> fechaRecordatorio;
    @FXML private TableColumn<ContactoDTO, String> fecha;
    @FXML
    private Label tipoLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label fechaLabel;
    @FXML
    private Label fechaRecordatorioLabel;

    // Reference to the main application.
    private AgendaFXApplication mainApp;

    public ObservableList<ContactoDTO> list;

    public ObservableList<RecordatorioOld> listRecordatorioOlds = FXCollections.observableArrayList(
            new RecordatorioOld("1", "Recodartorio 1", "1-11-2020", "1-10-2020"),
            new RecordatorioOld("2",  "Recodartorio 2", "1-12-2021", "1-10-2020"),
            new RecordatorioOld("3",  "Recodartorio 3", "3-10-2022", "1-10-2020"),
            new RecordatorioOld("4",  "Recodartorio 4", "3-10-2022", "1-10-2020")
    );

    @FXML
    private TableColumn fechaRecordatorioCol;
    @FXML
    private Label fechaLabelRecordatorio;
    @FXML
    private Button nuevoBoton;
    @FXML
    private TableColumn fechaRecordatorioRecordatorioCol;
    @FXML
    private Button nuevoBotonRecordatorio;
    @FXML
    private TableView tableRecordatorio;
    @FXML
    private TableColumn nameRecordatorioCol;
    @FXML
    private Label nameLabelRecordatorio;
    @FXML
    private Label fechaRecordatorioLabelRecordatorio;
    @FXML
    private TableColumn tipoRecordatorioCol;
    @FXML
    private Label tipoLabelRecordatorio;
    private Agenda miAgenda;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tipo.setCellValueFactory(new PropertyValueFactory<ContactoDTO, String>("tipo"));
        name.setCellValueFactory(new PropertyValueFactory<ContactoDTO, String>("name"));
        fechaRecordatorio.setCellValueFactory(new PropertyValueFactory<ContactoDTO, String>("fechaRecordatorio"));
        fecha.setCellValueFactory(new PropertyValueFactory<ContactoDTO, String>("fecha"));
        table.setItems(list);

        // Clear person details.
        showContactoDetails(null);

        // Listen for selection changes and show the person details when changed.
        table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showContactoDetails((ContactoDTO) newValue));

        tipoRecordatorioCol.setCellValueFactory(new PropertyValueFactory<RecordatorioOld, String>("tipo"));
        nameRecordatorioCol.setCellValueFactory(new PropertyValueFactory<RecordatorioOld, String>("name"));
        fechaRecordatorioRecordatorioCol.setCellValueFactory(new PropertyValueFactory<RecordatorioOld, String>("fechaRecordatorio"));
        fechaRecordatorioCol.setCellValueFactory(new PropertyValueFactory<RecordatorioOld, String>("fecha"));
        tableRecordatorio.setItems(listRecordatorioOlds);

        // Clear person details.
        showRecordatorioDetails(null);

        // Listen for selection changes and show the person details when changed.
        tableRecordatorio.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue2) -> showRecordatorioDetails((RecordatorioOld) newValue2));

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(AgendaFXApplication mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        table.setItems(list);
        tableRecordatorio.setItems(listRecordatorioOlds);
    }




    private void showContactoDetails(ContactoDTO contactoDTO) {
        if (contactoDTO != null) {
            // Fill the labels with info from the contactoDTO object.
            tipoLabel.setText(contactoDTO.getTipo());
            nameLabel.setText(contactoDTO.getname());
            fechaRecordatorioLabel.setText(contactoDTO.getFechaRecordatorio());
            fechaLabel.setText(contactoDTO.getFecha());
        } else {
            // ContactoDTO is null, remove all the text.
            tipoLabel.setText("");
            nameLabel.setText("");
            fechaRecordatorioLabel.setText("");
            fechaLabel.setText("");
        }
    }




    private void showRecordatorioDetails(RecordatorioOld recordatorioOld) {
        if (recordatorioOld != null) {
            // Fill the labels with info from the recordatorioOld object.
            tipoLabelRecordatorio.setText(recordatorioOld.getTipo());
            nameLabelRecordatorio.setText(recordatorioOld.getname());
            fechaRecordatorioLabelRecordatorio.setText(recordatorioOld.getFechaRecordatorio());
            fechaLabelRecordatorio.setText(recordatorioOld.getFecha());
        } else {
            // RecordatorioOld is null, remove all the text.
            tipoLabelRecordatorio.setText("");
            nameLabelRecordatorio.setText("");
            fechaRecordatorioLabelRecordatorio.setText("");
            fechaLabelRecordatorio.setText("");
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewContacto() throws IOException {
        ContactoDTO tempContactoDTO = new ContactoDTO();
        boolean okClicked = mainApp.showContactoEdicionDialogo(tempContactoDTO);
        if (okClicked) {
            list.add(tempContactoDTO);
            miAgenda.getItems().add(tempContactoDTO);
            this.serializa();
        }
    }

    private void serializa() throws IOException {
        this.miAgenda.serializa();
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @Deprecated
    private void handleEditContacto() {
        ContactoDTO selectedContactoDTO = table.getSelectionModel().getSelectedItem();
        if (selectedContactoDTO != null) {
            boolean okClicked = mainApp.showContactoEdicionDialogo(selectedContactoDTO);
            if (okClicked) {
                showContactoDetails(selectedContactoDTO);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No ContactoDTO Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }


    @FXML
    public void handleNewRecordatorio(ActionEvent actionEvent) {
        RecordatorioOld tempRecordatorioOld = new RecordatorioOld();
        boolean okClicked = mainApp.showRecordatorioEdicionDialogo(tempRecordatorioOld);
        if (okClicked) {
            listRecordatorioOlds.add(tempRecordatorioOld);
        }
    }

    public void setAgenda(Agenda agenda)
    {
        miAgenda = agenda;
        list = FXCollections.observableArrayList();

        for(int i=0; i<miAgenda.getItems().size(); i++)
        {
            list.add((ContactoDTO) new ContactoDTO(miAgenda.getItems().get(i)));
        }
    }
}