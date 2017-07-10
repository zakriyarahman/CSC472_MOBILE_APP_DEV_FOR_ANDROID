package edu.depaul.csc472.zr_listviewapp;

/**
 * Created by Zak on 10/31/15.
 */
public class FoodItems {

    enum Type {Pizza, Burritos, Burger, Soup, Pasta, Nuggets, Noodles, Tacos, Salad, Panini, Kebabs}
    String name;
    Type type;
    String shortDescription;
    String longDescription;

    public FoodItems(String name, Type type, String shortDescription, String longDescription) {
        this.name = name;
        this.type = type;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
    }

    public static int getIconResource(FoodItems.Type type){
        switch(type){
            case Pizza: return R.drawable.pizza;
            case Burritos: return R.drawable.burrito;
            case Burger: return R.drawable.burger;
            case Soup: return R.drawable.soup;
            case Pasta: return R.drawable.pasta;
            case Nuggets: return R.drawable.nuggets;
            case Noodles: return R.drawable.noodles;
            case Tacos: return R.drawable.tacos;
            case Salad: return R.drawable.salad;
            case Panini: return R.drawable.panini;
            case Kebabs: return R.drawable.kebabs;
        }
        return -1;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String toString() {
        return name;
    }
}
