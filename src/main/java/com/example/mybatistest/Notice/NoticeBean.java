package com.example.mybatistest.Notice;

public class NoticeBean {

	private int ID;
	private int SubjectID;
	private String Name;
	private String Content;
	private String SubjectName;
	
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getSubjectID() {
		return SubjectID;
	}
	public void setSubjectID(int SubjectID) {
		this.SubjectID = SubjectID;
	}

	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}

	public String getContent() {
		return Content;
	}
	public void setContent(String Content) {
		this.Content = Content;
	}

	public String getSubjectName() {
		return SubjectName;
	}
	public void setSubjectName(String SubjectName) {
		this.SubjectName = SubjectName;
	}
}

