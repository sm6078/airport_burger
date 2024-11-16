package com.javaacademy.unit.burger;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.Waitress;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WaitressTest {

    @Test
    @DisplayName("Успешное принятие заказа")
    public void waitressAcceptedOrderSuccess() {
        Kitchen kitchenMock = Mockito.mock(Kitchen.class);
        Waitress waitress = new Waitress();
        boolean expectedResult = true;
        Assertions.assertEquals(expectedResult, waitress.giveOrderToKitchen(DishType.BURGER, kitchenMock));
    }

    @Test
    @DisplayName("Успешное не принятие заказа")
    public void waitressAcceptedOrderFailure() {
        Kitchen kitchenMock = Mockito.mock(Kitchen.class);
        Waitress waitress = new Waitress();
        boolean expectedResult = false;
        Assertions.assertEquals(expectedResult, waitress.giveOrderToKitchen(DishType.FUAGRA, kitchenMock));
    }
}
