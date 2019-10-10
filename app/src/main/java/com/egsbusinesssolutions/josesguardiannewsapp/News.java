package com.egsbusinesssolutions.josesguardiannewsapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by Jose F. Gomez on 10/14/2018.
 */
public class News {
    private String mSectionId;
    private String mHeadLine;
    private String mByLine;
    private String mUrl;
    private String mDate;

    private String formatDate(String webPublicationDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd yyyy");
        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(webPublicationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dateToDisplay = dateFormatter.format(dateObject);
        return dateToDisplay;
    }

    private String formatTime(String webPublicationDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a");

        Date dateObject = null;
        try {
            dateObject = simpleDateFormat.parse(webPublicationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String TimeToDisplay = dateFormatter.format(dateObject);
        return TimeToDisplay;
    }

    /**
     * Constructs a new {@link News} object
     * @param SectionId
     * @param HeadLine
     * @param ByLine
     * @param Url
     * @param webPublicationDate
     */
    public News(String SectionId, String HeadLine, String ByLine, String Url, String webPublicationDate) {
        mByLine = ByLine;
        mHeadLine = HeadLine;
        mSectionId = SectionId;
        mUrl = Url;
        mDate = webPublicationDate;
    }

    public String getmSectionId() {
        return mSectionId;
    }

    public String getmHeadLine() {
        return mHeadLine;
    }

    public String getmByLine() {
        return mByLine;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmDate() {
        return formatDate(mDate);
    }

    public String getTime() {
        return formatTime(mDate);
    }

}
