package com.gz.post.model.vo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy {
	
	@Override
	public File rename(File origin) {
		
		String originName = origin.getName();
		
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		int random = (int)(Math.random()*90000) + 10000;
		
		String ext = originName.substring(originName.lastIndexOf("."));
		
		String changeName = time + random + ext;
		
		System.out.println(origin.getParent());
		return new File(origin.getParent(), changeName);
	}

}
