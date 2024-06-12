package ru.yandex.practicum;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import static com.codeborne.selenide.Selectors.byText;


public class orderPage extends BasePage {

    public orderPage(WebDriver driver) {
        super(driver);
    }

    //локатор кнопки "Заказать" на верху страницы

    private final By orderButton = By.className("Button_Button__ra12g");
    //локатор кнопки "Заказать" в середине страницы

    private final By orderBottomButton = By.className("Button_Middle__1CSJM");
    //локатор поля "Имя"

    private  final By name =By.xpath(".//input[@placeholder='* Имя']") ;
    //локатор поля "Фамилия"

    private final By surname=By.xpath(".//input[@placeholder='* Фамилия']");
    //локатор поля Адрес

    private final By  address=By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор поля Телефон

    private final By  phone=By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор поля Станция метро

    private  final By station=By.xpath(".//input[@placeholder='* Станция метро']");
    //локатор выбора станции
    private final By selectStation=By.className("select-search");
    //локатор кнопки "Далее"
    private final By buttonNext=By.className("Button_Middle__1CSJM");

    //локатор Когда привезти
    private final By  dateOfOrder=By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //локатор даты в календаре
    private final By chooseDateOfOrder=By.className("react-datepicker__day--001");
    //срок аренды
    private final By termOfRent=By.className("Dropdown-control");
    // - выпадающий список
    private final By chooseTermOfRen =By.xpath(".//div[@class='Dropdown-menu']");
    // Цвет - чекбокс
    private final By chooseColour=By.className("Order_Checkboxes__3lWSI");
    //Комментарий
    private final By comment=By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // кнопка Заказать
    private final By makeOrder=By.xpath(".//button[text()='Заказать']");
    //Кнопка Да
    private final By accept=By.xpath(".//button[text()='Да']");
    //хедер Заказ оформлен
    private final By makeOrderSuccess=By.className("Order_ModalHeader__3FDaJ");

    //нажать на кнопку Заказать вверху страницы
    public void clickToTopButton(){
        driver.findElement(orderButton).click();
    }
    //нажать на кнопку Заказать внизу страницы
    public void clickToBottomButton(){
        driver.findElement(orderBottomButton).click();
    }
    //заподнить поля на 1 странице и нажать далее
    public void firstStepOfOrder(String nameTest, String surnameTest, String addressTest, String phoneTest, String stationName){
        driver.findElement(name).sendKeys(nameTest);
        driver.findElement(surname).sendKeys(surnameTest);
        driver.findElement(address).sendKeys(addressTest);
        driver.findElement(phone).sendKeys(phoneTest);
        driver.findElement(station).click();
        driver.findElement(selectStation).findElement(byText(stationName)).click();
    }

    //добавить скрытие Да все привыкли
    public void acceptCookie(){
        driver.findElement(By.className("App_CookieButton__3cvqF")).click();
    }
    //нажать Далее
    public void clickNext(){
        driver.findElement(buttonNext).click();
    }
    //заполнить все поля на 2 странице и нажать заказать
    public void secondStepOfOrder(String dateTest, String termOfRentTest, String colour, String commentTest){
        driver.findElement(dateOfOrder).click();
        driver.findElement(chooseDateOfOrder).sendKeys(dateTest, Keys.ENTER);
        driver.findElement(termOfRent).click();
        driver.findElement(chooseTermOfRen).findElement(byText(termOfRentTest)).click();
        driver.findElement(chooseColour).findElement(byText(colour)).click();
        driver.findElement(comment).click();
        driver.findElement(comment).sendKeys(commentTest);

    }
    //нажать кнопку Заказать
    public void clickToFinishOrder(){
        driver.findElement(makeOrder).click();
        driver.findElement(makeOrder).click();
    }

    //проверка формирования заказа
    public void acceptOrder(String success){
        driver.findElement(accept).click();
        Assert.assertEquals(driver.findElement(makeOrderSuccess).getText(), success);
    }
}
