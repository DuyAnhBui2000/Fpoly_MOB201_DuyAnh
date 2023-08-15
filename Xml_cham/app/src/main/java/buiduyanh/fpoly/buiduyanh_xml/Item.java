package buiduyanh.fpoly.buiduyanh_xml;

public class Item {

    private int idXML;
    private String title;
    private String link;
    private String pubDate;
    private String description;
    private String imgAvt;

    public Item() {
    }

    public Item(String title, String link, String pubDate, String description) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.description = description;
    }

    public int getIdXML() {
        return idXML;
    }

    public void setIdXML(int idXML) {
        this.idXML = idXML;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgAvt() {
        return imgAvt;
    }

    public void setImgAvt(String imgAvt) {
        this.imgAvt = imgAvt;
    }
}
