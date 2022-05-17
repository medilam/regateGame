package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.model.Model;

public class CommandRight extends Command {


    public CommandRight(double operand, Model model) {
        super(operand, model);
    }

    @Override
    public double execute() {
        return get_model().turnRight(get_operand());
    }

    @Override
    public double undo() {
        return get_model().turnLeft(get_operand());
    }


}
