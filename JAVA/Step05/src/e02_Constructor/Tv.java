package e02_Constructor;
/**
 * TV 클래스
 * 1.필드
 * 	 채널(1~480), 볼륨(0~50), 전원(T,F), 음소거(T,F)
 * 2.메서드
 * 	 채널 업/다운, 볼륨 업/다운, 전원OnOff, 음소거 OnOff
 */
public class Tv {
//1. 필드
	private int ch;
	private int vol;
	public boolean power;
	public boolean mute;
	
	private final int MAX_CHANNEL_VALUE = 480;
	private final int MIN_CHANNEL_VALUE = 1;
	
	private final int Max_VOL_VALUE= 50;
	private final int MIN_VOL_VALUE= 0;
//2. 기본 생성자(원하시는 값으로 초기화)
	public Tv() {
		ch = 24;
		vol = 15;
		power= mute = false;
	}
//3. 전원 on off 
	public void	powerOnOff(){
		// poweronoff를 실행 할때마다 전원값이 바껴야함
		power =!power;
		//전원이 true이면 TV전원이 켜졌습니다.
		if(power)System.out.println("TV전원이 꺼졌습니다.");
		//전원이 false면 TV전원이 꺼졌습니다.
		else System.out.println("TV전원이 꺼졌습니다.");
	}
	//음소거
	public void muteOnOff(){
		mute =!mute;
		System.out.println(mute ? "음소거 활성화 되었습니다.":"음소거 비활성화 되었습니다.");	
	}
	//채널 업
	// 실행 할떄마다 채널 값이 1씩 증가
	// 마지막 채널에서는 첫번째 채널로 이동
	public void ChUP() {
//		ch++;
//		if(ch> 480) ch= 1;
//		System.out.println("현재채널 : "+ ch);
		
		if(!power)return;
		ch= ch % MAX_CHANNEL_VALUE + 1;
		System.out.println("현재채널 : "+ ch);
	}
	
//채널DOWN
//실행할 때 마다 채널 값이 1씩감소
//첫번째 채널에서는 마지막 채널로 이동
	public void ChDown() {
		if(!power)return;
		ch--;
		if(ch < MIN_CHANNEL_VALUE) ch= MAX_CHANNEL_VALUE;
		System.out.println("현재채널 : "+ ch);
		}
//음량 업
	public void volUp() {
		if(!power)return;
		if(mute) muteOnOff();
		if(vol<Max_VOL_VALUE)
			vol++;
		System.out.println("현재 음량 :"+ vol);
//		vol++;
//		if(vol>Max_VOL_VALUE) vol=Max_VOL_VALUE;
//		System.out.println("현재 음량 : "+ vol);
		}
//음량 다운
	public void volDown() {
		if(!power)return;
		if(mute) muteOnOff();
		if(vol>MIN_VOL_VALUE)
			vol--;
		System.out.println("현재 음량 :"+ vol);
		
//		vol--;
//		if(vol< MIN_VOL_VALUE) vol=MIN_VOL_VALUE;
//		System.out.println("현재 음량 : "+ vol);
	}
}


