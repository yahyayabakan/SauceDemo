package com.SauceDemo.stepDefinitions;

import com.SauceDemo.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.SauceDemo.utilities.Driver.driver;

public class Hooks {

    @After
    public void tearDownScenario(Scenario scenario) {
        //if my scenario failed
        // go and take screen shot
        if (scenario.isFailed()) {
            byte[] screenShot = ((TakesScreenshot) driver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", scenario.getName());
        }
        Driver.closeDriver();
    }
}
