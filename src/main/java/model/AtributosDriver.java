package main.java.model;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.webdriver.DriverExtrator;

public class AtributosDriver {

	private DriverExtrator extrator;
	private WebDriver driver;
	private WebDriverWait wait;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}

	public void setExtrator(DriverExtrator extrator) {
		this.extrator = extrator;
	}

	public AtributosDriver() {
		try {
			extrator = new DriverExtrator(false, false);
			driver = extrator.getDriver();
			wait = new WebDriverWait(driver, 10);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DriverExtrator getExtrator() {
		return extrator;
	}
}
