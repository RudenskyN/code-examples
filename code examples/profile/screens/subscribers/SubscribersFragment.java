package ru.myaround.egorshashkov.around.screens.profile.screens.subscribers;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import ru.myaround.egorshashkov.around.R;
import ru.myaround.egorshashkov.around.base.BaseMainFragment;
import ru.myaround.egorshashkov.around.base.BasePresenterInterface;
import ru.myaround.egorshashkov.around.base.Layout;


/**
 * Eventd by egor.shashkov on 19/04/2017.
 */
@Layout(id = R.layout.fragment_subscribers)
public class SubscribersFragment extends BaseMainFragment implements SubscribersView {
    @Nullable

    @BindView(R.id.recycler_view44)
    RecyclerView mRecyclerView;

    public SubscribersPresenterInterface basePresenter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public SubscribersPresenterInterface getBasePresenter() {
        return basePresenter;
    }

    @Override
    public void setBasePresenter(BasePresenterInterface basePresenter) {
        this.basePresenter = (SubscribersPresenterInterface) basePresenter;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void setRecyclerAdapter(RecyclerView.Adapter mAdapter){
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public int[] getImagesArray() {
        return new int[]{
            0,
            0,
            R.drawable.cross,
            0,
            0
        };
    }

    @Override
    protected void inject() {
        getMainActivityComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }
}
