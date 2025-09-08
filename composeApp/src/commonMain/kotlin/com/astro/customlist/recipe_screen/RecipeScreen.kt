package com.astro.customlist.recipe_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.astro.customlist.core_klist.KList

@Composable
fun RecipeScreen() {
    // Dummy data
    val lunchRecipes = listOf(
        Recipe("Paneer Butter Masala", "Veg", "30 min", "Medium",
            "Rich tomato gravy with butter and paneer cubes.", "https://picsum.photos/200/200?1"),
        Recipe("Veg Biryani", "Veg", "45 min", "Hard",
            "Aromatic basmati rice cooked with mixed vegetables and spices.", "https://picsum.photos/200/200?2"),
        Recipe("Dal Tadka", "Veg", "25 min", "Easy",
            "Yellow lentils tempered with ghee, garlic, and cumin.", "https://picsum.photos/200/200?3"),
        Recipe("Aloo Paratha", "Veg", "20 min", "Easy",
            "Stuffed wheat bread with spicy mashed potatoes.", "https://picsum.photos/200/200?4"),
        Recipe("Chole Bhature", "Veg", "40 min", "Hard",
            "Spicy chickpeas served with deep-fried bhature.", "https://picsum.photos/200/200?6")
    )

    val dinnerRecipes = listOf(
        Recipe("Palak Paneer", "Veg", "35 min", "Medium",
            "Creamy spinach curry with paneer cubes.", "https://picsum.photos/200/200?5"),
        Recipe("Chicken Curry", "Non-Veg", "45 min", "Medium",
            "Spicy chicken curry with onion-tomato base.", "https://picsum.photos/200/200?8"),
        Recipe("Mutton Rogan Josh", "Non-Veg", "60 min", "Hard",
            "Traditional Kashmiri lamb curry cooked in yogurt and spices.", "https://picsum.photos/200/200?11"),
        Recipe("Butter Chicken", "Non-Veg", "50 min", "Medium",
            "Creamy tomato-based curry with marinated chicken.", "https://picsum.photos/200/200?12"),
        Recipe("Prawn Masala", "Non-Veg", "35 min", "Medium",
            "Juicy prawns in spicy onion-tomato masala.", "https://picsum.photos/200/200?13")
    )

    val dessertRecipes = listOf(
        Recipe("Chocolate Cake", "Dessert", "70 min", "Hard",
            "Rich chocolate sponge layered with frosting.", "https://picsum.photos/200/200?14"),
        Recipe("Gulab Jamun", "Dessert", "45 min", "Medium",
            "Milk-solid balls fried and soaked in sugar syrup.", "https://picsum.photos/200/200?15"),
        Recipe("Rasgulla", "Dessert", "50 min", "Medium",
            "Soft spongy balls made from chhena and sugar syrup.", "https://picsum.photos/200/200?16"),
        Recipe("Kheer", "Dessert", "40 min", "Easy",
            "Rice pudding cooked with milk, sugar and dry fruits.", "https://picsum.photos/200/200?17"),
        Recipe("Ice Cream Sundae", "Dessert", "15 min", "Easy",
            "Ice cream topped with chocolate, nuts, and fruits.", "https://picsum.photos/200/200?18")
    )

    val drinksRecipes = listOf(
        Recipe("Lassi", "Drinks", "10 min", "Easy",
            "Refreshing yogurt-based drink, sweet or salty.", "https://picsum.photos/200/200?19"),
        Recipe("Mango Shake", "Drinks", "5 min", "Easy",
            "Cool summer drink made with ripe mangoes and milk.", "https://picsum.photos/200/200?20"),
        Recipe("Cold Coffee", "Drinks", "10 min", "Easy",
            "Chilled coffee blended with milk and sugar.", "https://picsum.photos/200/200?21"),
        Recipe("Masala Chai", "Drinks", "10 min", "Easy",
            "Indian tea brewed with spices and milk.", "https://picsum.photos/200/200?22"),
        Recipe("Green Tea", "Drinks", "5 min", "Easy",
            "Light, healthy tea with antioxidants.", "https://picsum.photos/200/200?31")
    )



    val desserts = emptyList<Recipe>() // to demo empty state

    KList.instance
        .padding(16.dp)
        .animateItems()
        .divider()
        .section("🍱 Lunch Specials", lunchRecipes) { RecipeItem(it) }
        .section("🍲 Dinner Delights", dinnerRecipes) { RecipeItem(it) }
        .section("🍰 Desserts", dessertRecipes) { RecipeItem(it) }
        .section("🥤 Drinks", drinksRecipes) { RecipeItem(it) }
        .emptyState {
            Text("No recipes found, add your first recipe! 🍳")
        }
        .render()
}


