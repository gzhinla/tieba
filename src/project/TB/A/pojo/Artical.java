package project.TB.A.pojo;
/**
 * 
 * @author ЙЋзг
 *
 */
public class Artical {
	private String id;
	private String username;
	private String postcontent;
	private String topic;
	public String getUsername() {
		return username;
	}
	/**
	 * 
	 * @param id
	 * @param username
	 * @param postcontent
	 * @param topic
	 */
	public Artical(String id, String username, String postcontent, String topic) {
		super();
		this.id = id;
		this.username = username;
		this.postcontent = postcontent;
		this.topic = topic;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPostcontent() {
		return postcontent;
	}
	public void setPostcontent(String postcontent) {
		this.postcontent = postcontent;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	@Override
	public String toString() {
		return "Artical [id=" + id + ", username=" + username + ", postcontent=" + postcontent + ", topic=" + topic
				+ "]";
	}

	
}
