package dto;

public class BoardDTO {
	private int bno;
	private int mno;
	private int bcount;
	private int bhate;
	private int blike;
	private int ccount;
	private String title;
	private String content;
	private String nickName;
	private String writeDate;
	private String updateDate;

	public BoardDTO() {	}

	public BoardDTO(int bno, int mno, int bcount, int bhate, int blike, int ccount, String title, String content,
			String nickName, String writeDate, String updateDate) {
		super();
		this.bno = bno;
		this.mno = mno;
		this.bcount = bcount;
		this.bhate = bhate;
		this.blike = blike;
		this.ccount = ccount;
		this.title = title;
		this.content = content;
		this.nickName = nickName;
		this.writeDate = writeDate;
		this.updateDate = updateDate;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getBcount() {
		return bcount;
	}

	public void setBcount(int bcount) {
		this.bcount = bcount;
	}

	public int getBhate() {
		return bhate;
	}

	public void setBhate(int bhate) {
		this.bhate = bhate;
	}

	public int getBlike() {
		return blike;
	}

	public void setBlike(int blike) {
		this.blike = blike;
	}

	public int getCcount() {
		return ccount;
	}

	public void setCcount(int ccount) {
		this.ccount = ccount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", mno=" + mno + ", bcount=" + bcount + ", bhate=" + bhate + ", blike=" + blike
				+ ", ccount=" + ccount + ", title=" + title + ", content=" + content + ", nickName=" + nickName
				+ ", writeDate=" + writeDate + ", updateDate=" + updateDate + "]";
	}
	
	
	
	
}




