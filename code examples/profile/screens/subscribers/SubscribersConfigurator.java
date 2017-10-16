package ru.myaround.egorshashkov.around.screens.profile.screens.subscribers;

import java.util.HashMap;

import javax.inject.Inject;

import ru.myaround.egorshashkov.around.MainActivity;
import ru.myaround.egorshashkov.around.base.BaseConfiguratorInterface;
import ru.myaround.egorshashkov.around.base.BaseMainFragment;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class SubscribersConfigurator implements BaseConfiguratorInterface {
    @Inject
    SubscribersInteractorInterface subscribersInteractorInterface;
    @Inject
    SubscribersPresenterInterface subscribersPresenterInterface;

    private BaseMainFragment fragment;

    public SubscribersConfigurator(BaseMainFragment fragment, HashMap<String, Object> payload, MainActivity ma) {
        ma.getMainActivityComponent().inject(this);
        ma.getMainActivityComponent().inject((SubscribersInteractor) subscribersInteractorInterface);

        this.fragment = fragment;
        this.fragment.setBasePresenter(subscribersPresenterInterface);

        subscribersInteractorInterface.setBasePresenter(subscribersPresenterInterface);

        subscribersPresenterInterface.setPayload(payload);
        subscribersPresenterInterface.setBaseInteractor(subscribersInteractorInterface);
        subscribersPresenterInterface.setRouter(ma);
        subscribersPresenterInterface.setView((SubscribersView) fragment);

    }

    public BaseMainFragment getFragment() {
        return this.fragment;
    }
}
