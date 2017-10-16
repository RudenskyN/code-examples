package ru.myaround.egorshashkov.around.screens.profile.screens.сreateReview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import ru.myaround.egorshashkov.around.R;
import ru.myaround.egorshashkov.around.base.BaseMainFragment;
import ru.myaround.egorshashkov.around.base.BasePresenterInterface;
import ru.myaround.egorshashkov.around.base.Layout;
import ru.myaround.egorshashkov.around.tabbar.helpers.JellyInterpolator;


/**
 * Eventd by egor.shashkov on 19/04/2017.
 */
@Layout(id = R.layout.fragment_create_review)
public class CreateReviewFragment extends BaseMainFragment implements CreateReviewView, View.OnClickListener {
    @Nullable

    @BindView(R.id.star_1)
    ImageButton starBtn1;
    @BindView(R.id.star_2)
    ImageButton starBtn2;
    @BindView(R.id.star_3)
    ImageButton starBtn3;
    @BindView(R.id.star_4)
    ImageButton starBtn4;
    @BindView(R.id.star_5)
    ImageButton starBtn5;

    ImageButton[] stars;

    public CreateReviewPresenterInterface basePresenter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public CreateReviewPresenterInterface getBasePresenter() {
        return basePresenter;
    }

    @Override
    public void setBasePresenter(BasePresenterInterface basePresenter) {
        this.basePresenter = (CreateReviewPresenterInterface) basePresenter;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        starBtn1.setOnClickListener(this);
        starBtn2.setOnClickListener(this);
        starBtn3.setOnClickListener(this);
        starBtn4.setOnClickListener(this);
        starBtn5.setOnClickListener(this);
    }


    public void setRecyclerAdapter(RecyclerView.Adapter mAdapter){
    }

    public void setStar(View view){
        clearAllStars();
        switch (view.getId()){
            case R.id.star_1:
                stars = new ImageButton[]{starBtn1};
                socialBtnAnimate(stars);
                break;
            case R.id.star_2:
                stars = new ImageButton[]{starBtn1, starBtn2};
                socialBtnAnimate(stars);
                break;
            case R.id.star_3:
                stars = new ImageButton[]{starBtn1, starBtn2, starBtn3};
                socialBtnAnimate(stars);
                break;
            case R.id.star_4:
                stars = new ImageButton[]{starBtn1, starBtn2, starBtn3, starBtn4};
                socialBtnAnimate(stars);
                break;
            case R.id.star_5:
                stars = new ImageButton[]{starBtn1, starBtn2, starBtn3, starBtn4, starBtn5};
                socialBtnAnimate(stars);
                break;
        }
    }

    public void setGoldStar(ImageButton star){
            star.setImageResource(R.drawable.star_big);
    }

    private void socialBtnAnimate(ImageButton[] stars){
        for (int i= 0; i<stars.length; i++){
            ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
            final ImageButton star = stars[i];
            int delayCoef = Math.abs(stars.length - i);
            animator.addUpdateListener(animation -> {
                float value = (float) animation.getAnimatedValue();
                star.setScaleX(1 + 0.3f * value);
                star.setScaleY(1 + 0.3f * value);
            });
            animator.setDuration(250);
            animator.setStartDelay(i*50);
            animator.setInterpolator(new JellyInterpolator());
            animator.start();
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    socialBtnAnimateCollapse(animator, star);
                    setGoldStar(star);
                }
            });
        }
    }

    private void socialBtnAnimateCollapse(ValueAnimator animator, ImageButton star){
        animator = ValueAnimator.ofFloat(0, 1);
        animator.setInterpolator(new JellyInterpolator());
        animator  = ValueAnimator.ofFloat(1, 0);
        animator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            star.setScaleX(1 + 0.3f * value);
            star.setScaleY(1 + 0.3f * value);
        });
        animator.setDuration(250);
        animator.start();
    }

    public void clearAllStars(){
        stars = null;
        starBtn1.setImageResource(R.drawable.star_empty_big);
        starBtn2.setImageResource(R.drawable.star_empty_big);
        starBtn3.setImageResource(R.drawable.star_empty_big);
        starBtn4.setImageResource(R.drawable.star_empty_big);
        starBtn5.setImageResource(R.drawable.star_empty_big);
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
            R.drawable.ok,
            0,
            R.drawable.cross
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
        setStar(v);

    }
}
