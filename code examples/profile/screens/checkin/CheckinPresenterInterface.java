package ru.myaround.egorshashkov.around.screens.profile.screens.checkin;

import java.util.HashMap;
import java.util.List;

import ru.myaround.egorshashkov.around.MainRouter;
import ru.myaround.egorshashkov.around.base.BasePresenterInterface;
import ru.myaround.egorshashkov.around.viewitems.EventViewItem;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public interface CheckinPresenterInterface extends BasePresenterInterface<CheckinView,MainRouter> {
    void updateCheckin(List<EventViewItem> items);
    void setEventScreen(HashMap<String, Object> payload);
}
