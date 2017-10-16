package ru.myaround.egorshashkov.around.screens.profile.screens.replies.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import ru.myaround.egorshashkov.around.R;
import ru.myaround.egorshashkov.around.viewitems.ReplyViewItem;

import static ru.myaround.egorshashkov.around.R.drawable.star_big;

/**
 * Created by RudenskyN on 022 22.04.17.
 */

public class RepliesAdapter extends RecyclerView.Adapter<RepliesAdapter.ViewHolderReplies>{


    LinearLayout myGallery;
    List<ReplyViewItem> replyViewItems;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public class ViewHolderReplies extends RecyclerView.ViewHolder {

        @BindView(R.id.reply_avatar)
        ImageView replyAvatar;
        @BindView(R.id.reply_nick)
        TextView replyNick;
        @BindView(R.id.reply_title)
        TextView replyTitle;
        @BindView(R.id.reply_comment)
        TextView replyComment;

        @BindView(R.id.reply_one_star)
        ImageView oneStar;
        @BindView(R.id.reply_two_star)
        ImageView twoStar;
        @BindView(R.id.reply_three_star)
        ImageView threeStar;
        @BindView(R.id.reply_four_star)
        ImageView fourStar;
        @BindView(R.id.reply_five_star)
        ImageView fiveStar;

        ImageView[] stars = {oneStar, twoStar, threeStar, fourStar, fiveStar};

        public ViewHolderReplies(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    // Конструктор
    public RepliesAdapter(List<ReplyViewItem> replyViewItems) {
       this.replyViewItems = replyViewItems;
    }

    @Override
    public ViewHolderReplies onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_list_review_item, parent, false);

        ViewHolderReplies vh = new ViewHolderReplies(view);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolderReplies holder, int position) {
        ReplyViewItem replyViewItem = replyViewItems.get(position);
        Glide
            .with(holder.itemView.getContext())
            .load(replyViewItem.getUser().getAvatar())
            .bitmapTransform(new CropCircleTransformation(holder.itemView.getContext()))
            .thumbnail( 0.1f )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .dontAnimate()
            .into(holder.replyAvatar);
        holder.replyNick.setText(String.valueOf(replyViewItem.getUser().getUsername()));
        holder.replyTitle.setText(String.valueOf(replyViewItem.getTitle()));
        holder.replyComment.setText(String.valueOf(replyViewItem.getComment()));

        switch (replyViewItem.getStars()){
            case 1:
                setStars(1, holder);
                break;
            case 2:
                setStars(2, holder);
                break;
            case 3:
                setStars(3, holder);
                break;
            case 4:
                setStars(4, holder);
                break;
            case 5:
                setStars(5, holder);
                break;
        }
    }

    private void setStars(int count, ViewHolderReplies holder){
        for (int i = 0; i< count; i++){
            Glide
                .with(holder.itemView.getContext())
                .load(R.drawable.star_big)
                .skipMemoryCache(false)
                .into(holder.stars[i]);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return replyViewItems.size();
    }



}
