package ru.myaround.egorshashkov.around.screens.profile;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.myaround.egorshashkov.around.base.BasePresenterInterface;
import ru.myaround.egorshashkov.around.base.Interactor;
import ru.myaround.egorshashkov.around.models.Event;
import ru.myaround.egorshashkov.around.models.Reply;
import ru.myaround.egorshashkov.around.models.User;
import ru.myaround.egorshashkov.around.viewitems.EventViewItem;
import ru.myaround.egorshashkov.around.services.EventServiceInterface;
import ru.myaround.egorshashkov.around.services.UserServiceInterface;
import ru.myaround.egorshashkov.around.services.methods.results.EventResults;
import ru.myaround.egorshashkov.around.services.methods.results.UserResults;
import ru.myaround.egorshashkov.around.viewitems.UserViewItem;

import static android.content.ContentValues.TAG;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class ProfileInteractor extends Interactor implements ProfileInteractorInterface {
    @Inject
    public UserServiceInterface userService;

    @Inject
    public EventServiceInterface eventService;

    public ProfilePresenterInterface basePresenter;

    public ProfileInteractor(){

    }

    @Override
    public void setBasePresenter(BasePresenterInterface basePresenter) {
        this.basePresenter = (ProfilePresenterInterface) basePresenter;
    }

    public void getMyUserInfo(){
        userService.getMyUserInfo(new UserResults() {
            @Override
            public void performWithUsers(User[] users) {

            }

            @Override
            public void performWithUsers(User user) {
                Log.d(TAG, "performWithUsers: ");
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
                basePresenter.updateWithUser(ui);
            }

            @Override
            public void performWithReplies(Reply[] replies) {

            }
        });
    }

    @Override
    public void getUserEvents(int userID, int page) {
        eventService.getUserEvents(userID, page, new EventResults() {
            @Override
            public void performWithEvents(Event[] events) {

                List<EventViewItem> eventViewItems = new ArrayList<EventViewItem>();

                for (Event event :
                        events) {
                    EventViewItem vi = new EventViewItem();
                    vi.setId(event.getID());
                    vi.setTitle(event.getTitle());
                    vi.setLongtitude(event.getLongtitude());
                    vi.setLatitude(event.getLatitude());
                    vi.setAddress(event.getAddress());
                    vi.setDescriptions( event.getDescriptions());
                    vi.setPhotos(event.getPhotos());
                    vi.setStartTime(event.getStartTime());
                    vi.setEndTime(event.getEndTime());
                    vi.setEvent_type(event.getEvent_type());
                    vi.setCountComments(event.getCountComments());
                    vi.setCountLike(event.getCountLike());
                    vi.setCountDislike(event.getCountDislike());
                    eventViewItems.add(vi);
                }
                basePresenter.updateEvents(eventViewItems);
            }

            @Override
            public void performWithBool(boolean flag) {

            }
        });
    }
}
