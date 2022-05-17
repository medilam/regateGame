package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.Boat;
import fr.ensicaen.genielogiciel.mvp.model.Game;
import fr.ensicaen.genielogiciel.mvp.model.Map;
import fr.ensicaen.genielogiciel.mvp.model.Model;
import fr.ensicaen.genielogiciel.mvp.view.GameView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;


public final class GamePresenter {
    private final Model _model;
    private GameView _view;
    private Image _boatImage;
    private Boat _boat;
    private Map _map;
    private Game _game;
    private boolean _stop;
    private boolean _resume;
    private boolean _finished;
    private Timeline _timeline;
    private int iterations;

    public GamePresenter(String nickName, Boat boat, Map map, Game g) {
        _map = map;
        _boat = boat;
        _game = g;
        _model = new Model(nickName, 600, 400, _map);
    }

    public void setView(GameView view) {
        _view = view;
    }

    public GameView getView() {
        return this._view;
    }

    public void runGameLoop() {
        final int FRAME_PER_SECONDS = 200;
        _timeline = new Timeline(new KeyFrame(Duration.millis(FRAME_PER_SECONDS), onFinished -> {
            // What is done for each frame
            update(new CommandGoForward(10, _model));
            render();
            iterations++;
        }));
        _timeline.setCycleCount(Animation.INDEFINITE);
        _timeline.play();
    }

    private void update(Command command) {
        cleanCanvas();
        double result = command.execute();
        if (_model.isArrived()) {
            finished();
        }
    }

    private void render() {
        System.out.println("Un tour de jeu");

        if(_stop){
            _timeline.stop();
        } else if(_resume){
            _timeline.play();
        }
        if (_finished) {
            _timeline.stop();
            showScore();
        }

        for (int i = 0; i < _map.getNbrBuoys(); i++) {
            paintBuoy(_map.getMapx()[i], _map.getMapy()[i]);
        }

        paintBoat(_model.getX(), _model.getY());
        _view.getSpeedLabel().setText(_model.getWindSpeed());
        _view.getWindDirectionLabel().setText(_model.getWindDirection());
    }

    /**
     * function which draw the boat image
     *
     * @param x initial x position in the canva
     * @param y initial y position in the canva
     */
    private void paintBoat(double x, double y) {
        GraphicsContext gc = _view.getCanvas().getGraphicsContext2D();
        _boatImage = new Image(String.valueOf(GameView.class.getResource("boat.png")));
        System.out.println("Le nombre d'équipage est " + _boat.getTeam());
        gc.drawImage(_boatImage, x, y, _boat.getSize(), _boat.getSize());
    }

    /**
     * function which draw the buoy
     *
     * @param x initial x position in the canva
     * @param y initial y position in the canva
     */
    private void paintBuoy(double x, double y) {
        GraphicsContext gc = _view.getCanvas().getGraphicsContext2D();
        Image _buoyImage = new Image(String.valueOf(GameView.class.getResource("buoy.png")));
        gc.drawImage(_buoyImage, x, y, 80, 80);
    }

    /**
     * function which clean the canvas for the update of the view
     */
    private void cleanCanvas() {
        GraphicsContext gc = _view.getCanvas().getGraphicsContext2D();
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }


    public void leftHandler() {
        update(new CommandLeft(10, _model));
        render();
    }


    public void rightHandler() {
        update(new CommandRight(10, _model));
        render();
    }

    /**
     * function related to the state of the game
     */
    public void stop() {
        _stop = _game.stop();
    }

    /**
     * function related to the state of the game
     */
    public void resume() {
        _resume = _game.resume();
        _stop = false;
        render();
    }

    /**
     * function related to the state of the game
     */
    public  void finished(){
        _finished = _game.finished();
    }


    private void showScore() {
        int score;
        if (iterations <=1000) {
            score = 1000 - iterations;
        } else {
            score = 0;
        }
        GraphicsContext gc = _view.getCanvas().getGraphicsContext2D();
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.setFont(new Font(80));
        gc.fillText("Score: " + score + "/1000", Math.round(_view.getCanvas().getWidth()/2),Math.round(_view.getCanvas().getHeight()/2));
    }

    public void replay(){
        _view.close();
    }
}