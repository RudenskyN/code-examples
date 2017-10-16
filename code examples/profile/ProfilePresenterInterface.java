package ru.myaround.egorshashkov.around.screens.profile;

import android.view.View;

import java.util.HashMap;
import java.util.List;

import ru.myaround.egorshashkov.around.MainRouter;
import ru.myaround.egorshashkov.around.base.BasePresenterInterface;
import ru.myaround.egorshashkov.around.viewitems.EventViewItem;
import ru.myaround.egorshashkov.around.viewitems.UserViewItem;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public interface ProfilePresenterInterface extends BasePresenterInterface<ProfileView,MainRouter> {
    void updateWithUser(UserViewItem item);
    void updateEvents(List<EventViewItem> items);
    void setProfileScreen(int id, HashMap<String, Object> payload);
    void setEventScreen(HashMap<String, Object> payload);
    void initWithButtonAndStringArray(View view, String[] pos);
}
