package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.Model;

abstract class Command {
    private final double _operand;
    private final Model _model;

    public Command(double operand, Model model) {
        _model = model;
        _operand = operand;
    }

    public abstract double execute();
    public abstract double undo();
    public double redo() {
        return execute();
    }

    protected Model get_model() {
        return _model;
    }

    public double get_operand() {
        return _operand;
    }
}
