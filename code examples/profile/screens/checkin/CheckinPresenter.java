package ru.myaround.egorshashkov.around.screens.profile.screens.checkin;

import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import ru.myaround.egorshashkov.around.MainRouter;
import ru.myaround.egorshashkov.around.base.BasePresenter;
import ru.myaround.egorshashkov.around.base.InteractorInterface;
import ru.myaround.egorshashkov.around.screens.profile.Adapters.ProfileAdapter;
import ru.myaround.egorshashkov.around.viewitems.EventViewItem;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class CheckinPresenter extends BasePresenter<CheckinView, MainRouter> implements CheckinPresenterInterface {

    public CheckinInteractorInterface baseInteractor;

    private RecyclerView.Adapter mAdapter;
    int userID;

    public CheckinPresenter() {

    }

    @Override
    public CheckinInteractorInterface getBaseInteractor() {
        return baseInteractor;
    }

    @Override
    public void setBaseInteractor(InteractorInterface baseInteractor) {
        this.baseInteractor = (CheckinInteractorInterface) baseInteractor;
    }

    @Override
    public void onStart() {

        baseInteractor.getCheckinsWhithData(userID, "0");
    }


    @Override
    public void setPayload(HashMap<String, Object> payload) {
        this.userID = (int) payload.get("userID");
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onTabBarButtonTouchWithIndex(int i) {
        if (i == 2){
            getRouter().openMap();
        }
    }

    @Override
    public CheckinView getView() {
        return super.getView();

    }

    @Override
    public void updateCheckin(List<EventViewItem> items) {
        mAdapter = new ProfileAdapter(items, (CheckinFragment) getView());
        getView().setRecyclerAdapter(mAdapter);
    }

    @Override
    public void setEventScreen(HashMap<String, Object> payload) {
        getRouter().openEvent(payload);
    }
}
