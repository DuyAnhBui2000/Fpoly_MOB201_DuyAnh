package buiduyanhph30569.fpoly.baithi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

public class ShowXML extends AppCompatActivity {

    private ListView lvNews;
    private AdapterXML adpter;

    private List<Item> arr = new ArrayList<>();
    public static final String URL = "https://vnexpress.net/rss/suc-khoe.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_xml);

        lvNews = findViewById(R.id.lvNews);
        adpter = new AdapterXML(ShowXML.this);
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo checkWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo check3G =  connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if(checkWifi.isConnected() || check3G.isConnected()) {
            

            if (!dbXml.getAllDB().isEmpty()) {
                adpter = new AdapterXML(ShowXML.this);

                arr = dbXml.getAllDB();
                // Sắp xếp danh sách các mục dựa trên trạng thái
                Collections.sort(arr, new Comparator<Item>() {
                    @Override
                    public int compare(Item item1, Item item2) {
                        return Integer.compare(item2.getTrangThai(), item1.getTrangThai());
                    }
                });

                adpter.setData(arr);
                lvNews.setAdapter(adpter);
            } else {
                new DownLoadXML().execute(URL);
            }
        } else {
            Toast.makeText(this, "Bạn đang xem ở chế độ offline!", Toast.LENGTH_SHORT).show();
            adpter = new AdapterXML(ShowXML.this);
            dbXml = new DB_XML(ShowXML.this);
            arr = dbXml.getAllDB();
            // Sắp xếp danh sách các mục dựa trên trạng thái
            Collections.sort(arr, new Comparator<Item>() {
                @Override
                public int compare(Item item1, Item item2) {
                    return Integer.compare(item2.getTrangThai(), item1.getTrangThai());
                }
            });

            adpter.setData(arr);
            lvNews.setAdapter(adpter);
        }
    }

    class DownLoadXML extends AsyncTask<String, Void, List<Item>> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ShowXML.this);
            progressDialog.setMessage("Vui lòng chờ...");
            progressDialog.show();
        }

        @Override
        protected List<Item> doInBackground(String... strings) {
            InputStream stream = null;
            List<Item> dataResult = null;
            try {
                stream = downloadUrl(strings[0]);
                XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
                MySaxHandler saxHandler = new MySaxHandler();
                xmlReader.setContentHandler(saxHandler);
                xmlReader.parse(new InputSource(stream));
                dataResult = saxHandler.getItems();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dataResult;
        }

        @Override
        protected void onPostExecute(List<Item> list) {
            super.onPostExecute(list);
            arr = list;
            adpter.setData(arr);
            lvNews.setAdapter(adpter);
            progressDialog.dismiss();

            dbXml = new DB_XML(ShowXML.this);
            dbXml.deleteAll();
            for (int i = 0; i < arr.size(); i++) {
                Item item = arr.get(i);
                dbXml.insert(item);
            }

            Toast.makeText(ShowXML.this, "Thêm dữ liệu thành công!", Toast.LENGTH_SHORT).show();
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
        return conn.getInputStream();
    }
}