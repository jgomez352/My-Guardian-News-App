package com.egsbusinesssolutions.josesguardiannewsapp;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jose F. Gomez on 10/14/2018.
 */
public class NewsAdapter extends ArrayAdapter<News> {

    private int getSectionIdColor(String mSectionId) {

        int SectionColorId;
        switch (mSectionId) {
            case "news":
                SectionColorId = R.color.newsColor;
                break;
            case "politics":
                SectionColorId = R.color.politicsColor;
                break;
            case "technology":
                SectionColorId = R.color.technologyColor;
                break;
            default:
                SectionColorId = R.color.otherColor;
                break;
        }
        return ContextCompat.getColor(getContext(), SectionColorId);
    }

    private String getSectionIdType(String mSectionId) {
        String SectionIdType;
        switch (mSectionId) {
            case "news":
                SectionIdType = "N";
                break;
            case "politics":
                SectionIdType = "P";
                break;
            case "technology":
                SectionIdType = "T";
                break;
            default:
                SectionIdType = "O";
                break;
        }
        return SectionIdType;
    }

    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_list_item, parent, false);
        }

        News currentNews = getItem(position);

        //This part handles the sectionId also knows as category (news, technology, politics, or other)
        TextView sectionIdView = (TextView) listItemView.findViewById(R.id.sectionId);
        String SectionIdType = getSectionIdType(currentNews.getmSectionId());
        sectionIdView.setText(SectionIdType);
        GradientDrawable typeCircle = (GradientDrawable) sectionIdView.getBackground();
        int sectionIdColor = getSectionIdColor(currentNews.getmSectionId());
        typeCircle.setColor(sectionIdColor);

        TextView ByLineView = (TextView) listItemView.findViewById(R.id.byline);
        ByLineView.setText(currentNews.getmByLine());

        TextView HeadLineView = (TextView) listItemView.findViewById(R.id.headline);
        HeadLineView.setText(currentNews.getmHeadLine());

        TextView WebDate_Date = (TextView) listItemView.findViewById(R.id.webPublicationDate_date);

        WebDate_Date.setText(currentNews.getmDate());

        TextView WebDate_Time = (TextView) listItemView.findViewById(R.id.webPublicationDate_time);
        WebDate_Time.setText(currentNews.getTime());

        return listItemView;
    }
}
