package com.egsbusinesssolutions.josesguardiannewsapp;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final int NEWS_LOADER_ID = 1;

    private static final String GUARDIAN_NEWS_URL = "https://content.guardianapis.com/search";

    /** here is the rest of the URL for testing purposes
    * ?&q=&production-office=us&from-date=2018-01-01&order-by=newest&lang=en&section=politics&show-fields=headline,byline,thumbnail&api-key=test";
    */

    private NewsAdapter mAdapter;

    private TextView EmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This four fields control the colors of the categories at the top of the activity_main.xml
        TextView TypeCircleP = (TextView) findViewById(R.id.typeP);
        GradientDrawable typeCircleP = (GradientDrawable) TypeCircleP.getBackground();
        typeCircleP.setColor(getResources().getColor(R.color.politicsColor));

        TextView TypeCircleT = (TextView) findViewById(R.id.typeT);
        GradientDrawable typeCircleT = (GradientDrawable) TypeCircleT.getBackground();
        typeCircleT.setColor(getResources().getColor(R.color.technologyColor));

        TextView TypeCircleN = (TextView) findViewById(R.id.typeN);
        GradientDrawable typeCircleN = (GradientDrawable) TypeCircleN.getBackground();
        typeCircleN.setColor(getResources().getColor(R.color.newsColor));

        TextView TypeCircleO = (TextView) findViewById(R.id.typeO);
        GradientDrawable typeCircleO = (GradientDrawable) TypeCircleO.getBackground();
        typeCircleO.setColor(getResources().getColor(R.color.otherColor));

        //get refrence toConnectivityManager to check for state of network connectivity
        ConnectivityManager MyConnMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        //get deatails on current activity default data network
        NetworkInfo networkInfo = MyConnMgr.getActiveNetworkInfo();


        ListView NewslistView = (ListView) this.findViewById(R.id.recycle_list);

        EmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        NewslistView.setEmptyView(EmptyStateTextView);


        //This part handles the situation where there is no network
        if (networkInfo != null && networkInfo.isConnected()) {


            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            EmptyStateTextView.setText(R.string.noInternet);
        }

        mAdapter = new NewsAdapter(this, new ArrayList<News>());

        NewslistView.setAdapter(mAdapter);


        NewslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News currentNews = mAdapter.getItem(position);

                Uri newsUri = Uri.parse(currentNews.getmUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                startActivity(websiteIntent);
            }
        });

    }




    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {

        //Here the the uriBuilder param from-date is handled to make sure it pulls only 365 days worth of news
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        currentDate.add(Calendar.DAY_OF_YEAR, -365);
        String UriParamDate = dateFormat.format(new Date(currentDate.getTimeInMillis()));

        //This part helps handle the no filter setting
        String noFilterSetting;
        noFilterSetting = getResources().getString(R.string.settings_filter_to_no_filter_value);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //getString retrieves a String value from the preferences. The second parameter is the default value for this preference.
        String filterTo = sharedPreferences.getString(
                getString(R.string.settings_filter_to_key),
                getString(R.string.settings_filter_to_default));

        String sortBy =sharedPreferences.getString(
                getString(R.string.settings_sort_by_key),
                getString(R.string.settings_sort_by_default));


        // parse breaks apart the URI string that's passed into its parameter
        Uri baseUri = Uri.parse(GUARDIAN_NEWS_URL);

        // buildUpon prepares the baseUri that we just parsed so we can add query parameters to it
        Uri.Builder uriBuilder = baseUri.buildUpon();

        if(filterTo.equals(noFilterSetting)){
            // Append query parameter and its value
            uriBuilder.appendQueryParameter("q","");
            uriBuilder.appendQueryParameter("production-office","us");
            uriBuilder.appendQueryParameter("from-date",UriParamDate);
            uriBuilder.appendQueryParameter("order-by",sortBy);
            uriBuilder.appendQueryParameter("lang","en");
            uriBuilder.appendQueryParameter("show-fields","headline,byline,thumbnail");
            uriBuilder.appendQueryParameter("api-key","test");
        }else{
            // Append query parameter and its value
            uriBuilder.appendQueryParameter("q","");
            uriBuilder.appendQueryParameter("production-office","us");
            uriBuilder.appendQueryParameter("from-date",UriParamDate);
            uriBuilder.appendQueryParameter("order-by",sortBy);
            uriBuilder.appendQueryParameter("lang","en");
            uriBuilder.appendQueryParameter("section",filterTo);
            uriBuilder.appendQueryParameter("show-fields","headline,byline,thumbnail");
            uriBuilder.appendQueryParameter("api-key","test");
        }
        // Create a new loader for the given URL
        return new NewsLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {

        //Here the loading circle is turned invincible
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        EmptyStateTextView.setText(R.string.NoNews);
        mAdapter.clear();

        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();

    }

    // This method initialize the contents of the Activity's options menu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the Options Menu specified in XML
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    //This method is where we can setup the specific action that occurs when any of the items in the Options Menu are selected.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        //if id is the same as action_settings then it will start the Settings Activity
        if (id == R.id.action_settings){
            Intent settingsIntent = new  Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
