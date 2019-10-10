package com.egsbusinesssolutions.josesguardiannewsapp;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose F. Gomez on 10/14/2018.
 */
public final class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();
//    private static final String SAMPLE_JSON_RESPONCE =" {\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":2617,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":262,\"orderBy\":\"newest\",\"results\":[{\"id\":\"technology/2018/oct/16/coinbase-cryptocurrency-exchange-hard-brexit-opening-dublin-offices\",\"type\":\"article\",\"sectionId\":\"technology\",\"sectionName\":\"Technology\",\"webPublicationDate\":\"2018-10-15T23:01:08Z\",\"webTitle\":\"Coinbase plans for hard Brexit by opening Dublin offices\",\"webUrl\":\"https://www.theguardian.com/technology/2018/oct/16/coinbase-cryptocurrency-exchange-hard-brexit-opening-dublin-offices\",\"apiUrl\":\"https://content.guardianapis.com/technology/2018/oct/16/coinbase-cryptocurrency-exchange-hard-brexit-opening-dublin-offices\",\"fields\":{\"headline\":\"Coinbase plans for hard Brexit by opening Dublin offices\",\"byline\":\"Alex Hern\",\"thumbnail\":\"https://media.guim.co.uk/20fb7d82797610cf8c794ae2239b12561c5a88b1/0_170_5052_3030/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"uk-news/2018/oct/16/asylum-seekers-could-contribute-42m-to-uk-if-work-rules-relaxed\",\"type\":\"article\",\"sectionId\":\"uk-news\",\"sectionName\":\"UK news\",\"webPublicationDate\":\"2018-10-15T23:01:07Z\",\"webTitle\":\"Asylum seekers 'could contribute £42m to UK' if work rules relaxed\",\"webUrl\":\"https://www.theguardian.com/uk-news/2018/oct/16/asylum-seekers-could-contribute-42m-to-uk-if-work-rules-relaxed\",\"apiUrl\":\"https://content.guardianapis.com/uk-news/2018/oct/16/asylum-seekers-could-contribute-42m-to-uk-if-work-rules-relaxed\",\"fields\":{\"headline\":\"Asylum seekers 'could contribute £42m to UK' if work rules relaxed\",\"byline\":\"Jamie Grierson Home affairs correspondent\",\"thumbnail\":\"https://media.guim.co.uk/95b11f3cf5d9b858ed09541cddca35eefb968e4a/0_148_2835_1701/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"business/2018/oct/15/a-shared-passion-for-post-offices\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2018-10-15T15:56:46Z\",\"webTitle\":\"A shared passion for post offices | Letters\",\"webUrl\":\"https://www.theguardian.com/business/2018/oct/15/a-shared-passion-for-post-offices\",\"apiUrl\":\"https://content.guardianapis.com/business/2018/oct/15/a-shared-passion-for-post-offices\",\"fields\":{\"headline\":\"A shared passion for post offices\",\"byline\":\"Letters\",\"thumbnail\":\"https://media.guim.co.uk/cc7c7af7c2266657875ba9918655f13fe4c50085/0_192_5760_3456/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"tv-and-radio/2018/oct/15/john-oliver-last-week-tonight-recap-trump-saudi-arabia-khashoggi\",\"type\":\"article\",\"sectionId\":\"tv-and-radio\",\"sectionName\":\"Television & radio\",\"webPublicationDate\":\"2018-10-15T15:26:06Z\",\"webTitle\":\"John Oliver: Trump and Bin Salman are 'similar in some of the worst ways'\",\"webUrl\":\"https://www.theguardian.com/tv-and-radio/2018/oct/15/john-oliver-last-week-tonight-recap-trump-saudi-arabia-khashoggi\",\"apiUrl\":\"https://content.guardianapis.com/tv-and-radio/2018/oct/15/john-oliver-last-week-tonight-recap-trump-saudi-arabia-khashoggi\",\"fields\":{\"headline\":\"John Oliver: Trump and Bin Salman are 'similar in some of the worst ways'\",\"byline\":\"Guardian staff\",\"thumbnail\":\"https://media.guim.co.uk/45581bf46b844d9368d9e9e2e1b06676e4dd2d9b/81_0_1618_971/500.png\"},\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"lifeandstyle/2018/oct/15/cannabis-health-products-live-up-to-hype-cannabidiol-cbd\",\"type\":\"article\",\"sectionId\":\"lifeandstyle\",\"sectionName\":\"Life and style\",\"webPublicationDate\":\"2018-10-15T09:00:32Z\",\"webTitle\":\"Cannabis health products are everywhere – but do they live up to the hype?\",\"webUrl\":\"https://www.theguardian.com/lifeandstyle/2018/oct/15/cannabis-health-products-live-up-to-hype-cannabidiol-cbd\",\"apiUrl\":\"https://content.guardianapis.com/lifeandstyle/2018/oct/15/cannabis-health-products-live-up-to-hype-cannabidiol-cbd\",\"fields\":{\"headline\":\"Cannabis health products are everywhere – but do they live up to the hype?\",\"byline\":\"Amy Fleming\",\"thumbnail\":\"https://media.guim.co.uk/dd627b15f8a7df64b4a4fc502652d55c05c26146/0_178_5331_3199/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"crosswords/crossword-blog/2018/oct/15/crossword-blog-try-our-american-style-puzzle-no-10-untitled\",\"type\":\"article\",\"sectionId\":\"crosswords\",\"sectionName\":\"Crosswords\",\"webPublicationDate\":\"2018-10-15T08:54:11Z\",\"webTitle\":\"Crossword blog: try our American-style puzzle No 10: [untitled]\",\"webUrl\":\"https://www.theguardian.com/crosswords/crossword-blog/2018/oct/15/crossword-blog-try-our-american-style-puzzle-no-10-untitled\",\"apiUrl\":\"https://content.guardianapis.com/crosswords/crossword-blog/2018/oct/15/crossword-blog-try-our-american-style-puzzle-no-10-untitled\",\"fields\":{\"headline\":\"Crossword blog: try our American-style puzzle No 10: [untitled]\",\"byline\":\"Alan Connor\",\"thumbnail\":\"https://media.guim.co.uk/21efb3bd8ddf44ce1e2ea6312c143763a492a17c/0_237_3089_1854/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"commentisfree/2018/oct/15/any-inquiry-must-get-to-the-bottom-of-political-pressure-on-the-abc\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-10-15T05:15:06Z\",\"webTitle\":\"Any inquiry must get to the bottom of political pressure on the ABC | Peter Manning\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/oct/15/any-inquiry-must-get-to-the-bottom-of-political-pressure-on-the-abc\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/oct/15/any-inquiry-must-get-to-the-bottom-of-political-pressure-on-the-abc\",\"fields\":{\"headline\":\"Any inquiry must get to the bottom of political pressure on the ABC\",\"byline\":\"Peter Manning\",\"thumbnail\":\"https://media.guim.co.uk/a9102ef60ff45c77243d15eb269f629459433615/0_200_5568_3341/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"world/2018/oct/15/polish-film-the-clergy-sparks-hundreds-of-allegations-of-abuse-kler-catholic\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2018-10-15T04:00:23Z\",\"webTitle\":\"Polish film The Clergy sparks hundreds of allegations of abuse\",\"webUrl\":\"https://www.theguardian.com/world/2018/oct/15/polish-film-the-clergy-sparks-hundreds-of-allegations-of-abuse-kler-catholic\",\"apiUrl\":\"https://content.guardianapis.com/world/2018/oct/15/polish-film-the-clergy-sparks-hundreds-of-allegations-of-abuse-kler-catholic\",\"fields\":{\"headline\":\"Polish film The Clergy sparks hundreds of allegations of abuse\",\"byline\":\"Christian Davies in Poland\",\"thumbnail\":\"https://media.guim.co.uk/689bd7fbf211a20c21ed140c51701d76cd1aee88/992_73_1823_1094/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/oct/15/foreign-office-left-disoriented-and-demoralised-by-brexit-say-diplomats\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-10-14T23:01:17Z\",\"webTitle\":\"Foreign Office left disoriented and demoralised by Brexit, say diplomats\",\"webUrl\":\"https://www.theguardian.com/politics/2018/oct/15/foreign-office-left-disoriented-and-demoralised-by-brexit-say-diplomats\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/oct/15/foreign-office-left-disoriented-and-demoralised-by-brexit-say-diplomats\",\"fields\":{\"headline\":\"Foreign Office left disoriented and demoralised by Brexit, say diplomats\",\"byline\":\"Patrick Wintour  Diplomatic Editor\",\"thumbnail\":\"https://media.guim.co.uk/7eaf9bd70afa5ffb12e4ff98e76f939ad94dbba0/0_1291_1648_989/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"commentisfree/2018/oct/14/the-guardian-view-on-artificial-intelligence-human-learning\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-10-14T16:58:42Z\",\"webTitle\":\"The Guardian view on artificial intelligence: human learning | Editorial\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/oct/14/the-guardian-view-on-artificial-intelligence-human-learning\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/oct/14/the-guardian-view-on-artificial-intelligence-human-learning\",\"fields\":{\"headline\":\"The Guardian view on artificial intelligence: human learning\",\"byline\":\"Editorial\",\"thumbnail\":\"https://media.guim.co.uk/8a96af49232c83fa6c6d902dcb7dbe7df6d5fe80/0_348_5217_3130/500.jpg\"},\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"}]}}";


    private QueryUtils() {
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building URL.", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String JsonResponse = "";

        if (url == null) {
            return JsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                JsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return JsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();

            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<News> extractResultsFromJson(String newsJSON) {

        String byline;

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }


        ArrayList<News> news = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            JSONObject response = baseJsonResponse.getJSONObject("response");
            JSONArray resultsArray = response.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject currentNews = resultsArray.getJSONObject(i);
                JSONObject resultsObject = resultsArray.getJSONObject(i);


                String sectionId = resultsObject.getString("sectionId");
                String webPublicationDate = resultsObject.getString("webPublicationDate");
                String webUrl = resultsObject.getString("webUrl");

                JSONObject fields = currentNews.getJSONObject("fields");
                String headline = fields.getString("headline");

                if (fields.has("byline") && !fields.isNull("byline")) {
                    byline = fields.getString("byline");
                } else byline = "No name Given.";


                News newsItem = new News(sectionId, headline, byline, webUrl, webPublicationDate);
                news.add(newsItem);

            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the news JSON results", e);
        }
        return news;


    }

    public static List<News> fetchNewsData(String requestUrl) {
        //create URL object
        URL url = createUrl(requestUrl);

        //perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        //Extract relavant field from JSON response and create a list of {@link News}
        List<News> news = extractResultsFromJson(jsonResponse);

        //return the list of {@link News}
        return news;
    }


}
