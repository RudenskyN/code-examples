package ru.myaround.egorshashkov.around.screens.profile;

import ru.myaround.egorshashkov.around.base.InteractorInterface;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public interface ProfileInteractorInterface extends InteractorInterface {
    void getMyUserInfo();
    void getUserEvents(int userID, int page);

}
