package images;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class EclipseIconEnlarger {

	private String eclipseBasePath;
	private int magnificationFactor;
	
  public EclipseIconEnlarger(String eclipseBasePath, int magnificationFactor){
	  this.eclipseBasePath = eclipseBasePath;
	  this.magnificationFactor = magnificationFactor;
  }
  
  /*
   * Command to run this utility:
   * java EclipseIconEnlarger eclipse_base_path magnification factor
   * */
  public static void main(String[] argv){
		EclipseIconEnlarger enlarger = new EclipseIconEnlarger(argv[0], Integer.parseInt(argv[1]));
		String[] formats = {"png", "gif"};
		for (String format : formats)
			enlarger.enlargeAllIcons(format);
  }
  
  public void enlargeAllIcons(String format){
	  System.out.println("Test");
	  BufferedImage img = null;
	  try {
		  File f = new File(this.eclipseBasePath);
		  List<File> matchingFiles = rListFiles(f, format);
		  
		  for (File a : matchingFiles){
			  
			  File file = new File(a.getAbsolutePath());
		      img = ImageIO.read(file);
		      if (img.getHeight() <= 16 && img.getWidth() <= 16){
		    	  System.out.println(img.getWidth()+"x"+img.getHeight()+ ": " +a.getAbsolutePath());
		    	  if (img.getType() != 0){
		    		  img = enlarge(img, this.magnificationFactor);
		    		  ImageIO.write(img, format, file);
		    	  }else{
		    		  System.out.println(img.getWidth()+"x"+img.getHeight()+ ": " +a.getAbsolutePath());
		    	  }
		      }
		  }
	      
	  } catch (IOException e) {
		  System.out.println("couldn't open the image");
	  }
  }

  public BufferedImage enlarge(BufferedImage image, int n) {

    int w = n * image.getWidth();
    int h = n * image.getHeight();

    BufferedImage enlargedImage = new BufferedImage(w, h, image.getType());

    for (int y = 0; y < h; ++y){
      for (int x = 0; x < w; ++x){
        enlargedImage.setRGB(x, y, image.getRGB(x / n, y / n));
      }
    }
    return enlargedImage;
  }
  
  public List<File> rListFiles(File dir, String format){
	  List<File> files = new ArrayList<File>();
	  File[] matchingFiles = dir.listFiles(new FilenameFilter() {
	      String _format;
		  
		  public boolean accept(File dir, String name) {
	          return name.endsWith(_format);
	      }
		  
		  public FilenameFilter initialize(String format){
			this._format = format;  
			return this;
		  }
	 
	  }.initialize(format));
	  for (File f : matchingFiles)
		  files.add(f);
	  
	  for (File temp : dir.listFiles()) {
		    if (temp.isDirectory()) {
		    	files.addAll(rListFiles(temp, format));
		    };
	  };
	  
	  return files;
  }
}