package yo.hoo.wiki.dao;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

class SectionTemp {
	//@JsonIgnore
	private String id;
	private String dna;
	private List<SectionTemp> children = new ArrayList<SectionTemp>();
	
	public void addChild(SectionTemp s){
		children.add(s);
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDna() {
		return dna;
	}
	
	public void setDna(String dna) {
		this.dna = dna;
	}
	
	public List<SectionTemp> getChildren() {
		return children;
	}
	
	public void setChildren(List<SectionTemp> children) {
		this.children = children;
	}
}