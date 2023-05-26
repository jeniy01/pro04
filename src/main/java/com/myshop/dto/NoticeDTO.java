package com.myshop.dto;

import lombok.Data;

@Data
public class NoticeDTO {
	private int no;
	private String title;
	private String content;
	private String author;
	private String resdate;
	private int visited;
}
