package com.otheroperations;

import javax.servlet.http.Cookie;

public class FindCookiePosition {

	public int getCookiePosition(String name, Cookie[] cook) {
		
		if(cook == null) {
			return 20;
		}
		for(int i = 0;i < cook.length;i++) {
			if(cook[i].getName().equals(name)) {
				return i;
			}
		}
		return 20;
	}
}
