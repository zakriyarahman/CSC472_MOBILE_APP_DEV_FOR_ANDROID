package edu.depaul.csc472.zr_listviewapp;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_items);
        setListAdapter(new FoodItemsAdapter());
    }

    class FoodItemsAdapter extends BaseAdapter {

        private LayoutInflater inflater;

        @Override
        public int getCount() {
            return FOODITEMS.length;
        }

        @Override
        public Object getItem(int i) {
            return FOODITEMS[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (convertView == null) {
                if (inflater == null)
                    inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.activity_list_items, parent, false);
            }

            ImageView icon = (ImageView) row.findViewById(R.id.imgFoodItem);
            TextView name = (TextView) row.findViewById(R.id.txtLabel);
            TextView description = (TextView) row.findViewById(R.id.txtShortDesc);

            FoodItems fooditem = FOODITEMS[position];
            name.setText(fooditem.getName());
            description.setText(fooditem.getShortDescription());
            icon.setImageResource(FoodItems.getIconResource(fooditem.getType()));
            return row;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private static final FoodItems[] FOODITEMS = {
        new FoodItems("Vegetable Pizza",
                FoodItems.Type.Pizza,
                "Onions, peppers and jalapeno Pizza",
                "Vegetable Pizza with grilled onions, fried green peppers, jalapeno, garlic and ginger."),

        new FoodItems("Chicken Burger",
                FoodItems.Type.Burger,
                "Chicken Burger",
                "Chicken Burger with a soft layer of bun in between burger with mayonnaise meshed with it."),

        new FoodItems("Gyro Burritos",
                FoodItems.Type.Burritos,
                "Gyro Burritos",
                "Gyro Burritos with vegetables chopped with meshed sauces."),

        new FoodItems("Chicken Pizza",
        FoodItems.Type.Pizza,
                "Add ons with onions, peppers and jalapeno Pizza",
                "Chicken Pizza with addons - grilled onions, fried green peppers, jalapeno, garlic and ginger."),

        new FoodItems("Gyro Burger",
                FoodItems.Type.Burger,
                "Gyro Burger",
                "Gyro Burger with a soft layer of bun in between burger with mayonnaise meshed with it."),

        new FoodItems("Chicken Burritos",
                FoodItems.Type.Burritos,
                "Chicken Burritos",
                "Chicken Burritos with vegetables chopped with meshed sauces."),

        new FoodItems("Meat Lovers Pizza",
                FoodItems.Type.Pizza,
                "Onions, peppers and jalapeno Pizza",
                "Homemade thin crust pizza, topped off with two types of cheese, bacon, ham, pepperoni and hot sausage! A must make for meat lover’s. "),

        new FoodItems("Hot and Sour Soup",
                FoodItems.Type.Soup,
                "The soup contains ingredients to make it both spicy and sour",
                "Common key ingredients in the include bamboo shoots, toasted sesame oil, wood ear, cloud ear fungus, day lily buds, vinegar, egg, corn starch, and white pepper. Other ingredients include button mushrooms and small slices of tofu skin. This soup is usually considered a healthy option and, other than being high in sodium, is a very healthy soup overall."),

        new FoodItems("Grilled Sheek Kebabs",
                FoodItems.Type.Kebabs,
                "",
                "Seekh Kababs are spicy kababs made from a smooth minced mixture. They can be ordered with either grilled over a bbq or baked in the oven or pan-fried on the stove-top, and are commonly served with a salad, fries, naan or pita bread and a variety of dips."),

        new FoodItems("Sun Dried Tomato Pasta",
                FoodItems.Type.Pasta,
                "",
                "Height-of-summer tomatoes burst with flavor and creates little embellised spectacular dish. Churning the oil into boiling liquid emulsifies the mixture, yielding a creamy sauce that coats."),

        new FoodItems("Garlic Butter Noodles",
                FoodItems.Type.Noodles,
                "Mesmersing buttered Noodles. Makes mouth watery and addictive",
                "Melt the butter with the oil in a small skillet or saucepan. Poured on the garlic butter and tossed well. Served immediately.\n"),

        new FoodItems("Chicken Nuggets",
                FoodItems.Type.Nuggets,
                "Served with garlic souce and tomato ketchup",
                "Chicken Nugget made from either meat slurry or chicken breasts cut to shape, breaded or battered, then deep-fried or baked. Fried in vegetable oil."),

        new FoodItems("Falafel Panini",
                FoodItems.Type.Panini,
                "Served with garlic souce and Hummus",
                "The bread is cut horizontally and filled with deli ingredients such as cheese and served warm after having been pressed by a warming grill"),

        new FoodItems("Chicken Salad",
                FoodItems.Type.Salad,
                "In a mixing bowl, toss together the chicken, celery, scallions and herbs",
                "In a medium sized bowl, whisk together the mayonnaise, lemon juice, mustard, salt and pepper to taste. Chicken and mixed gently until combined."),


    };

    protected void onListItemClick(ListView l, View view, int position, long id){
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("FoodItemName", FOODITEMS[position].getName());
        intent.putExtra("FoodItemIcon", FoodItems.getIconResource(FOODITEMS[position].getType()));
        intent.putExtra("FoodItemDescription", FOODITEMS[position].getLongDescription());
        startActivity(intent);
    }
}
