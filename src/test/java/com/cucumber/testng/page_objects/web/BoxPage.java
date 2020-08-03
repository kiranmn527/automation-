package com.cucumber.testng.page_objects.web;

import org.openqa.selenium.By;

import static com.cucumber.testng.application_utils.web_utils.WebObjects.*;
import static com.cucumber.testng.project_constants.enams.Frame.MAIN;
import static org.testng.Assert.assertEquals;

public class BoxPage extends WebBasePage {
    private String boxName;
    private By CREATE_NEW_BOX_LINK = By.xpath("//a[@id='create-new-box']");
    private static final String BOX_POP_UP = "//div[@id='box-dialog']";
    private By NUMBER_INPUT = By.xpath(BOX_POP_UP + "//input[@name='number']");
    private By SAVE_BTN = By.xpath(BOX_POP_UP + "//*[@id='submitBtn']");
    private static final String SEARCH_TABLE = "//table[@id='boxmgt']";

    public String getBoxNameFromBoxPage() {
        return this.boxName;
    }

    public BoxPage createNewBox(String boxName) {
        clickCreateBox();
        populateBoxName(boxName);
        save();
        return this;
    }

    private void clickCreateBox() {
        switchToFrame(MAIN);
        if (getElementCount(CREATE_NEW_BOX_LINK) > 0) {
            click(CREATE_NEW_BOX_LINK);
        }
    }

    private void populateBoxName(String boxName) {
        if (boxName != null) {
            input(NUMBER_INPUT, boxName);
        }
    }

    private void save() {
        click(SAVE_BTN);
    }

    public String verifyBoxPresent(String boxName) {
        switchToFrame(MAIN);
        assertEquals(getBoxCount(boxName), 1);
        return boxName;
    }

    private int getBoxCount(String boxName) {
        return getElementCount(By.xpath(SEARCH_TABLE + "//tbody//tr//td[text()='" + boxName + "'][1]"));
    }
}
