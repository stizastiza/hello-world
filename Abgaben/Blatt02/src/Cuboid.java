/**
 * Represents a Cuboid
 * http://en.wikipedia.org/wiki/Cuboid
 * 
 * @author AlgoDat
 *
 */
public class Cuboid extends Body{

	double height, length, width;
	
	/*
	 * Constructor for a Cuboid object
	 */
	public Cuboid() {
		
	}
	/*
	 * Constructor for a Cuboid object
	 * 
	 * @param h height
	 * @param l length
	 * @param w width
	 */
	public Cuboid(double h, double l, double w) {
		if (h>0) {
			this.height = h;
		}
		else {
			this.height = 1;
		}
		if (l>0) {
			this.length = l;
		}
		else {
			this.length = 1;
		}
		if (w>0) {
			this.width = w;
		}
		else {
			this.width = 1;
		}
	}

	public double getHeight(){
		return this.height;
	}
	public double getLength(){
		return this.length;
	}
	public double getWidth(){
		return this.width;
	}
	//TODO: ggf. weitere Methoden und member implementieren
	/**
	 * Method to calculate a Volume
	 * 
	 * Volume V of a rectangular Cuboid:
	 * V = Height*Length*Width
	 * @return V
	 */
	public double calculateVolume() {
		double h = this.getHeight();
		double l = this.getLength();
		double w = this.getWidth();
		return h*w*l;
	}
	/**
	 * Method to calculate a Surface
	 * 
	 * Surface O of a rectangular Cuboid:
	 * O = 2*(Height*Length+Height*width+length*width)
	 * @return O
	 */
	public double calculateSurface() {
		double h = this.getHeight();
		double l = this.getLength();
		double w = this.getWidth();
		return 2*(h*l+h*w+l*w);
	}

}

