/**
 *
 */
package pl.inferno.security.form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class SidePanel
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 *
 */
public class SidePanel {

	public enum Panel {
		USER1,
		USER2,
		ADMIN1,
		ADMIN2
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(SidePanel.class);

	private int id;

	private String title;

	private Panel content;

	private boolean visible;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the content
	 */
	public Panel getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(Panel content) {
		this.content = content;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((content == null) ? 0 : content.hashCode());
		result = (prime * result) + id;
		result = (prime * result) + ((title == null) ? 0 : title.hashCode());
		result = (prime * result) + (visible ? 1231 : 1237);
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SidePanel)) {
			return false;
		}
		SidePanel other = (SidePanel) obj;
		if (content != other.content) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (visible != other.visible) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SidePanel [id=").append(id).append(", ");
		if (title != null) {
			builder.append("title=").append(title).append(", ");
		}
		if (content != null) {
			builder.append("content=").append(content).append(", ");
		}
		builder.append("visible=").append(visible).append("]");
		return builder.toString();
	}

}
