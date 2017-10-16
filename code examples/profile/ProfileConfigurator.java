package ru.myaround.egorshashkov.around.screens.profile;

import java.util.HashMap;

import javax.inject.Inject;

import ru.myaround.egorshashkov.around.MainActivity;
import ru.myaround.egorshashkov.around.base.BaseConfiguratorInterface;
import ru.myaround.egorshashkov.around.base.BaseMainFragment;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class ProfileConfigurator implements BaseConfiguratorInterface {
    @Inject
    ProfileInteractorInterface ProfileInteractor;
    @Inject
    ProfilePresenterInterface ProfilePresenter;

    private BaseMainFragment fragment;

    public ProfileConfigurator(BaseMainFragment fragment, HashMap<String, Object> payload, MainActivity ma) {
        ma.getMainActivityComponent().inject(this);
        ma.getMainActivityComponent().inject((ProfileInteractor) ProfileInteractor);

        this.fragment = fragment;
        this.fragment.setBasePresenter(ProfilePresenter);

        ProfileInteractor.setBasePresenter(ProfilePresenter);

        ProfilePresenter.setPayload(payload);
        ProfilePresenter.setBaseInteractor(ProfileInteractor);
        ProfilePresenter.setRouter(ma);
        ProfilePresenter.setView((ProfileView) fragment);

    }

    public BaseMainFragment getFragment() {
        return this.fragment;
    }
}
