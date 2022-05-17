package fr.ensicaen.genielogiciel.mvp.model;

public class LargeSail extends Decorator {

    public LargeSail(Boat boat) {
        super(boat);

    }

    @Override
    public int getSize() {
        return 100;
    }

    @Override
    public int getTeam() {
       return super.getTeam();
    }

}
