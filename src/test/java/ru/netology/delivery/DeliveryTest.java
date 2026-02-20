package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldTestSuccessOrder() {
        open("http://0.0.0.0:9999");


        $("[data-test-id='city'] input").setValue("Уфа");


        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        String planningDate = generateDate(3);
        $("[data-test-id='date'] input").setValue(planningDate);


        $("[data-test-id='name'] input").setValue("Данис Ахметов-Аглетдинов");


        $("[data-test-id='phone'] input").setValue("+79373264679");


        $("[data-test-id='agreement']").click();


        $$("button").find(Condition.exactText("Забронировать")).click();


        $("[data-test-id='notification']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate));
    }
}
