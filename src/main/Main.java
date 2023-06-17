package main;

import java.io.IOException;

import ui.UI;

public class Main {

	public static void main(String[] args) {
		UI ui = new UI();
		
		try {
			ui.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
