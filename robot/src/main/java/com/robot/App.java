package com.robot;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.awt.MouseInfo.getPointerInfo;

/**
 * Hello world!
 *
 */
public class App
{
	private static Robot robot;
	private static boolean IS_CHROME = true;
	private static String MUSHROOM_FOREST = "Mushroom Forest";
	private static String GRASSLAND = "Grassland";
	private static String ACTIVE_VOLCANO = "Active Volcano";
	private static String GEOTHERMAL_SPRINGS = "Geothermal Springs";
	private static String PLAINS = "Plains";

    public static void main( String[] args ) throws InterruptedException, AWTException
    {
    	boolean DEBUG = false;

    	robot = new Robot();

    	//clickOnRefresh();

    	//clickOnCaptcha();
    	//clickOnApproval();

    	//wait(1200000,1220000, "Wait initial");
    	//wait(960000,980000, "Wait initial");
    	//wait(600000,620000, "Wait initial");
    	//wait(360000,380000, "Wait initial");
    	//wait(300000,320000, "Wait initial");

    	int rounds = 80;
    	int round = 1;

    	//String land = MUSHROOM_FOREST;
//    	String land = "Grassland";
//    	String land = "Active Volcano";
//    	String land = GEOTHERMAL_SPRINGS;
		String land = PLAINS;

    	//clickOnLogin();
    	while (round <= rounds && !DEBUG) {
			if (round < -10) {
				getMousePosition();
				Thread.sleep(3000);
				continue;
			}

    		System.out.println("Start of mining round " + round + "\n");
        	waitToMine(3600000);
    		clickOnMine();
    		waitToClaim(land);
        	clickOnClaim();
        	waitToApproval();
			clickOnApproval();
        	// waitToRefresh();
        	// clickOnRefresh();

        	System.out.println("End of mining round " + round + "\n\n");

    		round++;
    	}

		System.out.println("Paste download path and confirmed.");
    }

	public static void getMousePosition() throws InterruptedException {
		TimeUnit.SECONDS.sleep(1/2);
		double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
		double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
		System.out.println("X:" + mouseX);
		System.out.println("Y:" + mouseY);
	}

    public static void moveMouse(int sx, int sy, int ex, int ey, int speed) throws AWTException {
        int a = 10;
        boolean flag = true;
        for (int i = 0; i < 100; i++) {
            int mov_x = ((ex * i) / 100) + (sx * (100 - i) / 100);
            int mov_y = ((ey * i) / 100) + (sy * (100 - i) / 100);
            if (flag == true) {
                robot.mouseMove(mov_x + a, mov_y); // adds 10 to X-axis
                flag = false;
            } else {
                robot.mouseMove(mov_x - 2 * a, mov_y); // subtracts 20 to X-axis
                flag = true;
            }
            robot.delay(speed);
        }
    }

	private static void clickOnCaptcha() throws InterruptedException {
		String message = "clickOnCaptcha";
		if (IS_CHROME) {
			click(-400, 470, message);
		} else {
			click(-1430, 630, message);
		}
		Thread.sleep(3000);
	}

	private static void clickOnApproval() throws InterruptedException {
		String message = "clickOnApproval";
		if (IS_CHROME) {
			click(370, 555, message);
		} else {
			click(-1300, 730, message);
		}
	}

	private static void waitToApproval() throws InterruptedException {
		wait(2000, 2500, "waitToApproval");
		//wait(8000, 12000, "waitToApproval");
	}

	private static void wait(int min, int max, String message) throws InterruptedException {
		int wait =  new Random().ints(min, max).findFirst().getAsInt();
		System.out.println(message + ": " + wait);
		Thread.sleep(wait);
		System.out.println("Last " + message + ": 3000");
		Thread.sleep(3000);
	}

    private static void waitToMine(int delay) throws InterruptedException {
		wait(delay, delay + (delay/10), "waitToMine");
	}

    private static void waitToClaim(String land) throws InterruptedException {
    	String message = "waitToClaim " + land;
    	if (land.equals(ACTIVE_VOLCANO)) {
    		//wait(120000, 140000, "waitToClaim " + land);
    		wait(80000, 90000, message);

    	} else if (land.equals(GRASSLAND)) {
    		wait(60000, 65000, message);

    	} else if (land.equals(GEOTHERMAL_SPRINGS)) {
    		if (IS_CHROME) {
    			wait(50000, 55000, message);
    		} else {
    			wait(60000, 65000, message);
    		}
    	} else if (land.equals(MUSHROOM_FOREST)) {
    		if (IS_CHROME) {
    			wait(50000, 55000, message);
    		} else {
    			wait(60000, 65000, message);
    		}
    	} else if (land.equals(PLAINS)) {
			if (IS_CHROME) {
				wait(5000, 5500, message);
			} else {
				wait(5000, 5500, message);
			}
		} else {
    		wait(20000, 25000, message);
    	}
	}

	private static void waitToMineFromMiningHub() throws InterruptedException {
		wait(500, 1000, "waitToMineFromMiningHub");
	}

	private static void waitToMiningHub() throws InterruptedException {
		wait(6000,8000, "waitToMiningHub");
	}

	private static void waitToMiningDelay(String land) throws InterruptedException, AWTException {
		String message = "waitToMiningDelay " + land;
		if (land.equals("Active Volcano")) {
//			Thread.sleep(600000);
//			clickOnLogin();
//			wait(600000,620000, "waitToMiningDelay " + land);
			wait(1200000,1220000, message);

		} else if (land.equals("Grassland")) {
			wait(120000,140000, message);

		} else if (land.equals(GEOTHERMAL_SPRINGS)) {
    		wait(360000, 380000, message);

    	}  else if (land.equals(MUSHROOM_FOREST)) {
    		wait(540000, 550000, message);

    	} else {
			wait(120000,140000, message);
		}
	}

	private static void click(int x, int y, String message) throws InterruptedException {
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

		System.out.println(message);
		Thread.sleep(2000);
	}

	private static void clickOnLogin() throws AWTException, InterruptedException {
		click(-800, 750, "clickOnLogin");
	}

	private static void clickOnMine() throws InterruptedException {
		click(1114, 169, "clickOnMine");
	}

	private static void clickOnMineFromMiningHub() throws InterruptedException {
		click(-800, 850, "clickOnMineFromMiningHub");
	}

	private static void clickOnClaim() throws InterruptedException {
		click(1114, 169, "clickOnClaim");
	}

	private static void clickOnBack() throws InterruptedException {
		click(-200, 200, "clickOnBack");
	}

	private static void clickOnLogout() throws InterruptedException {
		click(-200, 200, "clickOnLogout");
	}

	private static void waitToRefresh() throws InterruptedException {
		wait(6000,8000, "waitToRefresh");
	}

	private static void clickOnRefresh() throws InterruptedException {
		click(-1520, 120, "clickOnRefresh");
		Thread.sleep(10000);
	}
}