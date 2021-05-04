package main.java.pageobjects;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import main.java.model.AtributosDriver;

public class InstagramHome {

	private ArrayList<String> links;
	private Iterator<String> linksIterator;
	private AtributosDriver atributos;

	public InstagramHome() {
	}

	public InstagramHome(AtributosDriver atributos) {
		this.atributos = atributos;
	}

	public void fazerBuscaPalavraChave(String palavraChave) {
		atributos.getDriver().findElement(By.xpath(Paths.Home.PESQUISAR)).sendKeys(palavraChave);
		atributos.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(Paths.Home.LOAD)));
	}

	public void extrairLinkPerfis() {
		links = new ArrayList<>();
		Iterator<WebElement> iteratorLinks = atributos.getDriver().findElements(By.xpath(Paths.Home.LINKS)).iterator();
		while (iteratorLinks.hasNext()) {
			links.add(iteratorLinks.next().getAttribute("href"));
		}
		linksIterator = links.iterator();
	}

	public boolean hasNextPerfil() {
		return linksIterator.hasNext();
	}

	public String nextPerfil() {
		return linksIterator.next();
	}

	public AtributosDriver getAtributos() {
		return atributos;
	}

	public void setAtributos(AtributosDriver atributos) {
		this.atributos = atributos;
	}
}
