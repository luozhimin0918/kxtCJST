package com.kxt.kxtcjst.index.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.kxt.kxtcjst.R;
import com.kxt.kxtcjst.common.coustom.MyTabView;
import com.kxt.kxtcjst.common.utils.BaseUtils;
import com.kxt.kxtcjst.common.utils.EventType;
import com.kxt.kxtcjst.index.SuperPlayerActivity;
import com.kxt.kxtcjst.index.jsonBean.VedioBean;
import com.kxt.kxtcjst.index.jsonBean.VideoDetails;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/21 0021.
 */

public class VideoListAdapter extends BaseAdapter {
    private Context context;
    private List<VideoDetails.DataBean.ListBean> dataBeans;

    public VideoListAdapter(Context context,List<VideoDetails.DataBean.ListBean> listBeen) {
        this.dataBeans = listBeen;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return dataBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (null == convertView) {
            convertView = View.inflate(context, R.layout.item_test_tj, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.sp_title.setText(dataBeans.get(position).getTitle());
      /*  String reads = SpConstant.getReadPreferences().getString(dataBeans.get(position).getId(), "");
        if (null != reads && !"".equals(reads)) {
            viewHolder.newTitle.setTextColor(context.getResources().getColor(R.color.font_bb2));
        } else {
            viewHolder.newTitle.setTextColor(context.getResources().getColor(R.color.text_title));
        }*/
        try {
            String time = BaseUtils.getDateBySjc(dataBeans.get(position).getPublish_time()).substring(0,10);
            viewHolder.sp_timelong.setText(time);
        } catch (Exception e) {
            e.getMessage();
            viewHolder.sp_timelong.setText("");
        }

        viewHolder.sp_times.setText(dataBeans.get(position).getPlay_count());
        viewHolder.sp_img.setImageDrawable(context.getResources().getDrawable(R.drawable.empty_photo_jiu_big));
        Glide.with(context)
                .load(dataBeans.get(position).getPicture())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        viewHolder.sp_img.setImageBitmap(resource);
                    }
                });

       String tags =  dataBeans.get(position).getCate_name();
        viewHolder.news_tab.removeAllViews();

            MyTabView myTabView = new MyTabView(context);
            if (null != tags&& !"".equals(tags)) {

                    myTabView.setTabStyle(true, tags);

                viewHolder.news_tab.addView(myTabView);
            }


        viewHolder.item_video_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(EventType.POST_USER_NAME.setObject(dataBeans.get(position).getId()));
//                Intent intent = new Intent(context, SuperPlayerActivity.class);
//                intent.putExtra("id", dataBeans.get(position).getId());
//                context.startActivity(intent);
              /*  SpConstant.getReadPreferences().edit().putString(dataBeans.get(position).getId(), dataBeans.get(position).getId()).commit();
                viewHolder.newTitle.setTextColor(context.getResources().getColor(R.color.font_bb2));
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("from", "news");
                intent.putExtra("url", dataBeans.get(position).getUrl());
                intent.putExtra("title", "要闻");
                context.startActivity(intent);*/
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.sp_img)
        ImageView sp_img;
        @BindView(R.id.sp_title)
        TextView sp_title;
        @BindView(R.id.sp_timelong)
        TextView sp_timelong;
        @BindView(R.id.sp_times)
        TextView sp_times;
        @BindView(R.id.item_video_root)
        LinearLayout item_video_root;
        @BindView(R.id.news_tab)
        LinearLayout news_tab;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
