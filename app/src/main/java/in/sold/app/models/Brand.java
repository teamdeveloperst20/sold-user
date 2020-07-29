package in.sold.app.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sk Suraj
 * Email: addr.suraj@gmail.com
 */

public class Brand {
     @SerializedName("Id")
    private String id;
    @SerializedName("BrandName")
    private String name;
    @SerializedName("BrandDesc")
    private String desc;
    @SerializedName("Caption")
    private String caption;
    @SerializedName("HasSeries")
    private String hasSeries;
    @SerializedName("ThumbnailImgPath")
    private String thumbImg;
    @SerializedName("ImgPath")
    private String img;

    public Brand() {}

    public Brand(String id, String name, String desc, String caption, String hasSeries, String thumbImg, String img) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.caption = caption;
        this.hasSeries = hasSeries;
        this.thumbImg = thumbImg;
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getHasSeries() {
        return hasSeries;
    }

    public void setHasSeries(String hasSeries) {
        this.hasSeries = hasSeries;
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
