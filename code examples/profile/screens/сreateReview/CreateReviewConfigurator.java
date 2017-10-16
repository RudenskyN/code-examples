package ru.myaround.egorshashkov.around.screens.profile.screens.сreateReview;

import java.util.HashMap;

import javax.inject.Inject;

import ru.myaround.egorshashkov.around.MainActivity;
import ru.myaround.egorshashkov.around.base.BaseConfiguratorInterface;
import ru.myaround.egorshashkov.around.base.BaseMainFragment;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class CreateReviewConfigurator implements BaseConfiguratorInterface {
    @Inject
    CreateReviewInteractorInterface createReviewInteractorInterface;
    @Inject
    CreateReviewPresenterInterface createReviewPresenterInterface;

    private BaseMainFragment fragment;

    public CreateReviewConfigurator(BaseMainFragment fragment, HashMap<String, Object> payload, MainActivity ma) {
        ma.getMainActivityComponent().inject(this);
        ma.getMainActivityComponent().inject((CreateReviewInteractor) createReviewInteractorInterface);

        this.fragment = fragment;
        this.fragment.setBasePresenter(createReviewPresenterInterface);

        createReviewInteractorInterface.setBasePresenter(createReviewPresenterInterface);

        createReviewPresenterInterface.setPayload(payload);
        createReviewPresenterInterface.setBaseInteractor(createReviewInteractorInterface);
        createReviewPresenterInterface.setRouter(ma);
        createReviewPresenterInterface.setView((CreateReviewView) fragment);

    }

    public BaseMainFragment getFragment() {
        return this.fragment;
    }
}
