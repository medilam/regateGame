package fr.ensicaen.genielogiciel.mvp.view;


import fr.ensicaen.genielogiciel.mvp.presenter.GamePresenter;


import fr.ensicaen.genielogiciel.mvp.presenter.LoginPresenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public final class GameView {

    private GamePresenter _gamePresenter;
    private LoginPresenter _loginPresenter;
    private Stage _stage;
    @FXML
    private Canvas _canvas;
    @FXML
    private Button _playBtn;
    @FXML
    private Button _stopBtn;
    @FXML
    private Button _resetBtn;
    @FXML
    private Label _speedLabel;
    @FXML
    private Label _directionLabel;
    @FXML
    private Button _replay;


    public static GameView createView(String fxmlMap) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(GameView.class.getResource(fxmlMap));
        Parent root = fxmlLoader.load();
        final GameView view = fxmlLoader.getController();
        fxmlLoader.setController(view);
        //Scene scene = new Scene(root, 788, 489);
        Scene scene = new Scene(root, 800, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        view._stage = stage;
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> view.onKeyPressed(event.getCode()));
        return view;
    }

    public void setPresenter( GamePresenter gamePresenter ) {
        _gamePresenter = gamePresenter;
    }

    public void show() {
        _stage.show();
    }

    private void onKeyPressed( KeyCode code ) {
        if (code == KeyCode.SPACE) {
            _gamePresenter.runGameLoop();
        }
        if (code == KeyCode.RIGHT) {
            _gamePresenter.rightHandler();
        }
        if (code == KeyCode.LEFT) {
            _gamePresenter.leftHandler();
        }

    }

    public Canvas getCanvas(){
        return _canvas;
    }

    public Label getSpeedLabel(){
        return _speedLabel;
    }

    public Label getWindDirectionLabel(){ return _directionLabel; }

    /**
     * A method for the button stop
     */
    public void stop(){
        _gamePresenter.stop();
    }

    /**
     * A method for the button play
     */
    public void resume(){
        _gamePresenter.resume();
    }

    /**
     * Replay function
     */
    public void replay() {
        _gamePresenter.replay();
        _gamePresenter.finished();
        LoginPresenter newLoginPresenter = new LoginPresenter();
        newLoginPresenter.launchGame("replay");

    }

    public void close() {
        _stage.close();
    }

}
