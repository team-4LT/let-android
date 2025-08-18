package com.jinyong68.network.dto


data class MealResponseDto (
    val mealId: Int,
    val mealDate: String,
    val mealType : String, // 조식, 중식, 석식으로 옴
    val calorie: Double,
    val menus: List<Menus>
    )

data class Menus(
    val menuId: Int,
    val menuName: String
)
