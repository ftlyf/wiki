package yo.hoo.wiki.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "u_section")
public class Section {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="mediumint(11)")
	private int secid;
	@Column(columnDefinition="varchar(150)")
	private String dna;
	@Column(columnDefinition="varchar(100)",nullable=false)
	private String title;
	@Column(columnDefinition="mediumtext",nullable=false)
	private String content;
	@Column(columnDefinition="int(10)",nullable=false)
	private Date time;

	public int getSecid() {
		return secid;
	}

	public void setSecid(int secid) {
		this.secid = secid;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
