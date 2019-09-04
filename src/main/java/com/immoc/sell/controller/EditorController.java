package com.immoc.sell.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//跨域请求
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/editor")
public class EditorController {
	/**
	 * 处理html富文本
	 * 
	 */
	@GetMapping("/submit")
	public void doSubmit(@RequestParam("content") String content) {
		System.out.println(content);
	}

	/**
	 * 上传富文本文件
	 * 
	 */

	@PostMapping("/upload")
	public Object upload(@RequestParam("file") MultipartFile file) {
		String fileName = "";
		if (!file.isEmpty()) {
			if (file.getSize() > 1024 * 1024 * 5) {
				System.out.println("文件太大了 超过5M");
			}
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
			if (StringUtils.isBlank(suffix)) {
				System.out.println("上传文件没有后缀，无法识别");
			}

			// 文件名
			fileName = System.currentTimeMillis() + suffix;
			// 上传的文件完整路径 包含文件名 D:/upload/ 在配置文件中配置  一般不要放在项目文件夹下
			String saveFileName = "F:/springboot_sell/src/main/resources/static" + "/article/" + fileName;
			// 创建文件对象
			File dest = new File(saveFileName);
			if (!dest.getParentFile().exists()) { // 判断文件父目录是否存在
				dest.getParentFile().mkdir();
			}
			try {
				file.transferTo(dest); // 保存文件
			} catch (Exception e) {
				e.printStackTrace();
				// return 返回错误信息
				System.out.println("上传失败" + e.getMessage());
				return new WangEditorResponse("1", "上传失败" + e.getMessage());
			}
		} else {
			// return 返回上传错误
			System.out.println("上传出错");
			return new WangEditorResponse("1", "上传出错");
		}
		
		// 上传成功后返回 此处应该返回url路径  例如 http://localhost:8080/article/1567494335032.png
        String imgUrl= "http://localhost:8080" + "/article/" +  fileName; // 坑 一定要加 http://
        System.out.println(imgUrl);
        // 返回后 图片在前端不能回显
        return new WangEditorResponse("0", imgUrl );
	}

	public class WangEditorResponse {
		public String getErrno() {
			return errno;
		}

		public void setErrno(String errno) {
			this.errno = errno;
		}

		public List<String> getData() {
			return data;
		}

		public void setData(List<String> data) {
			this.data = data;
		}

		String errno;
		List<String> data;

		public WangEditorResponse(String errno, List<String> data) {
			this.errno = errno;
			this.data = data;
		}

		public WangEditorResponse(String errno, String data) {
			this.errno = errno;
			this.data = new ArrayList<>();
			this.data.add(data);
		}
	}
}
