package buiduyanh.fpoly.ASM_MOB;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ASM_MOB.R;

import buiduyanh.fpoly.ASM_MOB.helper.XMLDOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    private ArrayList<String> arrTitle, arrLink;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ListView lstTieuDe = findViewById(R.id.listViewNews);

        arrTitle = new ArrayList<>();
        arrLink = new ArrayList<>();

        // Thực hiện đọc RSS thông qua AsyncTask
        new ReadRSS().execute("https://vnexpress.net/rss/giao-duc.rss");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrTitle);
        lstTieuDe.setAdapter(adapter);

        lstTieuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Chuyển sang WebviewActivity để hiển thị nội dung chi tiết của tin tức
                Intent intent = new Intent(NewsActivity.this, WebviewActivity.class);
                intent.putExtra("linkNews", arrLink.get(i));
                startActivity(intent);
            }
        });
    }

    private class ReadRSS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // Sử dụng XMLDOMParser để phân tích cú pháp XML
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");
            String title = "";
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                title = parser.getValue(element, "title");
                arrTitle.add(title);
                arrLink.add(parser.getValue(element, "link"));
            }
            adapter.notifyDataSetChanged();
        }
    }
}
