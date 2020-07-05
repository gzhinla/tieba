package project.TB.A.pojo;
/**
 * 
 * @author ЙЋзг
 *
 */
public class Commentt {
	private String comcontent;
	private String username;
	private String topic;
	public String getComcontent() {
		return comcontent;
	}
	public void setComcontent(String comcontent) {
		this.comcontent = comcontent;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	@Override
	public String toString() {
		return "Commentt [comcontent=" + comcontent + ", username=" + username + ", topic=" + topic + "]";
	}
	/**
	 * 
	 * @param comcontent
	 * @param username
	 * @param topic
	 */
	public Commentt(String comcontent, String username, String topic) {
		super();
		this.comcontent = comcontent;
		this.username = username;
		this.topic = topic;
	}
	
	
	
}