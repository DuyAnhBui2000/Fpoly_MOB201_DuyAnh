package buiduyanh.fpoly.xml_parse.Model;

public class Item {

    private String title;
    private String link;
    private String description;

    private String imgUrl;

    public Item() {
    }

    public Item(String title, String link, String description, String imgUrl) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.imgUrl = imgUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
