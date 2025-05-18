package enums;

public enum Navigator {
    MARKS("оценки","/votes/"),
    TOP250("Top250","/lists/movies/top250/"),
    LISTS("списки","/lists/categories/movies/");

    public final String description;
    public final String path;

    Navigator(String description, String path) {
        this.description = description;
        this.path = path;
    }

}
