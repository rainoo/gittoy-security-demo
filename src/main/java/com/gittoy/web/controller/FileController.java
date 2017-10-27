package com.gittoy.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gittoy.dto.FileInfo;

/**
 * FileController.java
 *
 * @author GaoYu 2017年10月25日 上午11:10:16
 */
@RestController
@RequestMapping("file")
public class FileController {

	String folder = "C:\\Users\\Administrator\\Documents\\workspace-sts-3.9.1.RELEASE\\gittoy-security-demo\\src\\main\\java\\com\\gittoy\\web\\controller";

	@PostMapping
	public FileInfo upload(MultipartFile file) throws Exception {

		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());

		File localFile = new File(folder, new Date().getTime() + ".txt");

		// 把传上来的文件写到本地的文件
		file.transferTo(localFile);

		return new FileInfo(localFile.getAbsolutePath());
	}

	@GetMapping("/{id}")
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// JDK7新语法。不用进行try catch关闭流。
		try (InputStream inputStream = new FileInputStream(new File(folder, id + ".txt"));
			OutputStream outputStream = response.getOutputStream();) {

			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");

			// 将文件的输入流拷贝到输出流。
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		}
	}
}
