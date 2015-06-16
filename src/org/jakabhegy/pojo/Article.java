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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((messages == null) ? 0 : messages.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	

}
