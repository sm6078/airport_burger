package com.javaacademy.unit.burger;

import com.javaacademy.burger.Currency;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.valueOf;

public class PayTerminalTest {

    private static final BigDecimal PRICE_BURGER = valueOf(300);

    @Test
    @DisplayName("Успешная оплата")
    public void paySuccess() {
        PayTerminal payTerminal = new PayTerminal();
        Paycheck expectedResult = new Paycheck(PRICE_BURGER,
                Currency.RUB, DishType.BURGER);
        Paycheck result = payTerminal.pay(DishType.BURGER, Currency.RUB);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Исключение во время оплаты, мозабмийские доллары не принимаются к оплате")
    public void payFailure() {
        PayTerminal payTerminal = new PayTerminal();
        Assertions.assertThrows(NotAcceptedCurrencyException.class,
                () -> payTerminal.pay(DishType.BURGER, Currency.MOZAMBICAN_DOLLARS));
    }
}
