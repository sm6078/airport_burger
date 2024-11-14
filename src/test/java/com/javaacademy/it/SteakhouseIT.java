package com.javaacademy.it;

import com.javaacademy.burger.*;
import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.javaacademy.burger.Currency.RUB;
import static com.javaacademy.burger.dish.DishType.RIBS;
import static java.math.BigDecimal.valueOf;

public class SteakhouseIT {

    //5. Приехал владелец бизнеса и попросил показать работу всего ресторана. Тут уже придется кормить его по настоящему! Написать тесты, которые проверяют работу всего ресторана:
//Ситуация №1: клиент захотел купить бургер за рубли. Заказал бургер, получил чек(300 руб, бургер, рубли), подошел за заказом, получил бургер. Проверить чек, проверить блюдо(что это именно бургер!).
    @Test
    @DisplayName("Успешный прием заказа")
    public void makeOrderSuccess() {
        Waitress waitress = new Waitress();
        Kitchen kitchen = new Kitchen();
        PayTerminal payTerminal = new PayTerminal();
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
        Paycheck result = steakhouse.makeOrder(DishType.BURGER, RUB);
        Paycheck expected = new Paycheck(valueOf(300), RUB, DishType.BURGER);
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
        Paycheck expectedPaycheck = new Paycheck(valueOf(700), RUB, RIBS);
        Mockito.when(payTerminal.pay(RIBS, RUB))
                .thenReturn(expectedPaycheck);
        Steakhouse steakhouse = new Steakhouse(waitress, kitchen, payTerminal);
        Paycheck resultPaycheck = steakhouse.makeOrder(RIBS, RUB);
        Dish expectedDish = new Dish(RIBS);
        Dish resultDish = steakhouse.takeOrder(resultPaycheck);
        Assertions.assertEquals(expectedPaycheck, resultPaycheck);
        Assertions.assertEquals(expectedDish, resultDish);
    }

    //7. Пришла проверка из налоговой, хочет посмотреть на то, что все заказы проходят через терминал (вдруг скрыли какие то заказы от налоговой). Необходимо написать тесты, которые проверяют работу ресторана, но кухня и официант на самом деле работать не будут, только терминал. При этом в налоговой попросили не делать запросы в банк на расчет валюты (сказали поставить 1 у.е.):
    //Ситуация №1: клиент захотел купить ребра за рубли. Заказал ребра, получил чек(сумма - 700, тип заказа - ребра, валюта - рубли).
    //Ситуация №2: клиент захотел купить картошку за доллары. Заказал картошку, получил чек(1, картошка, доллар)
    //Ситуация №3: клиент захотел купить бургер за мозамбикские доллары. Заказал бургер, получил чек(1, бургер, мозамбикский доллар)
    //Внимание!!!
    //7.1. Для того чтобы написать тесты на 7 пункт, потребуется чтобы фальшивый официант всегда выдавал true при приеме заказа. Используй Mockito.any() для аргументов в методе заказа у официанта при создании заглушки.
    //7.2. Для того, чтобы заменять результаты у spy объекта, нужно пользоваться конструкцией Mockito.doReturn([что возвращаем]).when(у кого вызываем).[метод]


}
