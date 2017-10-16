package ru.myaround.egorshashkov.around.screens.profile.screens.subscribers.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import ru.myaround.egorshashkov.around.R;
import ru.myaround.egorshashkov.around.viewitems.UserViewItem;

/**
 * Created by RudenskyN on 022 22.04.17.
 */

public class SubscribersAdapter extends RecyclerView.Adapter<SubscribersAdapter.ViewHolderSubscribers>{

    List<UserViewItem> userViewItems;

    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    public class ViewHolderSubscribers extends RecyclerView.ViewHolder {

        @BindView(R.id.subs_avatar)
        ImageView subsAvatar;
        @BindView(R.id.subs_name)
        TextView subsName;

        public ViewHolderSubscribers(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    // Конструктор
    public SubscribersAdapter(List<UserViewItem> userViewItems) {
        this.userViewItems = userViewItems;
    }

    @Override
    public ViewHolderSubscribers onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_subscribers_item, parent, false);

        ViewHolderSubscribers vh = new ViewHolderSubscribers(view);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolderSubscribers holder, int position) {
        UserViewItem item  = this.userViewItems.get(position);
        Glide
            .with(holder.itemView.getContext())
            .load(item.getAvatar())
            .bitmapTransform(new CropCircleTransformation(holder.itemView.getContext()))
            .thumbnail( 0.1f )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(false)
            .dontAnimate()
            .into(holder.subsAvatar);
        holder.subsName.setText(String.valueOf(item.getName()));
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return this.userViewItems.size();
    }



}
