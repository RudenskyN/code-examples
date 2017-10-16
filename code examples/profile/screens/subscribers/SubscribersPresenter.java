package ru.myaround.egorshashkov.around.screens.profile.screens.subscribers;

import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import ru.myaround.egorshashkov.around.MainRouter;
import ru.myaround.egorshashkov.around.base.BasePresenter;
import ru.myaround.egorshashkov.around.base.InteractorInterface;
import ru.myaround.egorshashkov.around.screens.profile.screens.subscribers.Adapters.SubscribersAdapter;
import ru.myaround.egorshashkov.around.viewitems.UserViewItem;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class SubscribersPresenter extends BasePresenter<SubscribersView, MainRouter> implements SubscribersPresenterInterface {

    public SubscribersInteractorInterface baseInteractor;

    private RecyclerView.Adapter mAdapter;

    private int userID;

    private String type;

    public SubscribersPresenter() {

    }

    @Override
    public SubscribersInteractorInterface getBaseInteractor() {
        return baseInteractor;
    }

    @Override
    public void setBaseInteractor(InteractorInterface baseInteractor) {
        this.baseInteractor = (SubscribersInteractorInterface) baseInteractor;
    }

    @Override
    public void onStart() {
        baseInteractor.getMySubscribers(this.userID, type, "0");
    }


    @Override
    public void setPayload(HashMap<String, Object> payload) {
        this.userID = (int) payload.get("userID");
        this.type = (String) payload.get("type");
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
    public SubscribersView getView() {
        return super.getView();

    }

    @Override
    public void getMySubscribers(List<UserViewItem> userViewItems) {
        mAdapter = new SubscribersAdapter(userViewItems);
        getView().setRecyclerAdapter(mAdapter);
    }
}
