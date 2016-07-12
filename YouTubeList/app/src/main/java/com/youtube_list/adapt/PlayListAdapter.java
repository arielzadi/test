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
import com.youtube_list.pojo.YouTubeListItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mysc on 13.5.2016.
 */
public class PlayListAdapter extends ArrayAdapter<YouTubeListItems> {

    private Context context;
    private List<YouTubeListItems> list;
    private LayoutInflater inflater;

    public PlayListAdapter(Context context, List<YouTubeListItems> list){
        super(context, R.layout.item_you_tube_lists, list);
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    class Holder{
        TextView tv_youtube_name;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        Holder holder;
        if (rowView == null){
            holder = new Holder();
            rowView = inflater.inflate(R.layout.item_you_tube_lists, null, false);
            holder.tv_youtube_name = (TextView) rowView.findViewById(R.id.tv_youtube_name);
            rowView.setTag(holder);
        } else {
            holder = (Holder) rowView.getTag();
        }
        if (list != null && position < list.size()){

            YouTubeListItems youTubeListItems = list.get(position);

            holder.tv_youtube_name.setText(youTubeListItems.getListTitle());

        }
        return rowView;
    }

}
