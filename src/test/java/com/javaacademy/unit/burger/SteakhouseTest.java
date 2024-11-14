package com.javaacademy.unit.burger;

import com.javaacademy.burger.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.dish.DishType.RIBS;
import static java.math.BigDecimal.valueOf;
import static org.mockito.ArgumentMatchers.any;

public class SteakhouseTest {
    //7. Пришла проверка из налоговой, хочет посмотреть на то, что все заказы проходят через терминал (вдруг скрыли какие то заказы от налоговой). Необходимо написать тесты, которые проверяют работу ресторана, но кухня и официант на самом деле работать не будут, только терминал. При этом в налоговой попросили не делать запросы в банк на расчет валюты (сказали поставить 1 у.е.):
    //Ситуация №1: клиент захотел купить ребра за рубли. Заказал ребра, получил чек(сумма - 700, тип заказа - ребра, валюта - рубли).
    //Ситуация №2: клиент захотел купить картошку за доллары. Заказал картошку, получил чек(1, картошка, доллар)
    //Ситуация №3: клиент захотел купить бургер за мозамбикские доллары. Заказал бургер, получил чек(1, бургер, мозамбикский доллар)
    //Внимание!!!
    //7.1. Для того чтобы написать тесты на 7 пункт, потребуется чтобы фальшивый официант всегда выдавал true при приеме заказа. Используй Mockito.any() для аргументов в методе заказа у официанта при создании заглушки.
    //7.2. Для того, чтобы заменять результаты у spy объекта, нужно пользоваться конструкцией Mockito.doReturn([что возвращаем]).when(у кого вызываем).[метод]
    @Test
    @DisplayName("Успешно заказываем и полчаем чек, вместо оплаты используется  с использованием mock вместо PayTerminal")
    public void whenWaitressAndKitchenMockPayTerminalSpyThenMakeOrderSuccess() {
        Waitress waitressMock = Mockito.mock(Waitress.class);
        Kitchen kitchenMock = Mockito.mock(Kitchen.class);
        PayTerminal payTerminal = new PayTerminal();
        Mockito.when(waitressMock.giveOrderToKitchen(any(), any())).thenReturn(true);
        Paycheck expectedPaycheck = new Paycheck(valueOf(700), RUB, RIBS);
        Steakhouse steakhouse = new Steakhouse(waitressMock, kitchenMock, payTerminal);
        Paycheck resultPaycheck = steakhouse.makeOrder(RIBS, RUB);
        Assertions.assertEquals(expectedPaycheck, resultPaycheck);
    }
}
