package com.ricardogwill.circleimagerecyclerviewwithintent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context mContext;
    // OnRecyclerViewListener is needed now to navigate to GalleryActivity.java.
    private OnRecyclerViewListener mOnRecyclerViewListener;

    public RecyclerViewAdapter(ArrayList<String> mImageNames, ArrayList<String> mImages, Context mContext, OnRecyclerViewListener mOnRecyclerViewListener) {
        this.mImageNames = mImageNames;
        this.mImages = mImages;
        this.mContext = mContext;
        this.mOnRecyclerViewListener = mOnRecyclerViewListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view, mOnRecyclerViewListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext).asBitmap().load(mImages.get(i)).into(viewHolder.image);

        viewHolder.imageName.setText(mImageNames.get(i));

        // Note: The following is commented out because this OnClickListener (for a Toast)
        // will overcome the other OnClickListener that takes us to GalleryActivity.

//        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Log.d(TAG, "onClick: clicked on: " + mImageNames.get(i));
//
//                Toast.makeText(mContext, mImageNames.get(i), Toast.LENGTH_SHORT).show();
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    // This implements an "OnClickListener" so that clicking can navigate to activity_gallery.xml.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CircleImageView image;
        TextView imageName;
        ConstraintLayout parentLayout;
        // Below is for the "OnClickListener".
        OnRecyclerViewListener onRecyclerViewListener;

        // The OnRecyclerViewListener is added as the second argument in the method below.
        public ViewHolder(@NonNull View itemView, OnRecyclerViewListener onRecyclerViewListener) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            imageName = itemView.findViewById(R.id.image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            // Here is the constructor for onRecyclerViewListener.
            this.onRecyclerViewListener = onRecyclerViewListener;

            // "this" refers to the implemented interface above.
            itemView.setOnClickListener(this);

        }

        // This is the "onClick" method that must be implemented (based on View.OnClickListener implementation above).
        @Override
        public void onClick(View view) {

            onRecyclerViewListener.onRecyclerViewClick(getAdapterPosition());

        }
    }

    // Interface for OnClick Intent
    public interface OnRecyclerViewListener{
        void onRecyclerViewClick(int i);
    }

}
