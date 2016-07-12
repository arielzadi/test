package com.youtube_list.adapt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.youtube_list.R;
import com.youtube_list.pojo.YouTubeItem;
import com.youtube_list.pojo.YouTubeListItems;

import java.util.List;

/**
 * Created by Mysc on 13.5.2016.
 */
public class YouTubeItemsAdapter extends ArrayAdapter<YouTubeItem> {

    private Context context;
    private List<YouTubeItem> list;
    private LayoutInflater inflater;

    public YouTubeItemsAdapter(Context context, List<YouTubeItem> list){
        super(context, R.layout.item_you_tube, list);
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    class Holder{
        TextView tv_video_name;
        ImageView thumbnail_image;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        Holder holder;
        if (rowView == null){
            holder = new Holder();
            rowView = inflater.inflate(R.layout.item_you_tube, null, false);
            holder.tv_video_name = (TextView) rowView.findViewById(R.id.tv_video_name);
            holder.thumbnail_image = (ImageView) rowView.findViewById(R.id.thumbnail_image);
            rowView.setTag(holder);
        } else {
            holder = (Holder) rowView.getTag();
        }
        if (list != null && position < list.size()){

            YouTubeItem youTubeItem = list.get(position);

            holder.tv_video_name.setText(youTubeItem.getTitle());

            if (youTubeItem.getThumb()!=null && youTubeItem.getThumb().length()>0){
                Picasso.with(context).load(youTubeItem.getThumb()).into(holder.thumbnail_image);

            }else {
                holder.thumbnail_image.setImageResource(R.mipmap.ic_launcher);
            }


        }
        return rowView;
    }

}
