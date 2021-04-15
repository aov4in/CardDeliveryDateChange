package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static ru.netology.test.DataGenerator.*;

public class CardDeliveryDateChangeTest {

    private final String city = getRandomCity();
    private final String dateOfDelivery = getCorrectDate(4);
    private final String dateOfDeliveryAnother = getCorrectDate(10);
    private String dateNoCorrectDelivery = getNotCorrectDate();
    private final String name = getRandomName();
    private final String phone = getRandomPhone();

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestAnotherDate() {
        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dateOfDelivery);
        $("[data-test-id='name'] input").setValue(name);
        $("[data-test-id='phone'] input").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(visible, ofSeconds(11));
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на "+dateOfDelivery));
        $("[data-test-id=success-notification] button").click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dateOfDeliveryAnother);
        $(withText("Запланировать")).click();
        $("[data-test-id='replan-notification']").shouldBe(visible, ofSeconds(11));
        $("[data-test-id='replan-notification']").shouldHave(text("У вас уже запланирована встреча на другую дату"));
        $(withText("Перепланировать")).click();
    }

    @Test
    void shouldNoCorrectDate() {
        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dateNoCorrectDelivery);
        $("[data-test-id='name'] input").setValue(name);
        $("[data-test-id='phone'] input").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();
        $("[data-test-id='date'] .input__sub").shouldBe(text("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldWrongDateOfRescheduling() {
        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dateOfDelivery);
        $("[data-test-id='name'] input").setValue(name);
        $("[data-test-id='phone'] input").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(visible, ofSeconds(11));
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на " + dateOfDelivery));
        $("[data-test-id=success-notification] button").click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dateNoCorrectDelivery);
        $(withText("Запланировать")).click();
        $("[data-test-id='date'] .input__sub").shouldBe(text("Заказ на выбранную дату невозможен"));
    }

}
