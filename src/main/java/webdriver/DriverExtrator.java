package main.java.webdriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverExtrator {

	private WebDriver driver;

	public DriverExtrator(boolean headless, boolean disableImages) throws IOException {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		if (headless) {
			options.addArguments("--headless");
		} else {
			options.addArguments("--disable-notifications");
		}
		if (disableImages) {
			options.addArguments("--disable-gpu");
			options.addArguments("--blink-settings=imagesEnabled=false");
		}
		driver = new ChromeDriver(options);
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.MINUTES);
		driver.manage().window().maximize();
	}

	public void waitForLoad() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new WebDriverWait(driver, 60).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));

	}

	public boolean existeElemento(String path) {
		return driver.findElements(By.xpath(path)).size() > 0;
	}

	public int retornaNumeroElementos(String path) {
		return driver.findElements(By.xpath(path)).size();
	}

	public void hoverMouseJavaScript(WebElement webElement) {
		String javaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(javaScript, webElement);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void abrirNovaAba() {
		((JavascriptExecutor) driver).executeScript("window.open()");
	}

	public void envioLentoCaracteres(String xpath, String mensagem) {
		WebElement element = driver.findElement(By.xpath(xpath));
		for (int i = 0; i < mensagem.toCharArray().length; i++) {
			element.sendKeys(mensagem.charAt(i) + "");
			try {
				TimeUnit.MILLISECONDS.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void mudarParaAba(int aba) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(aba));
	}

	public void clicarSeExiste(String proximaPagina) {
		if (this.existeElemento(proximaPagina)) {
			driver.findElement(By.xpath(proximaPagina)).click();
		}
	}

	public boolean elementoVisivel(String path) {
		return driver.findElement(By.xpath(path)).isDisplayed();
	}

	public boolean elementoComTexto(String path) {
		return driver.findElement(By.xpath(path)).getText() != ""
				&& driver.findElement(By.xpath(path)).getText() != null;
	}

}
