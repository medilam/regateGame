package fr.ensicaen.genielogiciel.mvp.model;

public abstract class StateGame {
    abstract boolean stop();
    abstract boolean resume();
    abstract boolean finished();
}
