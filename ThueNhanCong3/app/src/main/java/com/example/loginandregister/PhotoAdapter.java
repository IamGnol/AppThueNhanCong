package com.example.loginandregister;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {

    private Context mcontext;
    private List<Photo> listphoto;

    public PhotoAdapter(Context mcontext, List<Photo> listphoto) {
        this.mcontext = mcontext;
        this.listphoto = listphoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.itemanh,container,false);

        ImageView imagPhoto= view.findViewById(R.id.imganhlide);
        Photo photo=listphoto.get(position);

        if(photo!=null){
            Glide.with(mcontext).load(photo.getResourceId()).into(imagPhoto);
        }
        //add view to ViewGroup
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(listphoto !=null){
            return listphoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //Remove View
        container.removeView((View) object);
    }
}
