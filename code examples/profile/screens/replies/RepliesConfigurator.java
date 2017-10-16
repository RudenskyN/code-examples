package ru.myaround.egorshashkov.around.screens.profile.screens.replies;

import java.util.HashMap;

import javax.inject.Inject;

import ru.myaround.egorshashkov.around.MainActivity;
import ru.myaround.egorshashkov.around.base.BaseConfiguratorInterface;
import ru.myaround.egorshashkov.around.base.BaseMainFragment;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class RepliesConfigurator implements BaseConfiguratorInterface {
    @Inject
    RepliesInteractorInterface repliesInteractorInterface;
    @Inject
    RepliesPresenterInterface repliesPresenterInterface;

    private BaseMainFragment fragment;

    public RepliesConfigurator(BaseMainFragment fragment, HashMap<String, Object> payload, MainActivity ma) {
        ma.getMainActivityComponent().inject(this);
        ma.getMainActivityComponent().inject((RepliesInteractor) repliesInteractorInterface);

        this.fragment = fragment;
        this.fragment.setBasePresenter(repliesPresenterInterface);

        repliesInteractorInterface.setBasePresenter(repliesPresenterInterface);

        repliesPresenterInterface.setPayload(payload);
        repliesPresenterInterface.setBaseInteractor(repliesInteractorInterface);
        repliesPresenterInterface.setRouter(ma);
        repliesPresenterInterface.setView((RepliesView) fragment);

    }

    public BaseMainFragment getFragment() {
        return this.fragment;
    }
}
