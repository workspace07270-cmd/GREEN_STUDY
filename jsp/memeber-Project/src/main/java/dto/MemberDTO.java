package dto;

public class MemberDTO {
	private int no;
	private String id;
	private String passwd;
	private String userName;
	private String nickName;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public MemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(int no, String id, String passwd, String userName, String nickName) {
		super();
		this.no = no;
		this.id = id;
		this.passwd = passwd;
		this.userName = userName;
		this.nickName = nickName;
	}

	@Override
	public String toString() {
		return no + "," + id + "," + passwd + "," + userName + "," + nickName ;
	}
	
	
	
	
}