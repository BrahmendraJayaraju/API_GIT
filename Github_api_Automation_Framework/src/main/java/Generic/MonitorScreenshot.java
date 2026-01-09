package Generic;
import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class MonitorScreenshot {

    public static void main(String[] args) {
        try {
            // Get the graphics environment and screens (monitors)
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] screens = ge.getScreenDevices();

            // Assuming Monitor 1 = screens[0] (external monitor is usually 1, but may vary)
            GraphicsDevice monitor1 = screens[0];

            // Get bounds of Monitor 1
            Rectangle bounds = monitor1.getDefaultConfiguration().getBounds();

            // Take screenshot using Robot
            Robot robot = new Robot(monitor1);
            BufferedImage screenshot = robot.createScreenCapture(bounds);

            // Get desktop path
            String desktopPath = System.getProperty("user.home") + "/Desktop/";
            String filePath = desktopPath + "monitor1_screenshot.png";

            // Save image
            ImageIO.write(screenshot, "png", new File(filePath));
            System.out.println("Screenshot saved to: " + filePath);

        } catch (AWTException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
