package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.Model;

public class CommandLeft extends Command{

    public CommandLeft(double operand, Model model) {
        super(operand, model);
    }

    @Override
    public double execute() {
        return get_model().turnLeft(get_operand());
    }

    @Override
    public double undo() {
        return get_model().turnRight(get_operand());
    }
}
