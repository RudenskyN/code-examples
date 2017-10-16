package ru.myaround.egorshashkov.around.screens.profile.screens.subscribers;

import ru.myaround.egorshashkov.around.base.InteractorInterface;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public interface SubscribersInteractorInterface extends InteractorInterface {
    void getMySubscribers(int id, String type, String page);
}
