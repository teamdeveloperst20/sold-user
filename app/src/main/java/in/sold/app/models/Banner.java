package in.sold.app.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Sk Suraj
 * Email: addr.suraj@gmail.com
 */

public class Banner {
    @SerializedName("Id")
    private String id;

    @SerializedName("Caption")
    private String caption;

    @SerializedName("LinkUrl")
    private String linkUrl;

    @SerializedName("IsInAppLink")
    private String isInAppLink;

    @SerializedName("ImgPath")
    private String imgPath;

    public Banner() {}

    public Banner(String id, String caption, String linkUrl, String isInAppLink, String imgPath) {
        this.id = id;
        this.caption = caption;
        this.linkUrl = linkUrl;
        this.isInAppLink = isInAppLink;
        this.imgPath = imgPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getIsInAppLink() {
        return isInAppLink;
    }

    public void setIsInAppLink(String isInAppLink) {
        this.isInAppLink = isInAppLink;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
