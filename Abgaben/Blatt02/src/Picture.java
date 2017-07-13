
/**
* this class <code> Picture </code> describes a RGB picture with dimensions width x height.
* Each pixels coulour is represented by an RGBColour
* 
* @author AlgoDat
*
*/

import javax.imageio.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Picture {

    /**
    * A 2d array containing the colors of the individual pixels
    */
    private RGBColor imageMatrix[][];

    /**
    * The width of the image in pixels
    */
    private int width;

    /**
    * height of the image in pixels
    */
    private int height;
    
    /**
     * initialize a picture by creating a white picture
     */
    public Picture(int width, int height){
        this.height = height;
        this.width = width;
        createWhitePicture();
    }
    /**
     * initialize a picture by opening given file
     * @param picUrl path of *.bmp picture
     */
    public Picture(String picUrl){      
        openAndSetPicture(picUrl);      
    }

    /**
    * intialize a picture by giving an image matrix
    * @param imageMatrix two dimansionar RGBColor array
    */
    public Picture(RGBColor imageMatrix[][]){
        this.width = imageMatrix.length;
        this.height = imageMatrix[0].length;
        this.imageMatrix = imageMatrix;
    }

    /**
     * turns this picture 90 degrees to the right
     * newMatrix[][] is a empty matrix to fill with new 90-deg. rotated numbers
     * it is there, because i need to access some number twice, that is why i cannot work with
     * the same matrix imageMatrix
     * 
     * 
     * 
     */

    public void rot90DegRight(){
    	RGBColor newMatrix[][] = new RGBColor[this.height][this.width];
    	 	if (this.height == this.width) {
    	 		for (int h=0; h<this.height; h++) {
    	 			for (int w=0; w<this.width; w++) {
    	 				newMatrix[w][h] = this.imageMatrix[h][this.width-w-1];
    	 			}
    	 		}
    	 	} else {
    	 		for (int h=0; h<this.width; h++) {
    				for (int w=0; w<this.height; w++) {
    					newMatrix[w][h] = this.imageMatrix[h][this.height-w-1];
    				}
    	 		}
    	 	}
    	 	int swap;
    	 	swap = this.height;
    	 	this.height = this.width;
    	 	this.width = swap;
    	 	this.imageMatrix = newMatrix; 
    }
    
    /**
     * turns this picture 180 degrees
     *
     */
    public void rot180Deg(){
		rot90DegRight();
		rot90DegRight();
    }
    /**
     * Function checks, if pixel with this parameters of width and height is located
     * in appropriate bounds * 0<=widthPar<=this.width && 0<=heightPar<=this.height
     * @param widthPar width of Pixel
     * @param heightPar height of Pixel
     * @return true *if it is the case
     * @return false if its out of bounds
     */
    public boolean checkPixelBounds(int widthPar, int heightPar) {
    	if (widthPar>=0 && heightPar>=0 && widthPar<this.width && heightPar<this.height) {
    		return true;
    	}
    	else {
    		return false; 
    	}
    }
    
    /**
     * finds white pixels and approximates their new color by using the average of neighbor colors
     * x,y,z are new values of my pixel
     * ArrayList Fixed contains repaired pixels, - this way i remember, which pixel values should not
     * be used in calculation of Spots
     * 
     * w1, h1 are there to create kind of small Matrix from 8 Pixels around the one we are interested in to change
     * i is a counter. It counts how many Pixels were used to approximate a new color for a white spot.
     * funktion checkPixelBounds checks, if pixel might be out of bounds
     */    
    public void repairPicture(){     
    	ArrayList<RGBColor> Fixed = new ArrayList<RGBColor>();
    	for (int w=0; w<this.width; w++) {
    		for (int h=0; h<this.height; h++) {
    			if (this.imageMatrix[w][h].isWhite()) {
    				Fixed.add(this.imageMatrix[w][h]);
    				// running through little matrix of 8 neighbor elements
    		    	int x = 0;
    		    	int y = 0;
    		    	int z = 0;
    		    	int i = 0; 
    				for (int w1=w-1; w1<=w+1; w1++) {
    					for (int h1=h-1; h1<=h+1; h1++) {
    						//i don`t need to check, if w1!=w or h1!=h is, because this.imageMatrix[w][h] is white
    						if (checkPixelBounds(w1, h1)){
    							if (Fixed.contains(this.imageMatrix[w1][h1])==false && this.imageMatrix[w1][h1].isWhite()==false) {
    							x = x+this.imageMatrix[w1][h1].getRed();
    							y = y+this.imageMatrix[w1][h1].getGreen();
    							z = z+this.imageMatrix[w1][h1].getBlue();
    							i++; //how many pixels are in count to calculate a new value of x,y,z
    							}
    						}
    					}	
    				}
    				x = x/i;
    				y = y/i;
    				z = z/i;
    				// set my new color to x, y, z i got comparing 8 nearest pixels
    				this.imageMatrix[w][h] = new RGBColor(x, y, z);
    			}
    		}
    	}

    }
        
    /**
     * Creates a completely white picture
     *
     */
    public void createWhitePicture(){
        this.imageMatrix = new RGBColor[this.width][this.height];
        for (int w=0; w< this.width; w++){
            for(int h=0; h< this.height; h++){
                //set this colors in picture
                this.imageMatrix[w][h] = new RGBColor(255, 255, 255);                
            }
        }
    }

    /**
    * DO NOT CHANGE ANYTHING BELOW THIS LINE!
    **/

    // Getters
    
    /**
     * 
     * @return the width of the picture
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * 
     * @return the height of the picture
     */
    public int getHeight(){
        return this.height;
    }

    /**
     * 
     * @return the the picture matrix
     */
    public RGBColor[][] getImageMatrix(){
        return this.imageMatrix;
    }
    
    /**
     * reads an 24-bit(8,8,8) Bitmap and store it into picture-array
     * @param picUrl The url to the pic
     * @return true, if successful else false
     */
    private boolean openAndSetPicture(String picUrl){
         
         BufferedImage pic;
         
         
         try {
             InputStream iS= new FileInputStream(picUrl);
             // get buffer of the picture
             pic = ImageIO.read(iS);    
             
             // get additional picture informations
             this.height = pic.getHeight();
             this.width = pic.getWidth();            
             
             // store rgb colors in picture
             this.imageMatrix = new RGBColor[this.width][this.height];
             ColorModel cm= ColorModel.getRGBdefault();
             for (int w=0; w< this.width; w++){
                 for(int h=0; h< this.height; h++){
                     
                     // read out every RGBcolor
                     int pixel = pic.getRGB(w, h);
                     int rVal= cm.getRed(pixel);
                     int gVal= cm.getGreen(pixel);
                     int bVal= cm.getBlue(pixel);
                     
                     //set this colors in picture
                     this.imageMatrix[w][h] = new RGBColor(rVal, gVal, bVal);                
                 }
             }
             return true;
             
             
         }catch (IOException e) {
             e.printStackTrace();       
         }
         return false;
         
    }
    
    public BufferedImage getImage(){
        BufferedImage buf = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
        for(int w=0; w<this.width; w++){
            for(int h=0; h<this.height; h++){
                int red= this.imageMatrix[w][h].getRed();
                int blue= this.imageMatrix[w][h].getBlue();
                int green= this.imageMatrix[w][h].getGreen();
                int rgbVal= new Color(red, green, blue).getRGB();
                buf.setRGB(w, h, rgbVal);
            }
    }
    
        return buf;
    }


    
    
}

