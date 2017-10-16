package ru.myaround.egorshashkov.around.screens.profile.screens.replies;

import java.util.List;

import ru.myaround.egorshashkov.around.MainRouter;
import ru.myaround.egorshashkov.around.base.BasePresenterInterface;
import ru.myaround.egorshashkov.around.viewitems.ReplyViewItem;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public interface RepliesPresenterInterface extends BasePresenterInterface<RepliesView,MainRouter> {

    void getReplies(List<ReplyViewItem> replyViewItems);
}
