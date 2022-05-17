package fr.ensicaen.genielogiciel.mvp.view;

import fr.ensicaen.genielogiciel.mvp.LoginMain;
import fr.ensicaen.genielogiciel.mvp.presenter.ChoicePresenter;
import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ChoiceView {
    private ChoicePresenter _presenter;
    private Stage _stage;
    @FXML
    private Button _playButton;
    @FXML
    private CheckBox _checkLarge;
    @FXML
    private CheckBox _checkMedium;
    @FXML
    private CheckBox _checkTwoMembers;
    @FXML
    private CheckBox _checkFourMembers;
    @FXML
    private CheckBox _checkEtretat;
    @FXML
    private CheckBox _checkNice;
    @FXML
    private CheckBox _checkOuistreham;



    public void setPresenter( ChoicePresenter presenter ) {
        _presenter = presenter;
    }

    public static ChoiceView createView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(GameView.class.getResource("firstMap.fxml"));
        Parent root = fxmlLoader.load();
        final ChoiceView view = fxmlLoader.getController();
        fxmlLoader.setController(view);
        Scene scene = new Scene(root, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        view._stage = stage;
        return view;
    }

    public void show() {
        _stage.show();
    }

    public void close() {
        _stage.close();
    }

    /*
    public void displayError( String message ) {
        _errorLabel.setText(message);
    }*/

    @FXML
    private void gameView() {
        _presenter.checkMap(_checkNice, _checkEtretat, _checkOuistreham);
        _presenter.launchGame(_checkLarge, _checkMedium, _checkTwoMembers, _checkFourMembers);

    }
}
