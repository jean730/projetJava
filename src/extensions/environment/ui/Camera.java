package extensions.environment.ui;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;

public class Camera{
    private BufferedImage cameraView;
    private Graphics2D graphics;
    private int width;
    private int height;

    public Camera(int width,int height){
        if(width==0 || height==0){
            width=1;
            height=1;
        }
        cameraView = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        graphics = (Graphics2D)cameraView.getGraphics();
        this.width = width;
        this.height = height;
    }

    public void setTransform(double posX,double posY,double scale,double rotation){
        AffineTransform transform = new AffineTransform();
        transform.translate(posX,posY);
        transform.scale(scale,scale);
        transform.rotate(rotation,width/2,height/2);
        graphics.setTransform(transform);
    }

    public Graphics graphics(){
        return (Graphics)graphics;
    }
    
    public BufferedImage getImage(){
        return cameraView;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}

