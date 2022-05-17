package fr.ensicaen.genielogiciel.mvp.model;

public class FinishedGame extends StateGame {
    @Override
    boolean stop() {
        return false;
    }

    @Override
    boolean resume() {
        return false;
    }

    @Override
    boolean finished() {
        return true;
    }
}
