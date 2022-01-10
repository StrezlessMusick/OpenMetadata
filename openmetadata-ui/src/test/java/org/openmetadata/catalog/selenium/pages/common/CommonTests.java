/*
 *  Copyright 2021 Collate
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.openmetadata.catalog.selenium.pages.common;

import java.time.Duration;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openmetadata.catalog.selenium.events.Events;
import org.openmetadata.catalog.selenium.properties.Property;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

@Order(17)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommonTests {

  static WebDriver webDriver;
  static String url = Property.getInstance().getURL();
  static Actions actions;
  static WebDriverWait wait;
  Integer waitTime = Property.getInstance().getSleepTime();

  @BeforeEach
  public void openMetadataWindow() {
    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/linux/chromedriver");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--window-size=1280,800");
    webDriver = new ChromeDriver(options);
    actions = new Actions(webDriver);
    wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
    webDriver.manage().window().maximize();
    webDriver.get(url);
  }

  @Test
  @Order(1)
  public void tagDuplicationCheck() throws InterruptedException {
    Events.click(webDriver, By.cssSelector("[data-testid='closeWhatsNew']")); // Close What's new
    Thread.sleep(waitTime);
    Events.click(webDriver, By.cssSelector("[data-testid='tables']")); // Tables
    Events.sendKeys(webDriver, By.cssSelector("[data-testid='searchBox']"), "dim_location");
    Events.click(webDriver, By.cssSelector("[data-testid='data-name']"));
    Thread.sleep(waitTime);
    actions.moveToElement(webDriver.findElement(By.xpath("//div[@data-testid='tag-conatiner']//span"))).perform();
    Events.click(webDriver, By.xpath("//div[@data-testid='tag-conatiner']//span"));
    Events.click(webDriver, By.cssSelector("[data-testid='associatedTagName']"));
    Events.sendKeys(webDriver, By.cssSelector("[data-testid='associatedTagName']"), "PersonalData.Personal");
    Events.click(webDriver, By.cssSelector("[data-testid='list-item']"));
    Events.sendKeys(webDriver, By.cssSelector("[data-testid='associatedTagName']"), "User.FacePhoto");
    Events.click(webDriver, By.cssSelector("[data-testid='list-item']"));
    Events.click(webDriver, By.cssSelector("[data-testid='saveAssociatedTag']"));
    Thread.sleep(2000);
    Object tagCount =
        webDriver.findElements(By.xpath("//*[text()[contains(.,'" + "#PersonalData.Personal" + "')]] ")).size();
    Assert.assertEquals(tagCount, 2);
  }

  @AfterEach
  public void closeTabs() {
    ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
    String originalHandle = webDriver.getWindowHandle();
    for (String handle : webDriver.getWindowHandles()) {
      if (!handle.equals(originalHandle)) {
        webDriver.switchTo().window(handle);
        webDriver.close();
      }
    }
    webDriver.switchTo().window(tabs.get(0)).close();
  }
}