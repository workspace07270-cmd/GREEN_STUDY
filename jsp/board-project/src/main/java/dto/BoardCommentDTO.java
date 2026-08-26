package dto;

public class BoardCommentDTO {
	private int cno;
	private String content;
	private String cdate;
	private int mno;
	private int bno;
	private int clike;
	private int chate;
	private String nickName;
	
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getClike() {
		return clike;
	}
	public void setClike(int clike) {
		this.clike = clike;
	}
	public int getChate() {
		return chate;
	}
	public void setChate(int chate) {
		this.chate = chate;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "BoardCommentDTO [cno=" + cno + ", content=" + content + ", cdate=" + cdate + ", mno=" + mno + ", bno="
				+ bno + ", clike=" + clike + ", chate=" + chate + ", nickName=" + nickName + "]";
	}
	
	
	
}