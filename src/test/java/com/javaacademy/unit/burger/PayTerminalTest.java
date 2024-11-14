package com.javaacademy.unit.burger;

import com.javaacademy.burger.Currency;
import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PayTerminalTest {

    @Test
    @DisplayName("Успешная оплата")
    public void  paySuccess() {
        PayTerminal payTerminal = new PayTerminal();
        Paycheck expectedResult = new Paycheck(BigDecimal.valueOf(300), Currency.RUB, DishType.BURGER);
        Paycheck result = payTerminal.pay(DishType.BURGER, Currency.RUB);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Исключение во время оплаты")
    public void  payFailure() {
        PayTerminal payTerminal = new PayTerminal();
        Assertions.assertThrows(NotAcceptedCurrencyException.class, () -> payTerminal.pay(DishType.BURGER, Currency.MOZAMBICAN_DOLLARS));
    }
}
