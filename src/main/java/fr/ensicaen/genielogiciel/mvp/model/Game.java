package fr.ensicaen.genielogiciel.mvp.model;

public class Game {

    private StateGame _stateGame;


    public boolean stop() {
        _stateGame = new StopGame();
        return _stateGame.stop();
    }


    public boolean resume() {
        _stateGame = new ResumeGame();
        return _stateGame.resume();
    }


    public boolean finished() {
        _stateGame = new FinishedGame();
        return _stateGame.finished();
    }
}
