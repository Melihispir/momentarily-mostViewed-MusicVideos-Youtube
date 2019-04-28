package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.commit451.youtubeextractor.YouTubeExtractionResult;
import com.commit451.youtubeextractor.YouTubeExtractor;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import java.io.IOException;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YtFile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Main2Activity extends AppCompatActivity {


    private ProgressDialog mProgressDialog;
    String value;
    WebView wv;
    String iframeSrc;
    String iframeSrc2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        wv=(WebView)findViewById(R.id.webview2);
        Bundle extras = getIntent().getExtras();
        value = extras.getString("send_string");
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        new FetchTitle2().execute();


    }


    private   class FetchTitle2 extends AsyncTask <String , Void, String> {


        @Override
        protected  void  onPreExecute(){

            mProgressDialog = new ProgressDialog(Main2Activity.this);
            mProgressDialog.setTitle("Daily most listened Music Videos on YouTube");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            Document doc3 = null;
            try {
                doc3  =  Jsoup.connect(value).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Element iframe = doc3.select("iframe").first();
             iframeSrc = iframe.attr("src");
                iframeSrc2=iframeSrc.replace("embed/","watch?v=");


            return null;
         }



        @Override
        protected void onPostExecute(String result) {

            //wv.loadData(iframeSrc, "text/html", "utf-8");

           wv.loadUrl(iframeSrc2);


            mProgressDialog.dismiss();

        }
    }





}
