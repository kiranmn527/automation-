package com.cucumber.testng.application_utils.web_utils;

import com.cucumber.testng.project_constants.constants.Settings;
import com.cucumber.testng.project_constants.enams.BrowserType;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.function.Function;

import static com.cucumber.testng.page_objects.web.WebBasePage.getDriver;
import static java.lang.System.getProperty;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class WebObjects {
    private static final int DEFAULT_WAIT_SECONDS = 10;
    private static final int IMPLICITLY_WAIT = 20;
    private static final String BROWSER = "browser";
    private static final String URL = "url";
    //****************************
    // Action Functions
    //****************************

    public static void click(String... locatorKey) {
        for (String key : locatorKey) {
            click(key);
        }
    }

    public static void waitForPageToLoad() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            if (javaScriptExecutorPageReady(js)) {
                return;
            }
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                if (javaScriptExecutorPageReady(js)) {
                    extraWaitForPageToLoad(getDriver());
                    break;
                }
            }
        } catch (Exception e) {
            sleep(1000);
        }
    }

    public static boolean javaScriptExecutorPageReady(JavascriptExecutor js) {
        return js.executeScript("return document.readyState").toString().equals("complete");
    }

    public static void extraWaitForPageToLoad(WebDriver wdriver) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) js.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return javaScriptExecutorPageReady(js);
            }
        };
        wait.until(jQueryLoad);
        wait.until(jsLoad);
    }

    /*
     * Switches to the alert
     * */
    public static Alert switchToAlert(WebDriver driver) {
        int i = 0;
        Alert alert = null;
        while (i < 2) {
            sleep(4);
            try {
                WebDriverWait wait = new WebDriverWait(driver, IMPLICITLY_WAIT);
                wait.until(ExpectedConditions.alertIsPresent());
                alert = driver.switchTo().alert();
                break;
            } catch (Exception e) {
                i++;
            }
        }
        return alert;
    }

    public static void switchToFrame(com.cucumber.testng.project_constants.enams.Frame frame) {
        if (!frame.equals("")) {
            switchBack(getDriver());
            String[] frames = frame.getFrameName().split("\\|");
            for (int i = 0; i < frames.length; i++) {
                if (Settings.BrowserType.name().equalsIgnoreCase("IE")) {
                    WebDriverWait wait = new WebDriverWait(getDriver(), 20);
                    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@name='" + frames[i] + "' or @id = '" + frames[i] + "']")));
                } else
                    getDriver().switchTo().frame(getElement(By.xpath("//*[@name='" + frames[i] + "' or @id = '" + frames[i] + "']")));
            }
        }
    }

    public static void switchToFrame(By locatorKey) {
        getDriver().switchTo().frame(getElement(locatorKey));
    }

    public static void switchBack(WebDriver driver) {
        waitForPageToLoad();
        driver.switchTo().defaultContent();
    }

    public static void click(By locatorKey) {
        try {
            getElement(locatorKey).click();
        } catch (StaleElementReferenceException se) {
            sleep(500);
            getElement(locatorKey).click();
        } catch (WebDriverException se) {
            handleUnkownException(locatorKey, se).click();
        }
    }

    public static void forceClick(By locatorKey) {
        System.out.println("forceClick() - " + locatorKey);
        WebElement element = getElement(locatorKey);
        Actions action = new Actions(getDriver());
        action.moveToElement(element);
        action.click(element);
        action.build().perform();
    }

    public static void clickLink(String text) {
        click("//a[text()='" + text + "']");
    }

    public static void clickLinkWithPatialText(String text) {
        click("//a[contains(text(),'" + text + "')]");
    }

    public static void clickElementWithPartialText(String partialText) {
        click("//*[contains(text(),'" + partialText + "')]");
    }

    public static void input(By locatorKey, String data) {
        System.out.println("input() - Typing into " + locatorKey + " the value " + data);
        WebElement e;
        try {
            e = getElement(locatorKey);
            e.clear();
            e.sendKeys(data);
        } catch (StaleElementReferenceException se) {
            sleep(500);
            input(locatorKey, data);
        }
    }
//
//    public static boolean isSelectOptionPresent(By locatorKey, String dropdownOptions) {
//        WebElement dropdown = getElement(locatorKey);
//        Select select = new Select(dropdown);
//        boolean match = false;
//        List<WebElement> options = select.getOptions();
//        for (WebElement option : options) {
//            if (option.getText().equalsIgnoreCase(dropdownOptions)) {
//                match = true;
//                break;
//            }
//        }
//        return match;
//    }
//
//    /**
//     * Selects the specified dropdown option, or a random option if {@code option} is null. Returns the selected value.
//     */
//    public static String selectDropdownValue(By key, String option) {
//        System.out.println("Selecting option '" + option + "' from " + key);
//        Select select = new Select(getElement(key));
//        if (option != null) {
//            select.selectByVisibleText(option);
//            waitForPageToLoad();
//            return select.getFirstSelectedOption().getText();
//        }
//        List<WebElement> options = select.getOptions();
//        if (options.size() == 1 && options.get(0).getText().isEmpty()) {
//            return ""; // Return empty String if it's the only option
//        }
//        WebElement e = options.get(getRand(options.size() - 1));
//        while (e.getText().isEmpty() || e.getText().equals("Select...")) {
//            e = options.get(getRand(options.size() - 1));
//        }
//        select.selectByVisibleText(e.getText());
//        return e.getText();
//    }
//
//    /**
//     * Selects a dropdown option that contains the String {@code option}.
//     */
//    public static void selectDropdownOptionLike(By key, String option) {
//        System.out.println("Selecting option containing '" + option + "' from " + key);
//        String value = "";
//        try {
//            Select select = new Select(getElement(key));
//            for (WebElement e : select.getOptions()) {
//                if (e.getText().contains(option)) {
//                    select.selectByVisibleText(e.getText());
//                    waitForPageToLoad();
//                    break;
//                }
//            }
//        } catch (StaleElementReferenceException e) {
//            sleep(3000);
//            waitForPageToLoad();
//            selectDropdownOptionLike(key, option);
//        }
//    }

    public static void keyPress(By locatorKey, Keys key) {
        System.out.println("keyPress() - Performing a " + key.name() + " key press");
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getElement(locatorKey));
        actions.sendKeys(key);
        actions.build().perform();
    }

    public static void keyPress(By locatorKey, String key) {
        // logs("keyPress() - Performing a " + key + " key press");
        moveAndSendKeys(locatorKey, key);
    }

    public static void keyPress(int key) {
        // logs("keyPress() - Performing a " + key + " key press");
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(key);
    }

    private static void moveAndSendKeys(By locatorKey, String key) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getElement(locatorKey));
        actions.sendKeys(key);
        actions.build().perform();
    }

    public static void setCheckBoxTo(By locatorKey, boolean check) {
        // logs("setCheckBoxTo() - Changing check box at " + locatorKey + " to " + check);
        WebElement e = getElement(locatorKey);
        String state = "";
        try {
            state = e.getAttribute("checked");// true if checked or null if not
        } catch (StaleElementReferenceException se) {
            sleep(500);
            setCheckBoxTo(locatorKey, check);
        }
        if (check && state == null) {
            e.click();
        } else if (!check && state != null) {
            e.click();
        }
    }

    public static void dragAndDropTo(By elementToBeMoved, By dropToLocatorKey) {
        System.out.println(("dragAndDropTo() - Moving " + elementToBeMoved + " to " + dropToLocatorKey));
        WebElement from = getElement(elementToBeMoved);
        WebElement to = getElement(dropToLocatorKey);

        Action dragAndDrop = new Actions(getDriver()).clickAndHold(from)
                .moveToElement(to)
                .release(to)
                .build();
        dragAndDrop.perform();
    }

    public static void mouseOver(By locatorKey) {
        new Actions(getDriver()).moveToElement(getElement(locatorKey)).perform();
    }

    //****************************
    // Validation Functions
    //****************************

    public static void verifyElementsAreVisible(By... locatorKey) {
        for (By key : locatorKey) {
            verifyElementPresent(key);
        }
    }

    public static void verifyElementPresent(By locatorKey) {
        System.out.println("Verifying if Element Present: " + locatorKey);
        assertTrue(isElementVisible(locatorKey, DEFAULT_WAIT_SECONDS), "Element not visible - " + locatorKey);
    }

    public static void verifyElementPresent(By... locatorKeys) {
        for (By locatorKey : locatorKeys) {
            verifyElementPresent(locatorKey);
        }
    }

    public static void holdShiftAndClick(By locatorKey) {
        Actions shiftClick = new Actions(getDriver());
        shiftClick.keyDown(Keys.SHIFT).click(getElement(locatorKey)).keyUp(Keys.SHIFT).perform();
    }

    public static void verifyElementNotPresent(By locatorKey) {
        System.out.println("Verifying if Element is not Present: " + locatorKey);
        assertTrue(!(isElementVisible(locatorKey, DEFAULT_WAIT_SECONDS)), "Element is visible - " + locatorKey);
    }

    public static void verifyElementInvisible(By locatorKey) {
        assertFalse(isElementVisible(locatorKey), "Element supposed to be invisible - " + locatorKey);
    }

    //****************************
    // Utility Functions
    //****************************

    public static String getText(By locatorKey) {
        System.out.println("getText() at :" + locatorKey.toString());
        String text = "";
        try {
            text = getElement(locatorKey).getText().trim();
        } catch (StaleElementReferenceException se) {
            sleep(500);
            text = getElement(locatorKey).getText().trim();
        } catch (WebDriverException se) {
            text = handleUnkownException(locatorKey, se).getText().trim();
        }
        return text;
    }

    public static int getElementCount(By locatorKey) {
        sleep(500);
        int count = 0;
        try {
            count = getWebElements(locatorKey).size();
        } catch (StaleElementReferenceException se) {
            sleep(500);
            count = getWebElements(locatorKey).size();
        }
        System.out.println("getElementCount(" + locatorKey + ")-> has returned " + count + " items.");
        return count;
    }

    /**
     * Returns a {@code WebElement} once it is located and clickable (visible and enabled), otherwise the test will fail.
     */
    public static WebElement getElement(By locatorKey) {
        System.out.println("getElement() - Getting Element at " + locatorKey);
        waitForPageToLoad();
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS);
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locatorKey));
            scrollToElement(element);
            Actions action = new Actions(getDriver());
            action.moveToElement(element);
            action.build().perform();
            return element;
        } catch (StaleElementReferenceException se) {
            sleep(500);
            return getElement(locatorKey);
        } catch (WebDriverException se) {
            return handleUnkownException(locatorKey, se);
        } catch (Exception ex) {
            Assert.fail("Failure in Element Extraction using " + locatorKey + " - " + ex.getMessage());
            return null;
        }
    }

    public static WebElement handleUnkownException(By locatorKey, WebDriverException se) {
        String error = se.getMessage();
        if (error.contains("Cannot find context with specified id")) {
            return getElement(locatorKey);
        } else {
            Assert.fail("Failure in Element Extraction using " + locatorKey + " - " + se.getMessage());
            return null;
        }
    }

    public static List<WebElement> getWebElements(By locatorKey) {
        System.out.println("getWebElements() - Getting list of elements from " + locatorKey);
        try {
            return waitForPresenceOfElements(locatorKey);
        } catch (Exception e) {
            System.out.println("getWebElements() - Not all elements visible using path " + locatorKey);
            return new ArrayList<>();
        }
    }

    public static List<String> getElementStrings(By locatorKey) {
        System.out.println("getElementStrings() - Getting a list of strings from " + locatorKey.toString());
        return new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS)
                .ignoring(StaleElementReferenceException.class)
                .until(new Function<WebDriver, List<String>>() {
                    public List<String> apply(WebDriver driver) {
                        return stringConverter(getWebElements(locatorKey));
                    }
                });
    }

    private static List<String> stringConverter(List<WebElement> e) {
        List<String> elementString = new ArrayList<>();
        for (WebElement element : e) {
            elementString.add(element.getText());
        }
        return elementString;
    }

    /**
     * Returns true if element/s are present on the DOM and are visible before timeout, otherwise false.
     * Visibility means that the element/s are both displayed and have a height and width that is greater than 0.
     */
    public static boolean isElementVisible(By locatorKey, int timeout) {
        try {
            return new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS)
                    .ignoring(StaleElementReferenceException.class)
                    .until(new Function<WebDriver, Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            List<WebElement> e = waitForVisibilityOfElements(locatorKey, timeout);
                            return e != null && e.size() != 0;
                        }
                    });
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Returns true if element/s are present on the DOM and are visible within 1 second, otherwise false.
     * Visibility means that the element/s are both displayed and have a height and width that is greater than 0.
     */
    public static boolean isElementVisible(By locatorKey) {
        return isElementVisible(locatorKey, DEFAULT_WAIT_SECONDS);
    }

    public static boolean compareLists(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }
        ArrayList<String> result = new ArrayList<>(list1);
        result.retainAll(list2);
        return result.size() == list2.size();
    }

    public static void refresh() {
        getDriver().navigate().refresh();
    }

    public static String getElementAttribute(By locatorKey, String attribute) {
        System.out.println("getElementAttribute() - getting Attribute " + attribute + " from " + locatorKey);
        return waitForPresenceOfElement(locatorKey).getAttribute(attribute);
    }

//    public static String getSelectedDropdownOption(By locatorKey) {
//        return new Select(getElement(locatorKey)).getFirstSelectedOption().getText();
//    }

    // ****************************
    //  Wait Functions
    // ****************************

    /**
     * Calls Tread.sleep(). Ideally we shouldn't have this but there are odd quirks in the application that requires
     * this as a work around. <b>Try to use other wait methods before using this one<b/>.
     */
    public static void sleep(long sleepMillis) {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            System.out.println("sleep() - Error calling Thread.sleep().");
        }
    }

    /**
     * Waits until element is present on the DOM and is visible, or until timeout.
     * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
     *
     * @throws TimeoutException if timeout is reached before element is visible.
     */
    public static WebElement waitForVisibilityOfElement(String key) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(key)));
    }

    /**
     * Waits until all elements are present on the web page that match the locator are visible, or until timeout.
     * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
     *
     * @throws TimeoutException if timeout is reached before element is visible.
     */
    private static List<WebElement> waitForVisibilityOfElements(By locatorKey, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locatorKey));
    }

    /**
     * Waits until element is either invisible or not present on the DOM.
     *
     * @throws TimeoutException if timeout is reached before element is invisible.
     */
    public static void waitForInvisibilityOfElement(By locatorKey) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locatorKey));
    }

    /**
     * Waits until element is present on the DOM, or until timeout.
     *
     * @throws TimeoutException if timeout is reached before element is present.
     */
    private static WebElement waitForPresenceOfElement(By locatorKey) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locatorKey));
    }

    private static List<WebElement> waitForPresenceOfElements(By locatorKey) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS);
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorKey));
    }

    public static void verifyElementClickable(By locationKey) {
        assertTrue(isClickable(locationKey), locationKey+ " is not clickable");
    }

    public static boolean isClickable(By locationKey) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_WAIT_SECONDS);
            wait.until(ExpectedConditions.elementToBeClickable(getElement(locationKey)));
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public static void verifyElementNotClickable(By locationKey) {
        assertFalse(isClickable(locationKey), locationKey+ " is clickable");
    }

    /**
     * Compare utility methods
     */
    public static void verifyEachListElementContainsString(List<String> list, String seachString) {
        System.out.println("Verifying list " + list.toString() + " contains " + seachString);
        for (String item : list) {
            assertTrue(item.toLowerCase().contains(seachString.toLowerCase()));
        }
    }

    public static void verifyEachListElementEqualsString(List<String> list, String seachString) {
        System.out.println("Verifying list " + list.toString() + " equals " + seachString);
        for (String item : list) {
            assertTrue(item.equalsIgnoreCase(seachString));
        }
    }


    public static void goToElement(WebDriver wdriver, By locatorKey) {
        JavascriptExecutor je = (JavascriptExecutor) wdriver;
        WebElement element = getElement(locatorKey);
        je.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static String getCurrentDate(String dateFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }

    public static String getRelativeDate(String dateFormat, int days) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDate localDate = LocalDate.now();
        LocalDate tenDaysAgo = localDate.minusDays(days);
        return dtf.format(tenDaysAgo);
    }

    public static void isSorted(List<String> datesList, boolean descending) {
        ArrayList<Date> dataAsDisplayedInDefaultView = convertToDateList(datesList, "dd-MMM-yy");
        ArrayList<Date> expectedOrder = new ArrayList<Date>();
        //sort the copy on Date Descending, this is how the data is sorted in the default view on this website
        Collections.sort(expectedOrder, new Comparator<Date>() {

            @Override
            public int compare(Date o1, Date o2) {
                return o1.compareTo(o2);
            }
        });
        //This will pass only if the elements in both the lists appear in the same order
        //assertTrue(dataAsDisplayedInDefaultView.equals(expectedOrder));
    }

    private static ArrayList<Date> convertToDateList(List<String> datesList, String format) {
        ArrayList<Date> dateListToBetested = new ArrayList<Date>();
        for (String dateList : datesList) {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            try {
                if (!dateList.equals("")) {
                    Date date = formatter.parse(dateList);
                    dateListToBetested.add(date);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dateListToBetested;
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView();", element);
    }

    public static void navigateBack() {
        System.out.println("Navigating back");
        getDriver().navigate().back();
    }
}
