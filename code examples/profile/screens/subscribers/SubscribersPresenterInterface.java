package ru.myaround.egorshashkov.around.screens.profile.screens.subscribers;

import java.util.List;

import ru.myaround.egorshashkov.around.MainRouter;
import ru.myaround.egorshashkov.around.base.BasePresenterInterface;
import ru.myaround.egorshashkov.around.viewitems.UserViewItem;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public interface SubscribersPresenterInterface extends BasePresenterInterface<SubscribersView,MainRouter> {
    void getMySubscribers( List<UserViewItem> userViewItems);
}
