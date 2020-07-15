package in.sold.app.models;

/**
 * Created by Sk Suraj
 * Email: addr.suraj@gmail.com
 */

public class Slider {
    private String id;
    private String title;
    private String subTitle;
    private String image;
    private String type;

    public Slider() {}

    public Slider(String id, String title, String subTitle, String image, String type) {
        this.id = id;
        this.title = title;
        this.subTitle = subTitle;
        this.image = image;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
