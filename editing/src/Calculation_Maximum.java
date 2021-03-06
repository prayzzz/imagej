import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.GenericDialog;
import ij.plugin.ImageCalculator;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prayzzz on 08.05.2015.
 */
public class Calculation_Maximum implements PlugInFilter
{
    public int setup(String s, ImagePlus imagePlus)
    {
        return DOES_8G;
    }

    public void run(ImageProcessor imageProcessor)
    {
        int[] ids = WindowManager.getIDList();
        List<String> choices = new ArrayList<>();

        for (int id : ids)
        {
            choices.add(WindowManager.getImage(id).getTitle());
        }

        GenericDialog gd = new GenericDialog("Maximum");
        gd.addChoice("Image 1", choices.toArray(new String[choices.size()]), choices.get(0));
        gd.addChoice("Image 2", choices.toArray(new String[choices.size()]), choices.get(0));
        gd.showDialog();

        if (gd.wasCanceled())
        {
            return;
        }

        ImagePlus image1 = WindowManager.getImage(gd.getNextChoice());
        ImagePlus image2 = WindowManager.getImage(gd.getNextChoice());

        ImagePlus newImage = new ImageCalculator().run("Maximum create", image1, image2);
        newImage.show();
    }
}
