package Items;

public abstract class Item {
    private String title;

    public Item(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
}
