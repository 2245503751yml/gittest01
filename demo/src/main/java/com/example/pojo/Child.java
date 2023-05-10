package com.example.pojo;

import java.util.Date;

public class Child {
    private Integer id;

    private String surname;

    private String name;

    private String schoolName;

    private String grade;

    private Integer age;

    private String sex;

    private String recognitionid;

    private Date birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname == null ? null : surname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getRecognitionid() {
        return recognitionid;
    }

    public void setRecognitionid(String recognitionid) {
        this.recognitionid = recognitionid == null ? null : recognitionid.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

	@Override
	public String toString() {
		return "Child [id=" + id + ", surname=" + surname + ", name=" + name + ", schoolName=" + schoolName + ", grade="
				+ grade + ", age=" + age + ", sex=" + sex + ", recognitionid=" + recognitionid + ", birthday="
				+ birthday + "]";
	}
    
}