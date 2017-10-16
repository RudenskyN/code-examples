package ru.myaround.egorshashkov.around.screens.profile.screens.replies;

import ru.myaround.egorshashkov.around.base.InteractorInterface;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public interface RepliesInteractorInterface extends InteractorInterface {
    void getRepliesWithData(int id, String page);
}
