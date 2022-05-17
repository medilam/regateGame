package fr.ensicaen.genielogiciel.mvp.model;

public abstract class Team extends Boat {
    private Boat _myBoat;

    public Team(Boat boat){
        _myBoat = boat;
    }

    @Override
    public int getSize() {
        return _myBoat.getSize();
    }

    @Override
    public int getTeam() {
        return _myBoat.getTeam();
    }


}
