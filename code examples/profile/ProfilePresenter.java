package ru.myaround.egorshashkov.around.screens.profile;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import ru.myaround.egorshashkov.around.MainActivity;
import ru.myaround.egorshashkov.around.MainRouter;
import ru.myaround.egorshashkov.around.R;
import ru.myaround.egorshashkov.around.base.BasePresenter;
import ru.myaround.egorshashkov.around.base.InteractorInterface;
import ru.myaround.egorshashkov.around.popupMenu.PopupMenuCustom;
import ru.myaround.egorshashkov.around.popupMenu.PopupMenuInterface;
import ru.myaround.egorshashkov.around.viewitems.EventViewItem;
import ru.myaround.egorshashkov.around.screens.profile.Adapters.ProfileAdapter;
import ru.myaround.egorshashkov.around.viewitems.UserViewItem;

import static android.content.ContentValues.TAG;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileView, MainRouter> implements ProfilePresenterInterface {


    public ProfileInteractorInterface baseInteractor;

    private ProfileAdapter mAdapter;

    public ProfilePresenter() {

    }

    @Override
    public ProfileInteractorInterface getBaseInteractor() {
        return baseInteractor;
    }

    @Override
    public void setBaseInteractor(InteractorInterface baseInteractor) {
        this.baseInteractor = (ProfileInteractorInterface) baseInteractor;
    }

    @Override
    public void onStart() {
        new Handler().postDelayed(() -> baseInteractor.getMyUserInfo(), 500);

    }


    @Override
    public void setPayload(HashMap<String, Object> payload) {

    }

    @Override
    public void onStop() {
    }

    @Override
    public void onTabBarButtonTouchWithIndex(int i) {
        if (i == 0){
            getRouter().openSettings();
        }
        if (i == 2){
            getRouter().openMap();
        }
        if (i == 4){
            getRouter().openCheckin(getView().getPayload());
        }
    }

    @Override
    public ProfileView getView() {
        return super.getView();

    }

    @Override
    public void updateWithUser(UserViewItem item) {
        Log.d(TAG, "updateWithUser: ");
        getView().updateWithUseritem(item);
        baseInteractor.getUserEvents(item.getId(),0);
    }

    @Override
    public void updateEvents(List<EventViewItem> items) {
        if (mAdapter == null) {
            mAdapter = new ProfileAdapter(items, (ProfileFragment) getView());
            getView().setRecyclerAdapter(mAdapter);

        }else {

            mAdapter.setItems(items);
            mAdapter.notifyDataSetChanged();
        }
    }



    @Override
    public void setProfileScreen(int id, HashMap<String, Object> payload) {
        String type;
        switch(id){
            case R.id.reviewBtn:
                getRouter().openReview(payload);
                break;
            case R.id.subscribers:
                type = "subs";
                payload.put("type", type);
                getRouter().openSubscribers(payload);
                break;
            case R.id.subscriptions:
                type = "subrs";
                payload.put("type", type);
                getRouter().openSubscribers(payload);
                break;
        }
    }

    @Override
    public void setEventScreen(HashMap<String, Object> payload) {
        getRouter().openEvent(payload);
    }

    @Override
    public void initWithButtonAndStringArray(View view, String[] pos) {
        PopupMenuCustom popupMenuCustom = new PopupMenuCustom(view.getContext(), view);
        popupMenuCustom.setCurrentPresenter(this);
        popupMenuCustom.initWithButtonAndStringArray(view, pos);
    }

    @Override
    public void onPopupMenuButtonTouchWithIndex(String title) {
        switch (title){
            case "Хуй":
                Toast.makeText(getView().getContext(),
                        "Вы выбрали PopupMenu 1",
                        Toast.LENGTH_SHORT).show();
                break;
            case "Пизда":
                Toast.makeText(getView().getContext(),
                        "Вы выбрали PopupMenu 2",
                        Toast.LENGTH_SHORT).show();
                break;
            case "Джигурда":
                Toast.makeText(getView().getContext(),
                        "Вы выбрали PopupMenu 3",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
