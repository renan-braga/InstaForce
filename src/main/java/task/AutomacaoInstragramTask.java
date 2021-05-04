package main.java.task;

import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import javafx.concurrent.Task;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
import main.java.model.AtributosDriver;
import main.java.model.Login;
import main.java.pageobjects.InstagramHome;
import main.java.pageobjects.InstagramLoginPage;
import main.java.pageobjects.InstagramPerfilPage;
import main.java.pageobjects.InstragramMensagensPage;

public class AutomacaoInstragramTask extends Task<Void> {

	private ListView<String> listaPalavras;
	private Login login;
	private TextInputControl txtAreaMensagem;
	private AtributosDriver atributos;

	public AutomacaoInstragramTask(Login login, ListView<String> listaPalavras, TextArea txtAreaMensagem) {
		this.login = login;
		this.listaPalavras = listaPalavras;
		this.txtAreaMensagem = txtAreaMensagem;
	}

	@Override
	protected Void call() throws Exception {
		int mensagensEnviadas = 0;
		InstagramLoginPage instagramLoginPage = new InstagramLoginPage();
		InstagramHome instagramHome = instagramLoginPage.fazerLogin(login);
		atributos = instagramHome.getAtributos();
		ListIterator<String> listIterator = listaPalavras.getItems().listIterator();
		// itera a lista de palavras chave
		while (listIterator.hasNext()) {
			String palavraChave = listIterator.next();
			instagramHome.fazerBuscaPalavraChave(palavraChave);
			instagramHome.extrairLinkPerfis();
			InstagramPerfilPage instagramPerfilPage = new InstagramPerfilPage(instagramHome.getAtributos());
			// itera os perfis
			while (instagramHome.hasNextPerfil()) {
				instagramPerfilPage.acessaPerfil(instagramHome.nextPerfil());
				if (instagramPerfilPage.seguir()) {
					InstragramMensagensPage instragramMensagensPage = instagramPerfilPage.preparaTelaMensagem();
					int tempo = instragramMensagensPage.preparaEnvioMensagem(10, 50);
					if (tempo > 0) {
						// tenta enviar a msg
						try {
							instragramMensagensPage.enviarMensagem(txtAreaMensagem.getText());
							while (tempo > 0) {
								TimeUnit.SECONDS.sleep(1);
								updateMessage("Aguardando " + (tempo--) + " segundos");
							}
							updateMessage("Perfis Adicionados: " + (++mensagensEnviadas));
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("Erro enviando a mensagem");
							instagramPerfilPage.fecharAbaPerfil();
							continue;
						}
					}
					if (isCancelled())
						instagramHome.getAtributos().getDriver().close();
				}
				instagramPerfilPage.fecharAbaPerfil();
			}
		}
		return null;
	}

	public AtributosDriver getAtributos() {
		return atributos;
	}

	public void setAtributos(AtributosDriver atributos) {
		this.atributos = atributos;
	}

}
