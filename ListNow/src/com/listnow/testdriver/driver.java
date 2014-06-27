package com.listnow.testdriver;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.listnow.hosting.dao.Image;


public class driver {
	public static void main(String[] args){
		final String hash = Hashing.sha256().hashString("test", Charsets.UTF_8).toString();
		System.out.println(hash);
		
//		int n = Integer.parseInt(null);
//		if (n == 0){
//			System.out.println("0");
//		}
		
		STGroup templates = new STGroupDir("",'$','$');
		ST st = templates.getInstanceOf("st");
		String[] text = {"first","second"};
		st.add("text", text);
		
		System.out.println(st.render());
		
		Image image = new Image();
		image.setId(0);
		image.setName("test.jpg");
		image.setPath("image_post");
		image.setSize(10);
		image.setUploadDate(new Date());
		
		Map<String, Image> myImage = new HashMap<String, Image>();
		
		myImage.put("image", image);
		
		Gson gson = new Gson();
		
		System.out.println(gson.toJson(myImage));
	}
}
