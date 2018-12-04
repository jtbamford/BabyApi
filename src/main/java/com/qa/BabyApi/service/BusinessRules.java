package com.qa.BabyApi.service;

public class BusinessRules {
	
	public static Boolean isWordAllowed(String word) {		
		if( word.toLowerCase().equals("batman") || (word.toLowerCase().equals("nutella")) || (word.toLowerCase().equals("rambo")) ) {
			return false;
		} else {
			return true;
		}		
	}
	
	public static Boolean isWordLengthAllowed(int length, String word) {
		if(word==null) {
			if(length>12) {
				return false;
			} else {
				return true;
			}	
		} else {
		if(word.length()+length>12) {
			return false;
		} else {
			return true;
		}	
		}
	}

}
