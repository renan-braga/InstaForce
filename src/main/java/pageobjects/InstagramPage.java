package main.java.pageobjects;

import main.java.model.AtributosDriver;

public class InstagramPage {

	public AtributosDriver atributos;

	public InstagramPage() {
		atributos = new AtributosDriver();
	}

	public void clonarAtributos(InstagramPage page) {
		page.atributos = this.atributos;
	}

}
