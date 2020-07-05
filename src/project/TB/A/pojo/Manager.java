package project.TB.A.pojo;
/**
 * 
 * @author ЙЋзг
 *
 */
public class Manager {
	private String mNumber;
	private String managername;
	private String password;
	public String getmNumber() {
		return mNumber;
	}
	public void setmNumber(String mNumber) {
		this.mNumber = mNumber;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Manager [mNumber=" + mNumber + ", managername=" + managername + ", password=" + password + "]";
	}
	/**
	 * 
	 * @param mNumber
	 * @param managername
	 * @param password
	 */
	public Manager(String mNumber, String managername, String password) {
		super();
		this.mNumber = mNumber;
		this.managername = managername;
		this.password = password;
	}
	
}
