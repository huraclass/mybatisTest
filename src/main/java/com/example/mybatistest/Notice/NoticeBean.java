package com.example.mybatistest.Notice;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NoticeBean {

	private int ID;//auto_increment
	private int SubjectID;
	private String Name;
	private String Content;
	private String SubjectName;

}

