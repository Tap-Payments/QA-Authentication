package com.tappayments.automation.qaauthentication.base;

import com.tappayments.automation.qaauthentication.config.ConfigManager;
import com.tappayments.automation.qaauthentication.utils.AppConstants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup(){

        RestAssured.baseURI = ConfigManager.getPropertyValue(AppConstants.BASE_URI_VALUE);
    }
}
