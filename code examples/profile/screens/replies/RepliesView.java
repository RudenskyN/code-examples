package ru.myaround.egorshashkov.around.screens.profile.screens.replies;

import android.support.v7.widget.RecyclerView;

import ru.myaround.egorshashkov.around.base.BaseMainView;

/**
 * Eventd by egor.shashkov on 19/04/2017.
 */

public interface RepliesView extends BaseMainView {
    void setRecyclerAdapter(RecyclerView.Adapter mAdapter);
}
