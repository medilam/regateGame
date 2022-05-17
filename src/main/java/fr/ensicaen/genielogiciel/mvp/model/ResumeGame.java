package fr.ensicaen.genielogiciel.mvp.model;

public class ResumeGame extends StateGame {

    @Override
    boolean stop() {
        return false;
    }

    @Override
    boolean resume() {
        return true;
    }

    @Override
    boolean finished() {
        return true;
    }
}
