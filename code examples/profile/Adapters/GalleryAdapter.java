package ru.myaround.egorshashkov.around.screens.profile.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import ru.myaround.egorshashkov.around.MainActivity;
import ru.myaround.egorshashkov.around.R;
import ru.myaround.egorshashkov.around.gallery.GalleryFullscreen;
import ru.myaround.egorshashkov.around.models.Photo;
import ru.myaround.egorshashkov.around.screens.profile.ProfileView;
import ru.myaround.egorshashkov.around.viewitems.EventViewItem;

/**
 * Created by RudenskyN on 014 14.05.17.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolderGallery> {

    private Photo[] photos;
    Context context;
    ProfileView profileView;

    public class ViewHolderGallery extends RecyclerView.ViewHolder {
        @BindView(R.id.gallery_image_item)
        ImageView galleryItem;
        @BindView(R.id.profile_gallery_play_video)
        ImageView playVideo;

        public ViewHolderGallery(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }
    }


    public void setData(Photo[] photos, ProfileView profileView){
        this.photos = photos;
        this.profileView = profileView;
    }

    public GalleryAdapter(){

    }

    @Override
    public ViewHolderGallery onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_profile_events_gallery_item, parent, false);

        GalleryAdapter.ViewHolderGallery vh = new GalleryAdapter.ViewHolderGallery(view);

        vh.galleryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileView.openFullscreenGallery(photos, vh.getAdapterPosition());
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolderGallery holder, int position) {

        Photo photo = photos[position];
        if (photo.getVideo() == 1){
            holder.playVideo.setVisibility(View.VISIBLE);
        }
        Glide
            .with(context)
            .load(photo.getPhoto())
            .thumbnail( 0.1f )
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true)
            .priority(Priority.LOW)
            .into(holder.galleryItem);
    }

    @Override
    public int getItemCount() {
        if (photos != null)
            return photos.length;
        else
            return 0;
    }


}
