package com.javaacademy.unit.burger;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.UnsupportedDishTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KitchenTest {

    @Test
    @DisplayName("Успешное приготовление")
    public void cookSuccess() {
        Kitchen kitchen = new Kitchen();
        kitchen.cook(DishType.BURGER);
        Dish expectedDish = new Dish(DishType.BURGER);
        int expectedCountDish = 1;
        Assertions.assertEquals(expectedCountDish, kitchen.getCompletedDishes().get(DishType.BURGER).size());
        Assertions.assertEquals(expectedDish, kitchen.getCompletedDishes().get(DishType.BURGER).peek());
    }

    @Test
    @DisplayName("Исключение при приготовлении еды из-за отсутствия газа")
    public void  cookHasNotHasFailure() {
        Kitchen kitchen = new Kitchen();
        kitchen.setHasGas(false);
        Assertions.assertThrows(KitchenHasNoGasException.class, () -> kitchen.cook(DishType.BURGER));
    }

    @Test
    @DisplayName("Исключение не возможно приготовить фуагра")
    public void coolFailure() {
        Kitchen kitchen = new Kitchen();
        Assertions.assertThrows(UnsupportedDishTypeException.class, () -> kitchen.cook(DishType.FUAGRA));
    }
}
