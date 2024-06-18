package ru.yandex.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class faqPage extends BasePage {

    public faqPage(WebDriver driver) {
        super(driver);
    }

    //локатор стрелочки
    private final String paragraph = ".//*[@id='accordion__heading-%d']";

    //локатор текста
    private final String paragraphText = ".//*[@id='accordion__panel-%d']";

    //скролл и клик на пункт
    public void clickElementOfListQuestions(int paragraphIndex) {
        WebElement element = driver.findElement(By.xpath(String.format(paragraph, paragraphIndex)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(paragraph, paragraphIndex))));
        driver.findElement(By.xpath(String.format(paragraph, paragraphIndex))).click();
    }

    //получить текст открывшегося пункта
    public String getParagraphText(int paragraphIndex){
        return driver.findElement(By.xpath(String.format(paragraphText, paragraphIndex))).getText();
    }
}
