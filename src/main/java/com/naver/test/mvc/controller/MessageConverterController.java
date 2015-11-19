package com.naver.test.mvc.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.naver.test.mvc.model.Item;
import com.naver.test.mvc.model.ItemXml;

@RestController
@RequestMapping("mvc")
public class MessageConverterController {
	@RequestMapping(value = "/item/json/{id}", method = { RequestMethod.GET })
	public Item getItemJson(@PathVariable Integer id) {
		Item item = new Item();
		item.setId(id);
		item.setTitle("테스트 아이템");
		item.setContent("스프링 테스트");

		return item;
	};

	@RequestMapping(value = "/item/xml/{id}", method = { RequestMethod.GET })
	public ItemXml getItemXml(@PathVariable Integer id) {
		ItemXml item = new ItemXml();
		item.setId(id);
		item.setTitle("테스트 아이템");
		item.setContent("스프링 테스트");

		return item;
	};

	@RequestMapping(value = "/photo", headers = "Accept=image/jpeg, image/jpg, image/png, image/gif", method = RequestMethod.GET)
	public byte[] testphoto() throws Exception {
		Path path = Paths.get("D:/picture/MyGirl_20140605_213417.png");

		byte[] imageBytes = Files.readAllBytes(path);

		return imageBytes;

	}
}
