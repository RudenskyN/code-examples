package ru.myaround.egorshashkov.around.screens.profile.screens.checkin;

import java.util.HashMap;

import javax.inject.Inject;

import ru.myaround.egorshashkov.around.MainActivity;
import ru.myaround.egorshashkov.around.base.BaseConfiguratorInterface;
import ru.myaround.egorshashkov.around.base.BaseMainFragment;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class CheckinConfigurator implements BaseConfiguratorInterface {
    @Inject
    CheckinInteractorInterface checkinInteractorInterface;
    @Inject
    CheckinPresenterInterface checkinPresenterInterface;

    private BaseMainFragment fragment;

    public CheckinConfigurator(BaseMainFragment fragment, HashMap<String, Object> payload, MainActivity ma) {
        ma.getMainActivityComponent().inject(this);
        ma.getMainActivityComponent().inject((CheckinInteractor) checkinInteractorInterface);

        this.fragment = fragment;
        this.fragment.setBasePresenter(checkinPresenterInterface);

        checkinInteractorInterface.setBasePresenter(checkinPresenterInterface);

        checkinPresenterInterface.setPayload(payload);
        checkinPresenterInterface.setBaseInteractor(checkinInteractorInterface);
        checkinPresenterInterface.setRouter(ma);
        checkinPresenterInterface.setView((CheckinView) fragment);

    }

    public BaseMainFragment getFragment() {
        return this.fragment;
    }
}
