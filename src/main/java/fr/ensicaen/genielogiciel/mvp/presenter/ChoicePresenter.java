package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.LoginMain;
import fr.ensicaen.genielogiciel.mvp.model.*;
import fr.ensicaen.genielogiciel.mvp.view.ChoiceView;
import fr.ensicaen.genielogiciel.mvp.view.GameView;
import javafx.scene.control.CheckBox;

import java.io.IOException;

public class ChoicePresenter {
    private ChoiceView _view;
    private String _nickName;
    private Boat _boat;
    private Map _map;
    private String mapBackground;

    public ChoicePresenter(String nickName) {
        _nickName = nickName;
        mapBackground = "SpotMap3.fxml";
    }

    public void setView( ChoiceView view ) {_view = view;}

    public void launchGame(CheckBox checkLarge, CheckBox checkMedium, CheckBox checkTwoMembers, CheckBox checkFourMembers) {
        try {
            if(checkLarge.isSelected() && checkTwoMembers.isSelected()){
                 _boat = new LargeSail(new TwoMembers(new ConcreteBoat()));
            } else if(checkLarge.isSelected() && checkFourMembers.isSelected()) {
                _boat = new LargeSail(new FourMembers(new ConcreteBoat()));
            } else if(checkMedium.isSelected() && checkTwoMembers.isSelected()) {
                _boat = new MediumSail(new TwoMembers(new ConcreteBoat()));
            } else if(checkMedium.isSelected() && checkFourMembers.isSelected()) {
                _boat = new MediumSail(new FourMembers(new ConcreteBoat()));
            } else {
                _boat = new ConcreteBoat();
            }
            createAndDisplayGameView();
        } catch (IOException e) {
            e.printStackTrace();
        }
        _view.close();
    }

    /**
     * Set the map according to Map selected in a CheckBox
     * @param checkNice
     * @param checkEtretat
     * @param checkOuistreham
     */
    public void checkMap(CheckBox checkNice, CheckBox checkEtretat, CheckBox checkOuistreham) {
        if(checkNice.isSelected()){
            _map = new Map(1);
            mapBackground = "SpotMap.fxml";
        } else if (checkEtretat.isSelected()) {
            _map = new Map(2);
            mapBackground = "SpotMap2.fxml";
        } else {
            _map = new Map(3);
        }
    }


    private void createAndDisplayGameView() throws IOException {
        Game g = new Game();
        GameView view = GameView.createView(mapBackground);
        GamePresenter gamePresenter = new GamePresenter(_nickName, _boat, _map, g);
        view.setPresenter(gamePresenter);
        gamePresenter.setView(view);
        view.show();
    }


}
