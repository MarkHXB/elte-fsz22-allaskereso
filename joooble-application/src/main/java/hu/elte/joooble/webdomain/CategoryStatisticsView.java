package hu.elte.joooble.webdomain;

public class CategoryStatisticsView {
    private final String categoryId;
    private final String categoryName;
    private final Long count;

    public CategoryStatisticsView(String categoryId, String categoryName, Long count) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.count = count;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Long getCount() {
        return count;
    }
}
