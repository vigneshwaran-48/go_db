package com.otheroperations;

public class CookieOperations {

	//Characters that are not allowed in cookies [ ] ( ) = , " / ? @ : ;
	public String encodeCookie(String cookieValue) {
		
		cookieValue  = cookieValue.replaceAll("\r", " ").replaceAll("\n", " ");
		
		String tempCode = cookieValue.replaceAll(" ", "hereSpace");
		tempCode = tempCode.replaceAll("\\[", "hereOpenSquare");
		tempCode = tempCode.replaceAll("]", "hereCloseSquare");
		tempCode = tempCode.replaceAll("\\(", "hereOpenBracket");
		tempCode = tempCode.replaceAll("\\)", "hereCloseBracket");
		tempCode = tempCode.replaceAll("=", "hereEqual");
		tempCode = tempCode.replaceAll("\"", "hereQuotes");
		tempCode = tempCode.replaceAll("/", "hereDivide");
		tempCode = tempCode.replaceAll("\\?", "hereQuestion");
		tempCode = tempCode.replaceAll("@", "hereAt");
		tempCode = tempCode.replaceAll(":", "hereColon");
		tempCode = tempCode.replaceAll(";", "hereSemiColon");
		tempCode = tempCode.replaceAll(",", "hereComma");
		
		
		return tempCode;
	}
	public String decodeCookie(String cookieValue) {
		
		cookieValue = cookieValue.replaceAll("hereSpace", " ");
		cookieValue = cookieValue.replaceAll("hereSemiColon", ";");
		cookieValue = cookieValue.replaceAll("hereComma", ",");
		cookieValue = cookieValue.replaceAll("hereOpenSquare", "[");
		cookieValue = cookieValue.replaceAll("hereCloseSquare", "]");
		cookieValue = cookieValue.replaceAll("hereOpenBracket", "(");
		cookieValue = cookieValue.replaceAll("hereCloseBracket", ")");
		cookieValue = cookieValue.replaceAll("hereEqual", ")");
		cookieValue = cookieValue.replaceAll("hereQuotes", "\"");
		cookieValue = cookieValue.replaceAll("hereDivide", "/");
		cookieValue = cookieValue.replaceAll("hereQuestion", "?");
		cookieValue = cookieValue.replaceAll("hereAt", "@");
		cookieValue = cookieValue.replaceAll("hereColon", ":");
		return cookieValue;
	}
}
