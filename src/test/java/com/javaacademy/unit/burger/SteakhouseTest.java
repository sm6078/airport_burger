package com.javaacademy.unit.burger;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Waitress;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.Steakhouse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.Currency.USD;
import static com.javaacademy.burger.Currency.MOZAMBICAN_DOLLARS;
import static com.javaacademy.burger.dish.DishType.BURGER;
import static com.javaacademy.burger.dish.DishType.RIBS;
import static com.javaacademy.burger.dish.DishType.FRIED_POTATO;
import static java.math.BigDecimal.valueOf;
import static org.mockito.ArgumentMatchers.any;

public class SteakhouseTest {
    private static final BigDecimal PRICE_RIBS = valueOf(700);

    @Test
    @DisplayName("Успешно заказываем и получаем чек от фейкового терминала")
    public void whenWaitressAndKitchenMockPayTerminalSpyThenMakeOrderSuccess() {
        Waitress waitressMock = Mockito.mock(Waitress.class);
        Mockito.when(waitressMock.giveOrderToKitchen(any(), any())).thenReturn(true);
        Kitchen kitchenMock = Mockito.mock(Kitchen.class);
        PayTerminal payTerminalSpy = Mockito.spy(PayTerminal.class);
        Steakhouse steakhouse = new Steakhouse(waitressMock, kitchenMock, payTerminalSpy);
        Paycheck expectedPaycheck = new Paycheck(PRICE_RIBS, RUB, RIBS);
        Paycheck resultPaycheck = steakhouse.makeOrder(RIBS, RUB);
        Assertions.assertEquals(expectedPaycheck, resultPaycheck);
    }

    @Test
    @DisplayName("Успешно заказываем картошку и получаем чек со стоимостью 1 доллар")
    public void orderPotatoByDollarFakeTerminalMakeOrderSuccess() {
        Waitress waitressMock = Mockito.mock(Waitress.class);
        Mockito.when(waitressMock.giveOrderToKitchen(any(), any())).thenReturn(true);
        Kitchen kitchenMock = Mockito.mock(Kitchen.class);
        PayTerminal payTerminalSpy = Mockito.spy(PayTerminal.class);
        Paycheck expectedPaycheck = new Paycheck(BigDecimal.ONE, USD, FRIED_POTATO);
        Mockito.doReturn(expectedPaycheck).when(payTerminalSpy).pay(FRIED_POTATO, USD);
        Steakhouse steakhouse = new Steakhouse(waitressMock, kitchenMock, payTerminalSpy);
        Paycheck resultPaycheck = steakhouse.makeOrder(FRIED_POTATO, USD);
        Assertions.assertEquals(expectedPaycheck, resultPaycheck);
    }

    @Test
    @DisplayName("Успешно заказываем бургер и получаем чек со стоимостью 1 мозамбикский доллар")
    public void orderBurgerByMozambicanDollarFakeTerminalMakeOrderSuccess() {
        Waitress waitressMock = Mockito.mock(Waitress.class);
        Mockito.when(waitressMock.giveOrderToKitchen(any(), any())).thenReturn(true);
        Kitchen kitchenMock = Mockito.mock(Kitchen.class);
        PayTerminal payTerminalSpy = Mockito.spy(PayTerminal.class);
        Paycheck expectedPaycheck = new Paycheck(BigDecimal.ONE, MOZAMBICAN_DOLLARS, BURGER);
        Mockito.doReturn(expectedPaycheck).when(payTerminalSpy).pay(BURGER, MOZAMBICAN_DOLLARS);
        Steakhouse steakhouse = new Steakhouse(waitressMock, kitchenMock, payTerminalSpy);
        Paycheck resultPaycheck = steakhouse.makeOrder(BURGER, MOZAMBICAN_DOLLARS);
        Assertions.assertEquals(expectedPaycheck, resultPaycheck);
    }
}
