class Sphere {
	// members:
	Point center;
	double radius;
	// constructors:
	Sphere(int x, int y, int z, double r){
		// use a constructor in Point.java class:
		this.center = new Point(x, y, z);
		this.radius = r;	
	}
	
	Sphere(Point c, double r){
		this.center = c;
		this.radius = r;
	}
	// methods:
	int getX(){
		// use the class Point.java methods:
		return this.center.getX();
	}

	int getY(){
		return this.center.getY();
	}

	int getZ(){
		return this.center.getZ();
	}


	double getRadius(){
		return this.radius;
	}

	double calculateDiameter(){
		// Diameter is double radius:
		return this.radius*2;
	}	
	
	double calculateSurfaceArea(){
		// O = 4*Pi*r^2:
		double O = 4*Math.PI*Math.pow(this.radius, 2);
		return O;
		}
	
	double calculateVolume(){
		// V = 4/3 Pi*r^3:
		double V = (4.0/3.0) * Math.PI * Math.pow(this.radius, 3);
		// integer division is a devision with a "floor". Which is not what we currently need.
		return V;
	}

}

