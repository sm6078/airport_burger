package com.javaacademy.it.burger;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Steakhouse;
import com.javaacademy.burger.Waitress;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.dish.DishType.RIBS;
import static java.math.BigDecimal.valueOf;


public class SteakhouseIT {

    private static final BigDecimal PRICE_BURGER = valueOf(300);
    private static final BigDecimal PRICE_RIBS = valueOf(700);

    @Test
    @DisplayName("Успешный прием заказа")
    public void makeOrderSuccess() {
        Waitress waitress = new Waitress();
        Kitchen kitchen = new Kitchen();
        PayTerminal payTerminal = new PayTerminal();
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
        Paycheck result = steakhouse.makeOrder(DishType.BURGER, RUB);
        Paycheck expected = new Paycheck(PRICE_BURGER, RUB, DishType.BURGER);
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Успешное получение заказа")
    public void takeOrderSuccess() {
        Waitress waitress = new Waitress();
        Kitchen kitchen = new Kitchen();
        PayTerminal payTerminal = new PayTerminal();
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
        Paycheck paycheck = steakhouse.makeOrder(DishType.BURGER, RUB);
        Dish result = steakhouse.takeOrder(paycheck);
        Dish expected = new Dish(DishType.BURGER);
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Успешно заказываем и полчаем заказ, с использованием mock вместо PayTerminal")
    public void whenPayTerminalMockThenMakeAndTakeOrderSuccess() {
        Waitress waitress = new Waitress();
        Kitchen kitchen = new Kitchen();
        PayTerminal payTerminal = Mockito.mock(PayTerminal.class);
        Paycheck expectedPaycheck = new Paycheck(PRICE_RIBS, RUB, RIBS);
        Mockito.when(payTerminal.pay(RIBS, RUB))
                .thenReturn(expectedPaycheck);
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
        Paycheck resultPaycheck = steakhouse.makeOrder(RIBS, RUB);
        Dish expectedDish = new Dish(RIBS);
        Dish resultDish = steakhouse.takeOrder(resultPaycheck);
        Assertions.assertEquals(expectedPaycheck, resultPaycheck);
        Assertions.assertEquals(expectedDish, resultDish);
    }
}
