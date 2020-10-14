package com.david.giczi.guessnumber.service;

public interface GuessNumberService {

	
	int randNumber();
	boolean validateInputData(String inputData);
	String evaluate(int inputNumber, int taskNumber);
	String collectTippNumbers(String inputStore, String tipp);
	boolean isTheEndOfTheGame(int inputNumber, int taskNumber);
	
}
