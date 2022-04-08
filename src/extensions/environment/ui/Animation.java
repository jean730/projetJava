package extensions.environment.ui;
public class Animation{

    private int width;
    private int height;
    private int imageWidth;
    private int imageHeight;
    private int start;
    private int frames;
    private int duration;
    private int currentFrame = 0;
    private long lastFrameUpdate = 0;

    public Animation(int width, int height, int imageWidth, int imageHeight, int start, int frames, int duration){
        this.width = width;
        this.height = height;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.start = start;
        this.frames = frames;
        this.duration = duration;
    }

    public void reset(){
        currentFrame = 0;
        lastFrameUpdate = 0;
    }

    public void update(){
        if((System.currentTimeMillis()-lastFrameUpdate)>=duration){
            currentFrame = (currentFrame+1)%frames;
            lastFrameUpdate = System.currentTimeMillis();
        }
        
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getX(){
        return ((currentFrame+start)%(imageWidth/width))*width;

    }

    public int getY(){
        return (((currentFrame+start)/(imageHeight/height)))*height;
    }


}
