import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

class Section {
	@JsonIgnore
	private String name;
	private String content;
	private List<Section> children = new ArrayList<Section>();

	public String getName() {
		return name;
	}

	public void addChild(Section section) {
		children.add(section);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Section> getChildren() {
		return children;
	}

	public void setChildren(List<Section> children) {
		this.children = children;
	}
}