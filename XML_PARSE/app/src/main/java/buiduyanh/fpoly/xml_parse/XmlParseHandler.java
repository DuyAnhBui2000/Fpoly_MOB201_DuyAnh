package buiduyanh.fpoly.xml_parse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import buiduyanh.fpoly.xml_parse.Model.Item;

public class XmlParseHandler extends DefaultHandler {

    private List<Item> itemList;
    private Item item;
    private boolean isRead = false;
    private String temp;

    public XmlParseHandler() {
        itemList = new ArrayList<>();
    }

    // getter
    public List<Item> getItemList() {
        return itemList;
    }

    //1
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        // ở đây sẽ lấy dữ liệu trong cặp thẻ mở và đóng

        if (isRead){
            temp = new String(ch,start,length);
        }
    }


    //2
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        // tại đây sẽ xử lý khi gặp thẻ mở

        if (qName.equalsIgnoreCase("item")){
            item = new Item();
            isRead = true;
        }


    }


    //3
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        //tại đây sẽ xử lý khi gặp thẻ đóng

        if (qName.equalsIgnoreCase("url")){
             =temp;
        }
        if (qName.equalsIgnoreCase("item")){
            itemList.add(item);
            isRead=false;
        }else if (isRead){
            if (qName.equalsIgnoreCase("title")){
                item.setTitle(temp);
            }
            if (qName.equalsIgnoreCase("description")){
                item.setDescription(temp);
            }
            if (qName.equalsIgnoreCase("link")){
                item.setLink(temp);
            }
        }


    }
}
