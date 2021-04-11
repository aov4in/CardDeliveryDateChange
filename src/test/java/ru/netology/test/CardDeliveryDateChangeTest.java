package ru.netology.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;
import static ru.netology.test.DataGenerator.*;

public class CardDeliveryDateChangeTest {

    private final String city = getRandomCity();
    private final String dateOfDelivery = getCorrectDate(3);
    private final String dateOfDeliveryAnother = getCorrectDate(10);
//    private final String notCorrectDate = getNotCorrectDate();
    private final String name = getRandomName();
    private final String phone = getRandomPhone();

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

//    @Test
//    void shouldTestHappyPathWithFaker() {
//        String firstMeeting = getCorrectDate(23);
////        int secondMeeting = DataGenerator.Registration.generateRandomNumber(firstMeeting+1,firstMeeting+180);
//        $("[data-test-id='city'] input").setValue(getRandomCity());
//        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE);
//        $("[placeholder='Дата встречи']").setValue(DataGenerator.getCorrectDate(23));
//        $("[data-test-id='name'] input").setValue(DataGenerator.getRandomName());
//        $("[data-test-id='phone'] input").setValue(DataGenerator.getRandomPhone());
//        $("[data-test-id=agreement] .checkbox__box").click();
//        $(withText("Запланировать")).click();
//        $("[data-test-id='success-notification']").shouldBe(visible, ofSeconds(15));
//        $("[data-test-id='success-notification']>.notification__content")
//                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.getRandomName()));
//        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE);
//        $("[placeholder='Дата встречи']").setValue(DataGenerator.getCorrectDate(5));
//        $(withText("Запланировать")).click();
//        $("[data-test-id='replan-notification']").shouldBe(visible);
//        $("[data-test-id='replan-notification']>.notification__content")
//                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
//        $(withText("Перепланировать")).click();
//        $("[data-test-id='success-notification']>.notification__content").shouldBe(visible)
//                .shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.getCorrectDate(5)));
//    }

//    @Test
//    void shouldTestCardDelivery(){
//        open("http://localhost:9999");
//        $("[data-test-id=city] input").setValue("Екатеринбург");
//        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE);
//        $("[placeholder='Дата встречи']").setValue(DataGenerator.getCorrectDate(23));
//        $("[data-test-id=name] input").setValue("Андреев Андрей");
//        $("[data-test-id=phone] input").setValue("+12345678911");
//        $(Selectors.byClassName("checkbox__box")).click();
//        $(withText("Запланировать")).click();
//        Configuration.timeout = 11000;
//        $("[data-test-id='success-notification']").shouldBe(Condition.visible);
//        $("[data-test-id='success-notification']>.notification__content")
//                .shouldHave(text("Встреча успешно запланирована на "));
//        Configuration.timeout = 11000;
//        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.BACK_SPACE);
//        $("[placeholder='Дата встречи']").setValue(DataGenerator.getCorrectDate(5));
//        $(withText("Запланировать")).click();
//        Configuration.timeout = 11000;
//        $("[data-test-id='replan-notification']").shouldBe(visible);
//        $("[data-test-id='replan-notification']>.notification__content")
//                .shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
//        $(withText("Перепланировать")).click();
//        $("[data-test-id='success-notification']>.notification__content").shouldBe(visible)
//                .shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.getCorrectDate(5)));
//    }

    @Test
    void shouldTestAnotherDate() {
        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] input").setValue(dateOfDelivery);
        $("[data-test-id='name'] input").setValue(name);
        $("[data-test-id='phone'] input").setValue(phone);
        $("[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();
        Configuration.timeout = 11000;
        $("[data-test-id=success-notification] .notification__content").shouldHave(text("Встреча успешно запланирована на "+dateOfDelivery));
        $("[data-test-id=success-notification] button").click();
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(dateOfDeliveryAnother);
        $(withText("Запланировать")).click();
        Configuration.timeout = 11000;
        $("[data-test-id='replan-notification']").shouldHave(text("У вас уже запланирована встреча на другую дату"));
        $(withText("Перепланировать")).click();
    }

}