package buiduyanh.fpoly.buiduyanh_xml;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

public class MyShow extends AppCompatActivity {

    private DbHelper dbXml;

    private ListView lvNews;
    private AdapterXml adpter;

    private List<Item> arr = new ArrayList<>();
    public static final String URL = "https://vnexpress.net/rss/giao-duc.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_show);

        lvNews = findViewById(R.id.lvNews);
        adpter = new AdapterXml(MyShow.this);

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo checkWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo check3G = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (checkWifi.isConnected() || check3G.isConnected()) {

            new DownLoadXML().execute(URL);

        } else {
            Toast.makeText(this, "bạn đang xem ở chế độ off line!", Toast.LENGTH_SHORT).show();
            adpter = new AdapterXml(MyShow.this);
            dbXml = new DbHelper(MyShow.this);
            arr = dbXml.getAllCourse();
            adpter.setData(arr);
            lvNews.setAdapter(adpter);
        }
    }

    class DownLoadXML extends AsyncTask<String, Void, List> {

        ProgressDialog progressDialog;

        @Override
        protected List doInBackground(String... strings) {

            InputStream stream = null;
            List<Item> dataResult = null;
            try {
                stream = downloadUrl(strings[0]);
                XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
                MySaxHanlder saxHandler = new MySaxHanlder();
                xmlReader.setContentHandler(saxHandler);
                xmlReader.parse(new InputSource(stream));
                dataResult = saxHandler.getItems();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return dataResult;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MyShow.this);
            progressDialog.setMessage("Vui lòng chờ...");
            progressDialog.show();
        }


        @Override
        protected void onPostExecute(List list) {
            super.onPostExecute(list);
            arr = (ArrayList<Item>) list;
            adpter.setData(arr);
            lvNews.setAdapter(adpter);
            progressDialog.dismiss();

            dbXml = new DbHelper(MyShow.this);
            dbXml.deleteAll();
            for (int i = 0; i < arr.size(); i++) {
                Item item = arr.get(i);
                dbXml.insert(item);
            }

            Toast.makeText(MyShow.this, "Thêm dữ liệu thành công!", Toast.LENGTH_SHORT).show();

        }
    }


    private InputStream downloadUrl(String urlString) throws IOException {
        java.net.URL url = new URL(urlString);
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