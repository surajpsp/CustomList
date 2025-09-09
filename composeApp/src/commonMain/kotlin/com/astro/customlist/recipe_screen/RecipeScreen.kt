package com.astro.customlist.recipe_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.astro.customlist.core_klist.KList
import kotlinx.coroutines.delay

@Composable
fun RecipeScreen() {
    // Initial UI state
    var lunchRecipes by remember { mutableStateOf<List<Recipe>>(emptyList()) }
    var dinnerRecipes by remember { mutableStateOf<List<Recipe>>(emptyList()) }
    var dessertRecipes by remember { mutableStateOf<List<Recipe>>(emptyList()) }
    var drinksRecipes by remember { mutableStateOf<List<Recipe>>(emptyList()) }

    // Added Recipe item after two second so that initially it show empty list. that i added in Klist DSL Class
    LaunchedEffect(Unit) {
        delay(2000) // 3 seconds delay (simulating API call)

        lunchRecipes = listOf(
            Recipe("Paneer Butter Masala", "Veg", "30 min", "Medium",
                "Rich tomato gravy with butter and paneer cubes.",
                "https://media.istockphoto.com/id/885881832/photo/butter-paneer-masala-traditional-indian-paneer-curry.jpg?s=1024x1024&w=is&k=20&c=5Xf3ki6xKypynVJvxvoykYelKL_xTPnV-GlvOfhN6DQ="),
            Recipe("Veg Biryani", "Veg", "45 min", "Hard",
                "Aromatic basmati rice cooked with mixed vegetables and spices.",
                "https://media.istockphoto.com/id/495188382/photo/indian-pulav-vegetable-rice-veg-biryani-basmati-rice.jpg?s=1024x1024&w=is&k=20&c=UfSBeQpK2Swnd6aEERRmGhi848rZd5ltqPDrfCxUQuE="),
            Recipe("Dal Tadka", "Veg", "25 min", "Easy",
                "Yellow lentils tempered with ghee, garlic, and cumin.",
                "https://media.istockphoto.com/id/495455544/photo/red-lentil-indian-soup-with-flat-bread-masoor-dal.jpg?s=1024x1024&w=is&k=20&c=z5Hr-emChQksAXr7lmWT1NL3TL9To0UlZ8gGQ9gP4sU="),
            Recipe("Aloo Paratha", "Veg", "20 min", "Easy",
                "Stuffed wheat bread with spicy mashed potatoes.",
                "https://cdn.pixabay.com/photo/2022/05/14/16/18/indian-food-7196024_1280.jpg"),
            Recipe("Chole Bhature", "Veg", "40 min", "Hard",
                "Spicy chickpeas served with deep-fried bhature.",
                "https://picsum.photos/200/200?6")
        )

        dinnerRecipes = listOf(
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

        dessertRecipes = listOf(
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

        drinksRecipes = listOf(
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
    }

    // Used Core Klist DSL class to show recipe list with type, by section
    KList.instance
        .padding(16.dp)
        .animateItems()
        .divider()
        .section("🍱 Lunch Specials", lunchRecipes) { RecipeItem(it) }
        .section("🍲 Dinner Delights", dinnerRecipes) { RecipeItem(it) }
        .section("🍰 Desserts", dessertRecipes) { RecipeItem(it) }
        .section("🥤 Drinks", drinksRecipes) { RecipeItem(it) }
        .emptyState {
            Column(
                modifier = Modifier.fillMaxSize().padding(vertical = 24.dp, horizontal = 12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Loading recipes... please wait")
            }

        }
        .render()
}



