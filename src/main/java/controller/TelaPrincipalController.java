package main.java.controller;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.java.model.Login;

public class TelaPrincipalController {

	@FXML
	private TextField txtUsuario;

	@FXML
	private TextField txtSenha;

	@FXML
	private Button btnProsseguir;

	@FXML
	public void initialize() {
	}

	@FXML
	public void continuar() {
		try {
			Login login = new Login(txtUsuario.getText(), txtSenha.getText());

			URL url = getClass().getResource("TelaSolicitacaoSeguir.fxml");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(url);
			loader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root = (Parent) loader.load(url.openStream());
			Stage stage = new Stage();
			stage.setScene(new Scene(root, 700, 550));
			stage.show();

			TelaSolicitacaoSeguirController controller = loader.getController();
			controller.insereLogin(login);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
