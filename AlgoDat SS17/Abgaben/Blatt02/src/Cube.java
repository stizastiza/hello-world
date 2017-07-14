/**
 * Represents a Cube
 * @author AlgoDat
 */
public class Cube extends Cuboid {

	/*
	 * Constructor without parameter
	 */
	public Cube() {
		
	}
	/*
	 * Constructor with one parameter
	 * 
	 * @param length the side length of the cube
	 */
	public Cube(double length) {
		if (length>0) {
			this.length = length;
			this.height = length;
			this.width = length;
		}
		else {
			this.length = 1;
			this.height = 1;
			this.width = 1;
		}
	}

	public double getLength(){
		return this.length;
	}
	//TODO: ggf. weitere Methoden und member implementieren
	/**
	 * Method to calculate a Volume
	 * 
	 * Volume V of a Cube:
	 * V = Length^3
	 * @return V
	 */
	public double calculateVolume() {
		double l = this.getLength();
		return Math.pow(l, 3);
	}
	/**
	 * Method to calculate a Surface
	 * 
	 * Surface O of a Cube:
	 * O = 6*Length^2
	 * @return O
	 */
	public double calculateSurface() {
		double l = this.getLength();
		return 6*Math.pow(l, 2);
	}

}

