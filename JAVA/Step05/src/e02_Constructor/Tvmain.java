package e02_Constructor;

public class Tvmain {
	public static void main(String[] args){
		Tv tv = new Tv();
		tv.powerOnOff();
		for(int i = 0;i<500;i++)
			tv.ChUP();
		for(int i = 0;i<500;i++)
			tv.ChDown();
		for(int i = 0;i<50;i++)
			tv.volUp();
		tv.muteOnOff();
		for(int i = 0;i<60;i++)
			tv.volDown();
	}
}
