package main.java.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import main.java.model.Login;
import main.java.task.AutomacaoInstragramTask;

public class TelaSolicitacaoSeguirController {

	private Login login;

	@FXML
	public Label lblLog;
	@FXML
	public TextField txtPalavra;
	@FXML
	public Button btnAdicionar;
	@FXML
	public Pane paneLista;
	@FXML
	public ListView<String> listaPalavras;
	@FXML
	public TextArea txtAreaMensagem;

	private AutomacaoInstragramTask instagramTask;

	@FXML
	public void initialize() {
		txtPalavra.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					adicionarNaLista();
				}
			}
		});
		txtAreaMensagem.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					iniciarAutomacao();
				}
			}
		});
	}

	@FXML
	public void adicionarNaLista() {
		listaPalavras.getItems().add(txtPalavra.getText());
	}

	@FXML
	public void iniciarAutomacao() {
		instagramTask = new AutomacaoInstragramTask(login, listaPalavras, txtAreaMensagem);
		lblLog.textProperty().bind(instagramTask.messageProperty());
		new Thread(instagramTask).start();
	}

	@FXML
	public void removerDaLista() {
		int index = listaPalavras.getSelectionModel().getSelectedIndex();
		listaPalavras.getItems().remove(index);
	}

	@FXML
	public void configurarTxtReinicio() {
	}

	@FXML
	public void pararAutomacao() {
		instagramTask.cancel();
		instagramTask.getAtributos().getDriver().close();
	}

	public void insereLogin(Login login) {
		this.login = login;
	}
}
