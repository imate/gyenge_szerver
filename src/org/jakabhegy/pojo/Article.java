package org.jakabhegy.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.jakabhegy.tools.Tools;

@Entity
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private String title;
	@Column(length = 2000)
	private String text;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private Account author;
	@OneToMany(mappedBy = "article")
	private List<Message> messages;
	private Date date;

	// private DateFormat dateFormat = new
	// SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

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
		if (!author.getArticles().contains(this)) {
			author.addArticle(this);
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getArticleLink() {
		return "ShowArticle?id=" + getId();
	}

	public List<Message> getMessages() {
		return new ArrayList<>(this.messages);
	}

	public void addMessage(Message message) {
		this.messages.add(message);
		if (message.getArticle() != this) {
			message.setArticle(this);
		}
	}

	public String getMessageCount() {
		return this.messages.size() + " hozzászólás";
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", text=" + text
				+ ", creator=" + getAuthorName() + ", date="
				+ Tools.getFormattedDate(date) + "]";
	}

	public void deleteAuthor() {
		this.author.removeArticle(this);
		this.author=null;
}

}
