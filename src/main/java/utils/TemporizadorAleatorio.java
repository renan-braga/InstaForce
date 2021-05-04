package main.java.utils;

import java.util.Random;

public class TemporizadorAleatorio {

	public static int entre(int inicio, int fim) {
		int tempo = new Random().nextInt(fim - inicio) + inicio;
		return tempo;
	}
}
