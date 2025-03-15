package Generic;

import java.util.Random;

public class generaterandom {

	public static int getRandomNumber() {

		Random ran = new Random();
		int num = ran.nextInt();

		return num;
	}

}
