package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MandelbrotPanel extends JPanel implements ComponentListener,MouseWheelListener{
	private double left;
	private double right;
	private double bottom;
	private double top;
	private BufferedImage img;
	
	public MandelbrotPanel(){
		super();
		setup(-2,2,-2,2);
	}
	
	private void setup(double l, double r, double b, double t){
		left = l;
		right = r;
		bottom = b;
		top = t;
		img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.blue);
		g.fillRect(0, 0, 500, 500);
		for(int i=0; i<img.getHeight(); i++){
			for(int j=0; j<img.getWidth(); j++){
				int iter = MandelbrotMath.iterations(new ComplexNum(
						1.0*j/img.getWidth()*(right-left)+left,
						1.0*i/img.getHeight()*(top-bottom)+bottom));
				switch(iter/5){
					case 1:
						img.setRGB(j, i, Color.PINK.getRGB()); break;
					case 2:
						img.setRGB(j, i, Color.ORANGE.getRGB()); break;
					case 3: 
						img.setRGB(j, i, Color.GREEN.getRGB()); break;
					case 4: 
						img.setRGB(j, i, Color.CYAN.getRGB()); break;
					case 5: 
						img.setRGB(j, i, Color.MAGENTA.getRGB()); break;
					case 6: 
						img.setRGB(j, i, Color.RED.getRGB()); break;
					case 7: 
						img.setRGB(j, i, Color.LIGHT_GRAY.getRGB()); break;
					case 8: 
						img.setRGB(j, i, Color.GRAY.getRGB()); break;
					case 9: 
						img.setRGB(j, i, Color.DARK_GRAY.getRGB()); break;
					case 10: 
						img.setRGB(j, i, Color.BLACK.getRGB()); break;
				}
			}
		}
		g.drawImage(img,0,0,Color.BLUE,null);
	}

	public void componentHidden(ComponentEvent arg0) {}
	public void componentMoved(ComponentEvent arg0) {}
	public void componentShown(ComponentEvent arg0) {}
	
	public void componentResized(ComponentEvent ce) {
		Dimension d = this.getSize();
		img = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public static void main(String[] args){
		JFrame jf = new JFrame("MANDELBROT & SUCHLIKE TIRESOMENESS");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//jf.setResizable(false);
		MandelbrotPanel p = new MandelbrotPanel();
		p.addComponentListener(p);
		p.addMouseWheelListener(p);
		jf.add(p);
		jf.setSize(500, 500);
		jf.setVisible(true);
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println("Scroll Event Recorded");
		if(e.getWheelRotation()>0)
			scroll4(e.getX()*1.0/img.getWidth()*(right-left)+left,e.getY()*1.0/img.getHeight()*(top-bottom)+bottom);
		else
			scrollOut4(e.getX()*1.0/img.getWidth()*(right-left)+left,e.getY()*1.0/img.getHeight()*(top-bottom)+bottom);
		paint(getGraphics());
	}
	
	public void scroll4(double x, double y){
		left += (x-left)/4;
		right -= (right-x)/4;
		top -= (top-y)/4;
		bottom += (y-bottom)/4;
	}
	public void scrollOut4(double x, double y){
		left -= (x-left)/4;
		right += (right-x)/4;
		top += (top-y)/4;
		bottom -= (y-bottom)/4;
	}

}
