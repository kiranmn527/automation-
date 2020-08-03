package com.cucumber.testng.action;

import com.cucumber.testng.page_objects.web.BoxPage;
import com.cucumber.testng.page_objects.web.LoginPage;
import com.cucumber.testng.page_objects.web.MenuPage;

public class BoxActions {

    private String boxName;
    private MenuPage menuPage;
    private BoxPage boxPage;

    public BoxActions(MenuPage menuPage, BoxPage boxPage){
        this.menuPage = menuPage;
        this.boxPage = boxPage;
    }

    public void createBox(String boxName) {
        this.boxName = boxName;
        menuPage.gotoBoxManagement()
        .createNewBox(boxName);
    }

    public String verifyBoxCreated() {
        return boxPage.verifyBoxPresent(boxName);
    }

    public String getBoxNameFromBoxAction() {
        return this.boxName;
    }

    public BoxPage getBoxPage(){
        return this.boxPage;
    }
}
