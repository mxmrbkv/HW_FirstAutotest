package HomeWorkJS;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class OtusTest {

    protected Logger logger = LogManager.getLogger(OtusTest.class);
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер установлен");
    }

    @AfterEach
    public void setDown() {
        if (driver != null)
            driver.quit();
    }

    protected void auth() throws InterruptedException {
        String login = "mxmrbkv95@gmail.com";
        String password = "AutoTestOtus2022!";
        driver.findElement(By.cssSelector("button[data-modal-id=new-log-reg]")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@type='text' and @placeholder='Электронная почта']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@placeholder='Введите пароль']")).sendKeys(password);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();
        logger.info("Авторизация прошла успешно");
    }

    protected void enterLK() throws InterruptedException {

        Thread.sleep(1000);
        driver.get("https://otus.ru/lk/biography/personal/");
        logger.info("Перешел в личный кабинет");
    }
    @Test

    public void Otus() throws InterruptedException {

        // Перейти на Otus
        driver.get("https://otus.ru");
        logger.info("Otus открыт");

        //Авторизоваться на сайте
        auth();
        logger.info("Пользователь авторизован");

        //Войти в личный кабинет
        enterLK();

        // В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        driver.findElement(By.cssSelector("input[id='id_fname']")).clear();
        driver.findElement(By.cssSelector("input[id='id_fname_latin']")).clear();
        driver.findElement(By.cssSelector("input[id='id_lname']")).clear();
        driver.findElement(By.cssSelector("input[id='id_lname_latin']")).clear();
        driver.findElement(By.cssSelector("input[id='id_blog_name']")).clear();
        driver.findElement(By.xpath("//input[@name='date_of_birth']")).clear();
        logger.info("Поля очищенны");

        driver.findElement(By.cssSelector("input[id='id_fname']")).sendKeys("Максим");
        driver.findElement(By.cssSelector("input[id='id_fname_latin']")).sendKeys("Maksim");
        driver.findElement(By.cssSelector("input[id='id_lname']")).sendKeys("Ребиков");
        driver.findElement(By.cssSelector("input[id='id_lname_latin']")).sendKeys("Rebikov");
        driver.findElement(By.cssSelector("input[id='id_blog_name']")).sendKeys("mxmrbkv");
        driver.findElement(By.xpath("//input[@name='date_of_birth']")).sendKeys("18.04.1995");
        logger.info("Дата рождения введена");


        //Страна
        if(!driver.findElement(By.cssSelector("div[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-dependent-master js-lk-cv-custom-select']"))
                .getText().contains("Россия"))
        {
            driver.findElement(By.cssSelector("div[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-dependent-master js-lk-cv-custom-select']"))
                    .click();
            driver.findElement(By.xpath("//*[contains(text(), 'Россия')]"))
                    .submit();
        }
        logger.info("Страна добавлена");

        //Город
        if (!driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)"))
                .getText().contains("Тула"))
        {
            driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)"))
                    .click();
            driver.findElement(By.xpath("//*[contains(text(), 'Тула')]"))
                    .submit();
        }
        logger.info("Город добавлен");

        //Контактная информация
        driver.findElement(By.xpath("//button[@type='button'][(text() = 'Добавить')]"))
                .click();
        driver.findElement(By.xpath("//*[contains(@class, 'select')][.//*[text() = 'Способ связи']]"))
                .click();
        driver.findElement(By.xpath(""))
                .click();  // Выборс способа связи


    }


}

     //*[contains(@class, 'select')][.//*[contains(text(), 'Тelegram')]]
     //span[@calss='placeholder'][text() = 'Способ связи']
    //*[contains(@class, 'select')][.//*[text() = 'Способ связи']][contains(@class, 'js-custom-select-input ')]
     //*[contains(@class, 'select')][.//*[contains(text(), 'Тelegram')][contains(@class, 'js-custom-select-presentation')]][@data-selected-option-class]
    //*[contains(@class, 'select')][.//*[text() = 'Способ связи']][contains(@name, 'contact-1-service')]
    //*[contains(@class, 'select')][.//*[text() = 'Способ связи']]
// div