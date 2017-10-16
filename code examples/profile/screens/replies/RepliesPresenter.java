package ru.myaround.egorshashkov.around.screens.profile.screens.replies;

import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import ru.myaround.egorshashkov.around.MainRouter;
import ru.myaround.egorshashkov.around.base.BasePresenter;
import ru.myaround.egorshashkov.around.base.InteractorInterface;
import ru.myaround.egorshashkov.around.screens.profile.screens.replies.Adapters.RepliesAdapter;
import ru.myaround.egorshashkov.around.viewitems.ReplyViewItem;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class RepliesPresenter extends BasePresenter<RepliesView, MainRouter> implements RepliesPresenterInterface {

    public RepliesInteractorInterface baseInteractor;

    private RecyclerView.Adapter mAdapter;

    private int userID;

    public RepliesPresenter() {

    }

    @Override
    public RepliesInteractorInterface getBaseInteractor() {
        return baseInteractor;
    }

    @Override
    public void setBaseInteractor(InteractorInterface baseInteractor) {
        this.baseInteractor = (RepliesInteractorInterface) baseInteractor;
    }

    @Override
    public void onStart() {
        baseInteractor.getRepliesWithData(userID, "0");
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
        if(i == 2){
            getRouter().openCreateReview();
        }
        if(i == 4){
            getRouter().openMap();
        }
    }

    @Override
    public RepliesView getView() {
        return super.getView();

    }

    @Override
    public void getReplies(List<ReplyViewItem> replyViewItems) {
        mAdapter = new RepliesAdapter(replyViewItems);
        getView().setRecyclerAdapter(mAdapter);
    }
}
