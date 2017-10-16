package ru.myaround.egorshashkov.around.services.methods.results;

import ru.myaround.egorshashkov.around.models.Reply;
import ru.myaround.egorshashkov.around.models.User;

/**
 * Created by egorshashkov on 17/04/2017.
 */

public interface UserResults {
    void performWithUsers(User[] users);
    void performWithUsers(User user);
    void performWithReplies(Reply[] replies);
}
