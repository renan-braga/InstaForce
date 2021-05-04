package main.java.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import main.java.model.AtributosDriver;
import main.java.model.Login;

public class InstagramLoginPage {

	private AtributosDriver atributos;

	public InstagramLoginPage() {
		setAtributos(new AtributosDriver());
	}

	public InstagramHome fazerLogin(Login login) {
		getAtributos().getDriver().get("https://www.instagram.com");
		getAtributos().getExtrator().waitForLoad();
		getAtributos().getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Paths.Login.USERNAME)));
		getAtributos().getDriver().findElement(By.xpath(Paths.Login.USERNAME)).sendKeys(login.getUsuario());
		getAtributos().getDriver().findElement(By.xpath(Paths.Login.SENHA)).sendKeys(login.getSenha());
		getAtributos().getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Paths.Login.BTN_LOGIN)));
		getAtributos().getDriver().findElement(By.xpath(Paths.Login.BTN_LOGIN)).click();
		getAtributos().getExtrator().waitForLoad();

		getAtributos().getWait()
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Paths.Login.SALVA_INFORMACOES)));
		getAtributos().getDriver().findElement(By.xpath(Paths.Login.SALVA_INFORMACOES)).click();
		getAtributos().getExtrator().waitForLoad();
		return new InstagramHome(getAtributos());
	}

	public AtributosDriver getAtributos() {
		return atributos;
	}

	public void setAtributos(AtributosDriver atributos) {
		this.atributos = atributos;
	}
}