package ru.myaround.egorshashkov.around.screens.profile.screens.checkin;

import ru.myaround.egorshashkov.around.base.InteractorInterface;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public interface CheckinInteractorInterface extends InteractorInterface {
    void getCheckinsWhithData(int UserId, String page);
}
