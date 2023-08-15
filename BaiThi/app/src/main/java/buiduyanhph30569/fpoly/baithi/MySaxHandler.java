package buiduyanhph30569.fpoly.baithi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class MySaxHandler extends DefaultHandler {
    private ArrayList<Item> items = new ArrayList<>();
    private Item item;
    private String temp;
    private Boolean isRead = false;


    public MySaxHandler() {
    }

    public ArrayList<Item> getItems(){
        return items;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);

        if(isRead){
            temp = new String(ch,start,length);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        // xử lý khi gặp thẻ mở
        if(qName.equalsIgnoreCase("item")){
            item = new Item();
            isRead = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        // xử lý khi gặp thẻ đóng
        if(qName.equalsIgnoreCase("item")){

            if (item.getImgAvt() != null) {
                items.add(item);
            }
//            items.add(item);
            isRead = false;
        }else if(isRead){
            if(qName.equalsIgnoreCase("title")){
                item.setTitle(temp);
            }else if(qName.equalsIgnoreCase("link")){
                item.setLink(temp);
            }else if(qName.equalsIgnoreCase("pubDate")){
                item.setPubDate(temp);
            }else if(qName.equalsIgnoreCase("description")){

                Document doc = Jsoup.parse(temp);
                Elements imgTags = doc.select("img");
                for (Element img : imgTags){
                    String src = img.attr("src");
                    item.setImgAvt(src);
                }
                String nd = temp.replaceAll("<[^>]*>","");
                item.setDescription(nd);
//                item.setDescription(temp);
            }
        }
    }
}
