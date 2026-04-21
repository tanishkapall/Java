public class ProductAdapter implements Product {

    private LegacyItem legacyItem;

    // Constructor accepts LegacyItem
    public ProductAdapter(LegacyItem legacyItem) {
        this.legacyItem = legacyItem;
    }

    // Adapt old method to new interface
    @Override
    public void displayDetails() {
        legacyItem.print();
    }
}