import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class TimerConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the timer input (e.g., xy'ab\"cd): ");
        String input = scanner.nextLine();

        String[] values = input.split("");
        BufferedImage finalImage = null;

        for (String value : values) {
            String filename = getFileNameForValue(value);
            if (filename != null) {
                try {
                    BufferedImage image = ImageIO.read(new File(filename));
                    if (finalImage == null) {
                        finalImage = image;
                    } else {
                        finalImage = spliceImagesWithWhiteSpace(finalImage, image);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading image file: " + filename);
                }
            }
        }

        if (finalImage != null) {
            try {
                ImageIO.write(finalImage, "png", new File("output.png"));
                System.out.println("Final image saved as 'output.png'");
            } catch (IOException e) {
                System.err.println("Error saving final image");
            }
        } else {
            System.out.println("No valid image files found for the input.");
        }

        scanner.close();
    }

    private static String getFileNameForValue(String value) {
        switch (value) {
            case "0":
                return "zero.png";
            case "1":
                return "one.png";
            case "2":
                return "two.png";
            case "3":
                return "three.png";
            case "4":
                return "four.png";
            case "5":
                return "five.png";
            case "6":
                return "six.png";
            case "7":
                return "seven.png";
            case "8":
                return "eight.png";
            case "9":
                return "nine.png";
            case "\"":
                return "double.png";
            case "'":
                return "single.png";
            default:
                return null;
        }
    }

    private static BufferedImage spliceImagesWithWhiteSpace(BufferedImage image1, BufferedImage image2) {
        int spaceWidth = 7;  // Two pixels of space
        int width = image1.getWidth() + spaceWidth + image2.getWidth();
        int height = Math.max(image1.getHeight(), image2.getHeight());

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Create a white image for the space
        BufferedImage spaceImage = new BufferedImage(spaceWidth, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = spaceImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, spaceWidth, height);
        graphics.dispose();

        result.getGraphics().drawImage(image1, 0, 0, null);
        result.getGraphics().drawImage(spaceImage, image1.getWidth(), 0, null);
        result.getGraphics().drawImage(image2, image1.getWidth() + spaceWidth, 0, null);

        return result;
    }



}
