package ru.myaround.egorshashkov.around.screens.profile.screens.subscribers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.myaround.egorshashkov.around.base.BasePresenterInterface;
import ru.myaround.egorshashkov.around.base.Interactor;
import ru.myaround.egorshashkov.around.models.Reply;
import ru.myaround.egorshashkov.around.models.User;
import ru.myaround.egorshashkov.around.services.UserServiceInterface;
import ru.myaround.egorshashkov.around.services.methods.results.UserResults;
import ru.myaround.egorshashkov.around.viewitems.UserViewItem;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class SubscribersInteractor extends Interactor implements SubscribersInteractorInterface {

    @Inject
    public UserServiceInterface userService;

    public SubscribersPresenterInterface basePresenter;

    public SubscribersInteractor(){

    }

    @Override
    public void setBasePresenter(BasePresenterInterface basePresenter) {
        this.basePresenter = (SubscribersPresenterInterface) basePresenter;
    }

    @Override
    public void getMySubscribers(int id, String type, String page) {
        userService.getMySubscribers(id, type, page, new UserResults() {
            @Override
            public void performWithUsers(User[] users) {
                List<UserViewItem> userViewItems = new ArrayList<UserViewItem>();

                for (User user : users){
                    UserViewItem ui = new UserViewItem();
                    ui.setId(user.getId());
                    ui.setAvatar(user.getAvatar());
                    ui.setBanner(user.getBanner());
                    ui.setDescription(user.getDescription());
                    ui.setEvents_count(user.getEvents_count());
                    ui.setFollowed(user.getFollowed());
                    ui.setName(user.getName());
                    ui.setRepliesCount(user.getRepliesCount());
                    ui.setRepliesSum(user.getRepliesSum());
                    ui.setSubrs(user.getSubrs());
                    ui.setSubs(user.getSubs());
                    ui.setIsi(user.getIsi());
                    userViewItems.add(ui);
                    basePresenter.getMySubscribers(userViewItems);
                }
            }

            @Override
            public void performWithUsers(User user) {

            }

            @Override
            public void performWithReplies(Reply[] replies) {

            }
        });
    }
}
