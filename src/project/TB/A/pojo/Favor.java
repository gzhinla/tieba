package project.TB.A.pojo;
/**
 * 
 * @author ЙЋзг
 *
 */
public class Favor {
	private String postcontent;
	private String topic;
	private String username;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "Favor [postcontent=" + postcontent + ", topic=" + topic + ", username=" + username + "]";
	}
	/**
	 * 
	 * @param postcontent
	 * @param topic
	 * @param username
	 */
	public Favor(String postcontent, String topic, String username) {
		super();
		this.postcontent = postcontent;
		this.topic = topic;
		this.username = username;
	}
	

}
