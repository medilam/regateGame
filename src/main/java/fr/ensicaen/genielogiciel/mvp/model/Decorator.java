package fr.ensicaen.genielogiciel.mvp.model;

public abstract class Decorator extends Boat {
    private Boat _myBoat;
    public Decorator(Boat boat){
        _myBoat = boat;
    }

    @Override
    public int getSize() {
        if (_myBoat != null) {
            return _myBoat.getSize();
        }
        return 50;
    }

    @Override
    public int getTeam() {
        return _myBoat.getTeam();
    }

}
