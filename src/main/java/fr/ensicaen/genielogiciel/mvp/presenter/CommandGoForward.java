package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.Model;

public class CommandGoForward extends Command{

    public CommandGoForward(double operand, Model model) {
        super(operand, model);
    }

    @Override
    public double execute() {
        return get_model().moveForward(get_operand());
    }

    @Override
    public double undo() {
        return get_model().moveBack(get_operand());
    }
}
