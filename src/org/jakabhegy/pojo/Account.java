package org.jakabhegy.pojo;

import java.io.File;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private Date regDate;
	private String imgName;
	private String email;
	private boolean ellenorzott;
	private String checkText;

	public Account() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getImgName() {
		return imgName;
	}

	public String getImgPath() {
		if (imgName == null) {
			return "img/user.jpg";
		} else {
			return "img/" + "profile_" + getId() + ".jpg";
		}
	}

	public String getCheckText() {
		return checkText;
	}

	public void setCheckText(String checkText) {
		this.checkText = checkText;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEllenorzott() {
		return ellenorzott;
	}

	public void setEllenorzott(boolean ellenorzott) {
		this.ellenorzott = ellenorzott;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password="
				+ password + ", regDate=" + regDate + "]";
	}

}
