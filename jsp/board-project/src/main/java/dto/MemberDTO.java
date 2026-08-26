package dto;

/**
 * 회원 정보를 담는 데이터 전달 객체(Data Transfer Object)입니다.
 * DB의 board_member 테이블과 대응됩니다.
 */
public class MemberDTO {
	private int no;          // 회원 고유 번호 (자동 증가)
	private String id;       // 로그인 아이디
	private String passwd;   // 비밀번호 (암호화되어 저장됨)
	private String userName; // 실제 이름
	private String nickName; // 서비스에서 사용할 닉네임

	// --- Getter / Setter: private 필드에 접근하기 위한 메서드들 ---
	
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

	// 기본 생성자
	public MemberDTO() {
		super();
	}

	// 모든 필드를 초기화하는 생성자
	public MemberDTO(int no, String id, String passwd, String userName, String nickName) {
		super();
		this.no = no;
		this.id = id;
		this.passwd = passwd;
		this.userName = userName;
		this.nickName = nickName;
	}

	// 객체의 정보를 문자열로 반환 (디버깅용)
	@Override
	public String toString() {
		return no + "," + id + "," + passwd + "," + userName + "," + nickName ;
	}
}