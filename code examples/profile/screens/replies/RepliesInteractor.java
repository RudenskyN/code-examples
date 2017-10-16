package ru.myaround.egorshashkov.around.screens.profile.screens.replies;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.myaround.egorshashkov.around.base.BasePresenterInterface;
import ru.myaround.egorshashkov.around.base.Interactor;
import ru.myaround.egorshashkov.around.models.Reply;
import ru.myaround.egorshashkov.around.models.User;
import ru.myaround.egorshashkov.around.services.UserServiceInterface;
import ru.myaround.egorshashkov.around.services.methods.results.UserResults;
import ru.myaround.egorshashkov.around.viewitems.ReplyViewItem;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class RepliesInteractor extends Interactor implements RepliesInteractorInterface {

    @Inject
    UserServiceInterface userService;

    RepliesPresenterInterface basePresenter;

    public RepliesInteractor(){

    }

    @Override
    public void setBasePresenter(BasePresenterInterface basePresenter) {
        this.basePresenter = (RepliesPresenterInterface) basePresenter;
    }

    @Override
    public void getRepliesWithData(int id, String page) {
       userService.getMyReplies(id, page, new UserResults() {
           @Override
           public void performWithUsers(User[] users) {

           }

           @Override
           public void performWithUsers(User user) {

           }

           @Override
           public void performWithReplies(Reply[] replies) {
                List<ReplyViewItem> replyViewItems = new ArrayList<ReplyViewItem>();
               for (Reply reply: replies){
                   ReplyViewItem replyViewItem = new ReplyViewItem();
                   replyViewItem.setTitle(reply.getTitle());
                   replyViewItem.setComment(reply.getComment());
                   replyViewItem.setOwnerId(reply.getOwnerId());
                   replyViewItem.setStars(reply.getStars());
                   replyViewItem.setUser(reply.getUser());
                   replyViewItem.setUserId(reply.getUserId());
                   replyViewItems.add(replyViewItem);
               }
               basePresenter.getReplies(replyViewItems);
           }
       });
    }
}
