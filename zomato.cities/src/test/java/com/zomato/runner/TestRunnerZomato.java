package com.zomato.runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features = { "src/test/resource" }, plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:src/ZomatoCities_TestReport.html" }, tags = {
				"@zomato" }, monochrome = true, glue = { "com.zomato.test" }, dryRun = false)

public class TestRunnerZomato {



	@AfterClass
	public static void reportSetup() {
		Reporter.loadXMLConfig(new File("src/test/resource/extent-config.xml"));
		Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
		Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
		Reporter.setSystemInfo("64 Bit", "Windows 10");
		Reporter.setSystemInfo("3.3.9", "Maven");
		Reporter.setSystemInfo("1.8.0_66", "Java Version");
		Reporter.setTestRunnerOutput("Cucumber JUnit Test Runner");
	}
}
