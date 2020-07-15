package in.sold.app.models;

/**
 * Created by Sk Suraj
 * Email: addr.suraj@gmail.com
 */

public class DashboardItem {
    private String title, icon;

    public DashboardItem() {}

    public DashboardItem(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
