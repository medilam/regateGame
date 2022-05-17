package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.LoginMain;
import fr.ensicaen.genielogiciel.mvp.view.ChoiceView;
import fr.ensicaen.genielogiciel.mvp.view.LoginView;

import java.io.IOException;

public final class LoginPresenter {
    private LoginView _view;

    public void setView( LoginView view ) {
        _view = view;
    }

    public void launchGame( String nickName ) {
        if (nickName.isEmpty()) {
            _view.displayError(LoginMain.getMessageBundle().getString("error.nickname"));
        } else {
            try {
                createAndDisplayChoiceView(nickName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (_view != null) {
                _view.close();
            }
        }
    }

    private void createAndDisplayChoiceView( String nickName ) throws IOException {
        //GameView view = GameView.createView();
        ChoiceView view = ChoiceView.createView();
        //GamePresenter gamePresenter = new GamePresenter(nickName);
        ChoicePresenter choicePresenter = new ChoicePresenter(nickName);
        //view.setPresenter(gamePresenter);
        view.setPresenter(choicePresenter);
        //gamePresenter.setView(view);
        choicePresenter.setView(view);
        view.show();
    }
}
