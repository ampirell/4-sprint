import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.orderPage;

@RunWith(Parameterized.class)
public class OrderPageTest {
    final String nameTest;
    final String surnameTest;
    final String addressTest;
    final String phoneTest;
    final String stationName;
    final String dateTest;
    final String termOfRentTest;
    final String colour;
    final String commentTest;
    final String success;

    public OrderPageTest(String nameTest, String surnameTest,  String addressTest, String phoneTest, String stationName, String dateTest, String termOfRentTest, String colour, String commentTest, String success ) {
        this.nameTest = nameTest;
        this.surnameTest = surnameTest;
        this.addressTest = addressTest;
        this.phoneTest = phoneTest;
        this.stationName = stationName;
        this.dateTest = dateTest;
        this.termOfRentTest = termOfRentTest;
        this.colour = colour;
        this.commentTest = commentTest;
        this.success = success;
    }

    WebDriver driver;
    @Before
    public void setUp(){
        driver = new FirefoxDriver();
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {"Эксперт", "Яндекса", "Толстого 16", "Сокольники","12345678901", "01.05.2024", "трое суток", "серая безысходность", "Вжууух", "Заказ оформлен"},
                {"Студент", "Старающийся", "Пум 12", "Красносельская", "09876543210", "31.05.2024", "сутки", "чёрный жемчуг", "С ветерком", "Заказ оформлен"}
        };
    }

    @Test
    public void letsMakeOrder() {
        orderPage objOrderPage = new orderPage(driver);

        driver.get("https://qa-scooter.praktikum-services.ru/");
        objOrderPage.clickToTopButton();//Нажать Заказать
        objOrderPage.acceptCookie();//скипнуть куки
        objOrderPage.firstStepOfOrder(nameTest, surnameTest, addressTest, stationName, phoneTest);//заполнить поля на 1 странице
        objOrderPage.clickNext();//нажать Далее
        objOrderPage.secondStepOfOrder(dateTest, termOfRentTest, colour, commentTest);//заполнить поля на 2 странице
        objOrderPage.clickToFinishOrder();
        objOrderPage.acceptOrder(success);//нажать Заказать, Да и сравнить заголовок уведомления
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}