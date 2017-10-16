package ru.myaround.egorshashkov.around.screens.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import ru.myaround.egorshashkov.around.MainActivity;
import ru.myaround.egorshashkov.around.R;
import ru.myaround.egorshashkov.around.base.BaseMainFragment;
import ru.myaround.egorshashkov.around.base.BasePresenterInterface;
import ru.myaround.egorshashkov.around.base.Layout;
import ru.myaround.egorshashkov.around.gallery.GalleryFullscreen;
import ru.myaround.egorshashkov.around.libs.stikkyHeader.StikkyHeaderBuilder;
import ru.myaround.egorshashkov.around.libs.stikkyHeader.StikkyHeaderRecyclerView;
import ru.myaround.egorshashkov.around.libs.stikkyHeader.animator.AnimatorBuilder;
import ru.myaround.egorshashkov.around.libs.stikkyHeader.animator.HeaderStikkyAnimator;
import ru.myaround.egorshashkov.around.models.Photo;
import ru.myaround.egorshashkov.around.screens.profile.Adapters.ProfileAdapter;
import ru.myaround.egorshashkov.around.base.RecyclerItemClickListener;
import ru.myaround.egorshashkov.around.viewitems.EventViewItem;
import ru.myaround.egorshashkov.around.viewitems.UserViewItem;

import static android.content.ContentValues.TAG;


/**
 * Eventd by egor.shashkov on 19/04/2017.
 */
@Layout(id = R.layout.fragment_profile)
public class ProfileFragment extends BaseMainFragment implements ProfileView, View.OnClickListener{
    @Nullable

    @BindView(R.id.recyclerview_profile)
    RecyclerView mRecyclerView;
    @BindView(R.id.header_image_profile)
    ImageView mHeader_image;
    @BindView(R.id.reviewBtn)
    ConstraintLayout reviewBtn;
    @BindView(R.id.subscribers)
    TextView subscribers;
    @BindView(R.id.subscriptions)
    TextView subscriptions;
    @BindView(R.id.publications)
    TextView publications;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.about_me)
    TextView aboutMe;
    @BindView(R.id.profile_icon)
    ImageView profileIcon;
    @BindView(R.id.profile_star_one)
    ImageView starOne;
    @BindView(R.id.profile_star_two)
    ImageView starTwo;
    @BindView(R.id.profile_star_three)
    ImageView starThree;
    @BindView(R.id.profile_star_four)
    ImageView starFour;
    @BindView(R.id.profile_star_five)
    ImageView starFive;
    @BindView(R.id.profile_replies_count)
    TextView repliesCount;
    @BindView(R.id.profile_popup_menu)
    Button mPopupBtn;


    ViewPager viewPager;

    HashMap<String, Object> payload;

    ImageView[] stars = {starOne, starTwo, starThree, starFour, starFive};

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    String avatar;


    public ProfilePresenterInterface basePresenter;
    private RecyclerView.LayoutManager mLayoutManager;
    StikkyHeaderRecyclerView headder;

    @Override
    public ProfilePresenterInterface getBasePresenter() {
        return basePresenter;
    }

    @Override
    public void setBasePresenter(BasePresenterInterface basePresenter) {
        this.basePresenter = (ProfilePresenterInterface) basePresenter;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        reviewBtn.setOnClickListener(this);
        subscribers.setOnClickListener(this);
        subscriptions.setOnClickListener(this);
        mPopupBtn.setOnClickListener(this);
        profileIcon.setOnClickListener(this);

    }

    private void setStars(int count){
        for (int i = 0; i< count; i++){
            Glide
                .with(getContext())
                .load(R.drawable.star)
                .skipMemoryCache(false)
                .into(this.stars[i]);
        }
    }

    private void setHeaderAnimation(){
        if (headder == null)
            headder = (StikkyHeaderRecyclerView) StikkyHeaderBuilder.RecyclerViewBuilder.stickTo(mRecyclerView)
                .setHeader(R.id.header_profile, (ViewGroup) getView())
                .minHeightHeaderDim(R.dimen.min_height_header_profile)
                .animator(new ParallaxStikkyAnimator())
                .build();
    }

    public void setRecyclerAdapter(ProfileAdapter mAdapter){
        assert mRecyclerView != null;
        mAdapter.setHasStableIds(true);
        mRecyclerView.setItemViewCacheSize(50);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        setHeaderAnimation();
    }

    @Override
    public void updateWithUseritem(UserViewItem item) {
        //вот тут нахуячивай инфо о юзере
        subscribers.setText(String.valueOf(item.getSubs()));
        subscriptions.setText(String.valueOf(item.getSubrs()));
        titleName.setText(String.valueOf(item.getName()));
        aboutMe.setText(String.valueOf(item.getDescription()));
        publications.setText(String.valueOf(item.getEvents_count()));
        setStars(item.getRepliesSum());
        repliesCount.setText(String.valueOf("("+item.getRepliesCount()+")"));
        avatar = item.getAvatar();
        Glide
        .with(getContext())
            .load(item.getBanner())
            .thumbnail( 0.2f )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .into(mHeader_image);

        Glide
            .with(getContext())
            .load(item.getAvatar())
            .bitmapTransform(new CropCircleTransformation(getContext()))
            .thumbnail( 0.1f )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .dontAnimate()
            .into(profileIcon);

        payload = new HashMap<String, Object>();
        int ide = item.getId();
        payload.put("userID", ide);

        Log.d(TAG, "updateWithUseritem: ");
    }

    @Override
    public void updateWithEvents(List<EventViewItem> items) {
        //вот тут нахуячивай данные событий
        Log.d(TAG, "updateWithEvents: ");
    }

    @Override
    public HashMap<String, Object> getPayload() {
        return this.payload;
    }

    @Override
    public void openFullscreenGallery(Photo[] photos, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("photos", photos);
        bundle.putInt("position", position);

        FragmentTransaction ft = ((MainActivity) getContext()).getSupportFragmentManager().beginTransaction();
        GalleryFullscreen newFragment = GalleryFullscreen.newInstance();
        newFragment.setArguments(bundle);
        newFragment.show(ft, "slideshow");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public int[] getImagesArray() {
        return new int[]{
            R.drawable.settings,
            0,
            R.drawable.cross,
            0,
            R.drawable.chekin
        };
    }

    public String[] getPopupPositionsAray(){
        return new String[]{
            "Хуй",
            "Пизда",
            "Джигурда"
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.profile_popup_menu) {
            basePresenter.initWithButtonAndStringArray(v, getPopupPositionsAray());
            return;
        }

        if (v.getId() == R.id.profile_icon){
            Photo photo = new Photo();
            photo.setPhoto(avatar);
            Photo[] photos = {photo};

            Bundle bundle = new Bundle();
            bundle.putSerializable("photos", photos);
            bundle.putInt("position", 0);

            FragmentTransaction ft = ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction();
            GalleryFullscreen newFragment = GalleryFullscreen.newInstance();
            newFragment.setArguments(bundle);
            newFragment.show(ft, "slideshow");
        }

        basePresenter.setProfileScreen(v.getId(), payload);
    }



    private class ParallaxStikkyAnimator extends HeaderStikkyAnimator {
        @Override
        public AnimatorBuilder getAnimatorBuilder() {
            return AnimatorBuilder.create()
                .applyVerticalParallax(mHeader_image);
        }
    }
}
