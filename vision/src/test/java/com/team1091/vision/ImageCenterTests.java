package com.team1091.vision;

import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This is used to find illuminated tape targets
 */
public class ImageCenterTests {

    @Test
    public void testBlackImage() {
        // TODO: Test that the a pure black image does not cause the program to crash
        BufferedImage bi = generateColoredImage(10, 10, Color.BLACK);

        TargetingOutput output = VisionStandalone.process(bi);
        assert output != null;
        assert output.getYellowCenter() == 0;

    }


    @Test
    public void testGreenImage() {
        // TODO: Test that the a pure green image does not cause the program to crash
        BufferedImage bi = generateColoredImage(100, 100, Color.GREEN);

        TargetingOutput output = VisionStandalone.process(bi);
        assert output != null;
        assert Math.abs(output.getYellowCenter()) <= 0.01;
    }


    @Test
    public void testCenter() {
        // TODO: test that a valid image returns a reasonable center
        assert true;
    }


    private BufferedImage generateColoredImage(int xSize, int ySize, Color color) {
        BufferedImage bi = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bi.createGraphics();
        graphics.setPaint(color);
        graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        return bi;
    }
}
