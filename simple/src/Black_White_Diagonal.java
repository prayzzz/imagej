import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.plugin.PlugIn;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.ImageProcessor;

/**
 * Created by prayzzz on 02.05.2015.
 */
public class Black_White_Diagonal implements PlugIn
{
    public void run(String arg)
    {
        GenericDialog gd = new GenericDialog("Black White Diagonal");
        gd.addNumericField("Line Width", 33, 0);
        gd.showDialog();
        if (gd.wasCanceled())
        {
            return;
        }

        int w = 400, h = 400;
        ImageProcessor ip = new ByteProcessor(w, h);
        byte[] pixels = (byte[]) ip.getPixels();

        int lineWidth = (int) gd.getNextNumber();

        int i = 0;
        for (int y = 0; y < h; y++)
        {
            for (int x = 0; x < w; x++)
            {
                Point currentPoint = rotateInverse(new Point(x, y));
                int intensity = Math.floorDiv(currentPoint.Y, lineWidth) % 2 == 0 ? 0 : 255;

                pixels[i++] = (byte) intensity;
            }
        }

        new ImagePlus("Black White Diagonal", ip).show();
    }

    private Point rotateInverse(Point point)
    {
        int x = (int) Math.round(point.X * Math.cos(45) + point.Y * Math.sin(45));
        int y = (int) Math.round(point.Y * Math.cos(45) - point.X * Math.sin(45));

        return new Point(x, y);
    }

    private class Point
    {
        public int X;
        public int Y;

        public Point(int x, int y)
        {
            this.X = x;
            this.Y = y;
        }
    }
}
