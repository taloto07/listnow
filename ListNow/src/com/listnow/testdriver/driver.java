package com.listnow.testdriver;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class driver {
	public static void main(String[] args){
		final String hash = Hashing.sha256().hashString("test", Charsets.UTF_8).toString();
		System.out.println(hash);
	}
}
