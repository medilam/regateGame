package fr.ensicaen.genielogiciel.mvp.model;

public class StopGame extends StateGame {

    @Override
    boolean stop() {
        return true;
    }

    @Override
    boolean resume() {
        return false;
    }

    @Override
    boolean finished() {
        return false;
    }
}
