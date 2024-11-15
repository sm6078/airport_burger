package com.javaacademy.unit.burger;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.Waitress;
import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WaitressTest {

    @Test
    @DisplayName("Успешное принятие заказа")
    public void WaitressAcceptedOrderSuccess() {
        Kitchen kitchenMock = Mockito.mock(Kitchen.class);
        Waitress waitress = new Waitress();
        boolean expectedResult = true;
        Assertions.assertEquals(expectedResult,  waitress.giveOrderToKitchen(DishType.BURGER, kitchenMock));
    }

    @Test
    @DisplayName("Успешное не принятие заказа")
    public void WaitressAcceptedOrderFailure() {
        Kitchen kitchenMock = Mockito.mock(Kitchen.class);
        Waitress waitress = new Waitress();
        boolean expectedResult = false;
        Assertions.assertEquals(expectedResult,  waitress.giveOrderToKitchen(DishType.FUAGRA, kitchenMock));
    }
}
