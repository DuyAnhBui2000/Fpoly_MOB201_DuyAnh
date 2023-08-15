package buiduyanh.fpoly.xml_parse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import buiduyanh.fpoly.xml_parse.Model.Item;

public class MainActivity extends AppCompatActivity {

    private Button BtnShow;
    private ListView ListView;
    public static final String URL = "https://vnexpress.net/rss/suc-khoe.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnShow = findViewById(R.id.BtnShow);
        ListView = findViewById(R.id.ListView);

        BtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    class TaskXmlParser extends AsyncTask<Void,Void, List>{
        List<Item> listResult = null;
        InputStream stream = null;
        @Override
        protected List doInBackground(String... str) {
            try {
                stream = downloadUrl(str[0]);
                XMLReader xmlReader = SAXParserFactory.newInstance()
            }
            return null;
        }

        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
        }
    }

    private InputStream downloadUrl(String urlString) throws IOException {
        java.net.URL url= new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
    }
}