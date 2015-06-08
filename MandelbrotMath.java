package src;

public class MandelbrotMath {
	public static double ESCAPE = 2;
	
	public static ComplexNum nextF(ComplexNum z, ComplexNum c){
		return z.square().add(c);
	}
	
	public static int iterations(ComplexNum c){
		ComplexNum z = new ComplexNum(c.x,c.y);
		int i=0;
		while(i<50 && z.abs()<=ESCAPE){
			i++;
			z = nextF(z,c);
		}
		return i;
	}
}
