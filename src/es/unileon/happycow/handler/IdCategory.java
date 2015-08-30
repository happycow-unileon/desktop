package es.unileon.happycow.handler;

/**
 *
 * @author dorian
 */
public class IdCategory implements IdHandler {

    private Category category;

    public IdCategory(Category category) {
        this.category = category;
    }

    public IdCategory(String category) {
        if (category.contains("Category-")) {
            String[] result = category.split("Category-", 2);
            this.category = Category.getEnum(result[1]);
        } else {
            this.category = Category.getEnum(category);
        }
    }

    public Category getCategory() {
        return category;
    }

    public IdCategory(IdHandler id) {
        if (id.toString().contains("Category-")) {
            IdCategory cat = (IdCategory) id;
            this.category = cat.getCategory();
        } else {
            throw new IllegalArgumentException("No es un identificador de categoria");
        }
        try {
            IdCategory cat = (IdCategory) id;
            this.category = cat.getCategory();
        } catch (Exception e) {
            throw new IllegalArgumentException("No es un identificador de categoría");
        }
    }

    @Override
    public String toString() {
        return "Category-" + getValue();
    }

    @Override
    public int compareTo(IdHandler another) {
        return this.toString().compareTo(another.toString());
    }

    @Override
    public String getValue() {
        return category.getName(category);
    }
}
