package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int x =0;
    WebView wv;
    ArrayAdapter<String> veriAdaptoru;
    List<String> list2 = new ArrayList<>();
    List<String> list = new ArrayList<>();
    String s =null;
    ListView lv;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listview1);
        new FetchTitle().execute();
        veriAdaptoru=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, list2);


    }

    private class FetchTitle extends AsyncTask <String , Void, String> {


        @Override
        protected  void  onPreExecute(){

            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Daily most listened Music Videos on YouTube");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... voids) {
            Document doc = null;
            Document doc5 = null;
                try {
                   doc  = Jsoup.connect("https://kworb.net/youtube/").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            Elements el = doc.select("tr");
            Elements e2 = doc.select("a[href]");
            String s3=null;
            String s4="";
            el.remove(0);
            for (Element e : el){

                   s=e.text();                                                   //yazilar

                String dizi[] =s.split(" ");
                for(int i =0;i<dizi.length;i++){

                      if (i==0)
                        continue;
                    else if(i==1)
                        continue;
                    else if (i==dizi.length-2)
                        continue;
                    else if (i==dizi.length-1)
                        continue;
                    else
                            s4+=dizi[i]+" ";

                }
                list2.add(s4);
                s4="";
            }
           // list2.remove(0);

            for ( Element e : e2    ){              //linkler
                s=e.attr( "href" );
                if(s.contains("video")){
                    String s2 = "https://kworb.net/youtube/";
                    s3= s2.concat(s);
                    list.add(s3);
                }
               else if (s.contains("https://www.youtube.com/watch"))
                   list.add(s);
            }
            list.remove(0);



            lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {

                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    Intent intent = new Intent(getBaseContext() , Main2Activity.class    );
                    intent.putExtra("send_string",list.get(arg2));
                    startActivity(intent);

                }
            });


            return null;
        }
        @Override
        protected void onPostExecute(String result) {

            lv.setAdapter(veriAdaptoru);
            mProgressDialog.dismiss();
        }
    }


}



