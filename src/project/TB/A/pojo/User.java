package project.TB.A.pojo;
/**
 * 
 * @author ЙЋзг
 *
 */
public class User {
	private String uNumber;
	private String password;
	private String userName;
	private String mylike;
	private String notice;
	private String ID;
	
	public String getMylike() {
		return mylike;
	}
	public void setMylike(String mylike) {
		this.mylike = mylike;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getuNumber() {
		return uNumber;
	}
	public void setuNumber(String uNumber) {
		this.uNumber = uNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "User [uNumber=" + uNumber + ", password=" + password + ", userName=" + userName + ", mylike=" + mylike
				+ ", notice=" + notice + ", ID=" + ID + "]";
	}
	/**
	 * 
	 * @param uNumber
	 * @param password
	 * @param userName
	 * @param mylike
	 * @param notice
	 * @param iD
	 */
	public User(String uNumber, String password, String userName, String mylike, String notice, String iD) {
		super();
		this.uNumber = uNumber;
		this.password = password;
		this.userName = userName;
		this.mylike = mylike;
		this.notice = notice;
		ID = iD;
	}
	
}
