package fr.ensicaen.genielogiciel.mvp.model;

public class MediumSail extends Decorator {

    public MediumSail(Boat boat) {
        super(boat);
    }

    @Override
    public int getSize() {
        return 50;
    }

    @Override
    public int getTeam() {
        return super.getTeam();
    }
}
