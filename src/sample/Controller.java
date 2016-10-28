package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class Controller {

    @FXML
    private MenuItem mbCopy;
    @FXML
    private MenuItem mbCut;
    @FXML
    private TextArea textArea;
    @FXML
    private Button tbCopy;
    @FXML
    private Button tbCut;
    @FXML
    private Button tbPaste;


    public void initialize(){


        tbCut.setGraphic( new ImageView("cut.png") );
        tbPaste.setGraphic( new ImageView("paste.png"));
        tbCopy.setGraphic( new ImageView("copia.png") );

    }


    public void exitAct(){
        Platform.exit();
    }

    public void copyAct(){
        textArea.copy();
    }

    public void cutAct(){
        textArea.cut();
    }

    public void pasteAct() {
        textArea.paste();
    }

    public void undoAct() {
        textArea.undo();
    }

    public void setArial() {
        textArea.setFont( new Font("Arial", textArea.getFont().getSize() ) );
    }

    public void setVerdana(){
        textArea.setFont( new Font("Liberation Sans", textArea.getFont().getSize() ) );
    }

    public void setFreeMono(){
        textArea.setFont( new Font("FreeMono" , textArea.getFont().getSize() ) );
    }

    public void setSize13(){
        textArea.setFont( new Font(textArea.getFont().getName(), 13 ) );
    }

    public void setSize16(){
        textArea.setFont( new Font(textArea.getFont().getName(), 16 ) );
    }

    public void setSize20(){
        textArea.setFont( new Font(textArea.getFont().getName(), 20 ));
    }

    public void deshabilitarMenu(){

        if( textArea.getSelectedText().length() == 0 ){
            mbCopy.setDisable(true);
            mbCut.setDisable(true);
        }else{
            mbCopy.setDisable(false);
            mbCut.setDisable( false );
        }
    }


    public void sobreEditor() {

        Alert alert = new Alert( Alert.AlertType.INFORMATION );
        alert.isResizable();
        alert.setTitle("Informacio");
        alert.setHeaderText(null);
        alert.setContentText("Editor de text.\nOpcions de la barra de menu:\nFitxer\n\tSortir:\n\t\tSurt de l'aplicacio" +
                "\nEdició\n\tCopiar:\n\t\tCopia el text seleccionat\n\tTallar:\n\t\tRetalla el text seleccionat" +
                "\n\tEnganxar:\n\t\tEnganxa el text copiat o retallat\n\tDesfer:\n\t\tDesfa l'accio realitzada anteriorment" +
                "\nOpcions:\n\tFont\n\t\tCanvia la font de tot el text\n\tTamany\n\t\tCanvia el tamany de la lletra del text" +
                "\nCreat per Fabian Puig. 2n DAM IES Poblenou");


        alert.showAndWait();
    }

    public void openAct(){
        Stage stage = (Stage) textArea.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Obrir fitxer");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("texto","*.txt")
        );
        File file = fc.showOpenDialog( stage );

        String line = "";
        String text = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                text = text + line + "\n";
            }
            br.close();

        }catch (IOException e) {
            e.printStackTrace();
        }

        textArea.setText( text );

        stage.setTitle( file.getName() );

    }

    public void saveAct() {

        Stage stage = (Stage) textArea.getScene().getWindow();
        FileChooser fc = new FileChooser();
        fc.setTitle("Guardar fitxer");

        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("texto", "*.txt")
        );

        File file = fc.showSaveDialog( stage );

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter( file ) );
            bw.write( textArea.getText() );
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
