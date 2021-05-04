package main.java.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import main.java.model.AtributosDriver;
import main.java.utils.TemporizadorAleatorio;


public class InstragramMensagensPage {

	private AtributosDriver atributos;

	public InstragramMensagensPage(AtributosDriver atributos) {
		this.atributos = atributos;
	}

	public void enviarMensagem(String mensagem) {
		try {
			atributos.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Paths.Mensagens.TXT_MSG)));
			atributos.getDriver().findElement(By.xpath(Paths.Mensagens.TXT_MSG)).sendKeys(mensagem);
			atributos.getDriver().findElement(By.xpath(Paths.Mensagens.BTN_ENVIAR)).click();
			atributos.getWait().until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(Paths.Mensagens.msgListBox(mensagem))));
			atributos.getExtrator().waitForLoad();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Mensagem já enviada");
		}
	}

	public int preparaEnvioMensagem(int tempoInicio, int tempoFim) {
		int tempo = 0;
		try {
			atributos.getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Paths.Mensagens.TXT_MSG)));
			tempo = TemporizadorAleatorio.entre(tempoInicio, tempoFim);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempo;
	}
}
