package ru.myaround.egorshashkov.around.screens.profile.screens.сreateReview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.HashMap;
import java.util.List;

import ru.myaround.egorshashkov.around.MainRouter;
import ru.myaround.egorshashkov.around.base.BasePresenter;
import ru.myaround.egorshashkov.around.base.InteractorInterface;
import ru.myaround.egorshashkov.around.screens.profile.Adapters.ProfileAdapter;
import ru.myaround.egorshashkov.around.viewitems.EventViewItem;
import ru.myaround.egorshashkov.around.viewitems.UserViewItem;

import static android.content.ContentValues.TAG;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class CreateReviewPresenter extends BasePresenter<CreateReviewView, MainRouter> implements CreateReviewPresenterInterface {


    public CreateReviewInteractorInterface baseInteractor;

    private RecyclerView.Adapter mAdapter;



    public CreateReviewPresenter() {

    }

    @Override
    public CreateReviewInteractorInterface getBaseInteractor() {
        return baseInteractor;
    }

    @Override
    public void setBaseInteractor(InteractorInterface baseInteractor) {
        this.baseInteractor = (CreateReviewInteractorInterface) baseInteractor;
    }

    @Override
    public void onStart() {
    }


    @Override
    public void setPayload(HashMap<String, Object> payload) {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void onTabBarButtonTouchWithIndex(int i) {
        if(i == 2){
          //  getRouter().openReview();
        }
        if(i == 4){
            getRouter().openMap();
        }
    }

    @Override
    public CreateReviewView getView() {
        return super.getView();

    }
}
