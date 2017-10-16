package ru.myaround.egorshashkov.around.screens.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import ru.myaround.egorshashkov.around.base.BaseMainView;
import ru.myaround.egorshashkov.around.models.Photo;
import ru.myaround.egorshashkov.around.screens.profile.Adapters.ProfileAdapter;
import ru.myaround.egorshashkov.around.viewitems.EventViewItem;
import ru.myaround.egorshashkov.around.viewitems.UserViewItem;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public interface ProfileView extends BaseMainView {

    void setRecyclerAdapter(ProfileAdapter mAdapter);
    void updateWithUseritem(UserViewItem item);
    void updateWithEvents(List<EventViewItem> items);
    HashMap<String, Object> getPayload();
    Context getContext();
    void openFullscreenGallery(Photo[] photos, int position);
}
