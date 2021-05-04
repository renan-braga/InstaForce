package main.java.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import main.java.model.AtributosDriver;


public class InstagramPerfilPage {

	public AtributosDriver atributos;

	public InstagramPerfilPage(AtributosDriver atributos) {
		this.atributos = atributos;
	}

	public boolean seguir() {
		try {
			if (atributos.getExtrator().existeElemento(Paths.Perfil.BTN_SEGUIR)) {
				atributos.getDriver().findElement(By.xpath(Paths.Perfil.BTN_SEGUIR)).click();
				atributos.getWait()
						.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(Paths.Perfil.SEGUINDO)));
				atributos.getExtrator().waitForLoad();
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public InstragramMensagensPage preparaTelaMensagem() {
		atributos.getDriver().findElement(By.xpath(Paths.Perfil.BTN_ENVIAR_MSG)).click();
		atributos.getExtrator().waitForLoad();
		return new InstragramMensagensPage(atributos);
	}

	public void acessaPerfil(String nextPerfil) {
		atributos.getExtrator().abrirNovaAba();
		atributos.getExtrator().mudarParaAba(1);
		atributos.getDriver().get(nextPerfil);
		atributos.getExtrator().waitForLoad();
	}

	public void fecharAbaPerfil() {
		atributos.getDriver().close();
		atributos.getExtrator().mudarParaAba(0);
	}
}
