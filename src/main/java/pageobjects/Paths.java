package main.java.pageobjects;

public interface Paths {

	interface Login {
		String USERNAME = "//input[@name='username']";
		String SENHA = "//input[@name='password']";
		String BTN_LOGIN = "//button[@type='submit']";
		String SALVA_INFORMACOES = "//button[contains(text(),'Agora não')]";
	}

	interface Home {
		String PESQUISAR = "//input[@placeholder='Pesquisar']";
		String LOAD = "//input[@placeholder='Pesquisar']/..//*[@aria-label='Carregando...']";
		String LINKS = "//div[@role='none']/a";
	}

	interface Perfil {
		String BTN_SEGUIR = "//button[contains(text(),'Seguir')]";
		String SEGUINDO = "//span[@aria-label='Seguindo']";
		String BTN_ENVIAR_MSG = "//button[contains(text(),'Enviar mensagem')]";
	}

	interface Mensagens {
		String TXT_MSG = "//textarea[@placeholder='Mensagem...']";
		String BTN_ENVIAR = "//button[contains(text(),'Enviar')]";

		public static String msgListBox(String texto) {
			String MSG_LISTBOX = "(//div[@role='listbox'])[1]//*[contains(text(),'" + texto + "')]";
			return MSG_LISTBOX;
		}
	}

}
