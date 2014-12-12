package yo.hoo.wiki.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="u_title")
public class Title {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int did;
	private int mappingid;
	private int cid;
	private String dna;
	private String title;
	private String summary;
	private int authorid;
	private String authorized_ids;
	@Column(columnDefinition="int(10)",nullable=false)
	private Date time;
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public int getMappingid() {
		return mappingid;
	}
	public void setMappingid(int mappingid) {
		this.mappingid = mappingid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	public String getAuthorized_ids() {
		return authorized_ids;
	}
	public void setAuthorized_ids(String authorized_ids) {
		this.authorized_ids = authorized_ids;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}

}
