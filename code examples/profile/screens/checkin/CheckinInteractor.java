package ru.myaround.egorshashkov.around.screens.profile.screens.checkin;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.myaround.egorshashkov.around.base.BasePresenterInterface;
import ru.myaround.egorshashkov.around.base.Interactor;
import ru.myaround.egorshashkov.around.models.Event;
import ru.myaround.egorshashkov.around.screens.profile.ProfilePresenterInterface;
import ru.myaround.egorshashkov.around.services.EventServiceInterface;
import ru.myaround.egorshashkov.around.services.UserServiceInterface;
import ru.myaround.egorshashkov.around.services.methods.results.EventResults;
import ru.myaround.egorshashkov.around.viewitems.EventViewItem;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public class CheckinInteractor extends Interactor implements CheckinInteractorInterface {

    @Inject
    public EventServiceInterface eventService;

    public CheckinPresenterInterface basePresenter;

    public CheckinInteractor(){

    }

    @Override
    public void setBasePresenter(BasePresenterInterface basePresenter) {
        this.basePresenter = (CheckinPresenterInterface) basePresenter;
    }


    @Override
    public void getCheckinsWhithData(int userId, String page) {
        eventService.getUserCheckins(userId, page, new EventResults() {
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
                basePresenter.updateCheckin(eventViewItems);
            }

            @Override
            public void performWithBool(boolean flag) {

            }
        });
    }
}
