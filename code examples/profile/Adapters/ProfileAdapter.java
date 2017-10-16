package ru.myaround.egorshashkov.around.screens.profile.Adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.myaround.egorshashkov.around.R;
import ru.myaround.egorshashkov.around.base.RecyclerItemClickListener;
import ru.myaround.egorshashkov.around.models.Photo;
import ru.myaround.egorshashkov.around.screens.profile.ProfileFragment;
import ru.myaround.egorshashkov.around.screens.profile.screens.checkin.CheckinFragment;
import ru.myaround.egorshashkov.around.viewitems.EventViewItem;

/**
 * Created by RudenskyN on 022 22.04.17.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolderProfile> {

    ProfileFragment profileFragment;
    CheckinFragment checkinFragment;
    List<EventViewItem> items;
     
    public static class ViewHolderProfile extends RecyclerView.ViewHolder {

        @BindView(R.id.profileEventTitle)
        TextView eventTitle;
        @BindView(R.id.profileEventTime)
        TextView time;
        @BindView(R.id.profileEventCountComments)
        TextView comments;
        @BindView(R.id.profileEventCountLike)
        TextView like;
        @BindView(R.id.profileEventCountDislike)
        TextView dislike;
        @BindView(R.id.profileEventDay)
        TextView eventDay;
        @BindView(R.id.profileEventMonth)
        TextView eventMonth;
        @BindView(R.id.my_gallery)
        RecyclerView galleryView;
        @BindView(R.id.galley_content)
        LinearLayout galleryContent;

        private GalleryAdapter galleryAdapter;

        public ViewHolderProfile(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            galleryView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            galleryAdapter = new GalleryAdapter();
            galleryAdapter.hasStableIds();
            galleryView.setHasFixedSize(true);
            galleryView.setItemViewCacheSize(50);
            galleryView.setDrawingCacheEnabled(true);
            galleryView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
            galleryView.setAdapter(galleryAdapter);
        }
    }
    // Конструктор
    public ProfileAdapter(List<EventViewItem> items, ProfileFragment profileFragment) {
        this.profileFragment = profileFragment;
        this.items = items;
    }

    public ProfileAdapter(List<EventViewItem> items, CheckinFragment checkinFragment) {
        this.checkinFragment = checkinFragment;
        this.items = items;
    }

    public void setItems(List<EventViewItem> items){
        this.items = items;
    }

    public EventViewItem getCurrentItem(int position){
        return items.get(position);
    }

    @Override
    public ProfileAdapter.ViewHolderProfile onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_profile_events, parent, false);

        ViewHolderProfile holder = new ViewHolderProfile(view);

        holder.itemView.setOnClickListener(v -> {
            HashMap<String, Object> payload = new HashMap<String, Object>();
            int ide = getCurrentItem(holder.getAdapterPosition()).getId();
            payload.put("eventID", ide);
            profileFragment.basePresenter.setEventScreen(payload);
        });

        return holder;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolderProfile holder, int position) {

        EventViewItem item = items.get(position);
        holder.eventDay.setText(String.valueOf(item.getDayStr()));
        holder.eventMonth.setText(String.valueOf(item.getMonthStr()));
        holder.eventTitle.setText(String.valueOf(item.getTitle()));
        holder.time.setText(String.valueOf("С " + String.valueOf(item.getStartTimeStr()) + " До " + String.valueOf(item.getEndTimeStr())));
        holder.comments.setText(String.valueOf(item.getCountComments()));
        holder.like.setText(String.valueOf(item.getCountLike()));
        holder.dislike.setText(String.valueOf(item.getCountDislike()));

        if (item.getPhotos().length != 0){
            holder.galleryAdapter.setData(item.getPhotos(), profileFragment);
            holder.galleryAdapter.notifyDataSetChanged();
        }else {
            holder.galleryContent.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        if (items != null)
            return items.size();
        else
            return 0;
    }



}
