package e03_syncronized;

import java.util.ArrayList;

public class SyncronizedMain {

	public static void main(String[] args) {
		ArrayList<Player> list = new ArrayList<Player>();
		while(list.size() < 5) list.add(new Player());
		list.forEach(t -> t.start());
		
	}

}