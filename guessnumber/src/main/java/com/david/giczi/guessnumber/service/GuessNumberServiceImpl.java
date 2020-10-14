package com.david.giczi.guessnumber.service;

import org.springframework.stereotype.Service;

@Service
public class GuessNumberServiceImpl implements GuessNumberService {

	@Override
	public int randNumber() {
		return (int) (Math.random() * 100 + 1);
	}

	@Override
	public String evaluate(int inputNumber, int taskNumber) {
		
		if(inputNumber < taskNumber) {
			return "Nagyobb a szám.";
		}
			
		return "Kissebb a szám.";
	}

	@Override
	public boolean validateInputData(String inputData) {
		
		try {
			Integer.parseInt(inputData);
		}
		catch (NumberFormatException e) {
			
			return false;
		}
			
		return true;
	}

	@Override
	public String collectTippNumbers(String inputStore, String tipp) {
		
		return inputStore.length() == 2 ? inputStore.substring(0, inputStore.length() - 1) + tipp + "]"
				: inputStore.substring(0, inputStore.length() - 1) + "," + tipp + "]";
	}

	@Override
	public boolean isTheEndOfTheGame(int inputNumber, int taskNumber) {
		
		return inputNumber == taskNumber;
	}

}
