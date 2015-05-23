package org.jakabhegy.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String text;
	private Account author;
	private Date date;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Account getAuthor() {
		return author;
	}

	public String getAuthorName() {
		return getAuthor().getUsername();
	}

	public void setAuthor(Account author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFormattedDate() {
		return dateFormat.format(date);
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", text=" + text
				+ ", creator=" + getAuthorName() + ", date="
				+ dateFormat.format(date) + "]";
	}

}