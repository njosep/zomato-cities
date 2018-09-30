package com.zomato.cities;

import cucumber.api.Scenario;


public class TestScenario 
{
	
	private Scenario scenario;
	public static String ScenarioName = null;
	public static int ScenarioID = 0;
	
	
	public void setscenario(Scenario scenario) {
		this.scenario = scenario;
		ScenarioName = scenario.getName();
		ScenarioID++;
	}
	}
