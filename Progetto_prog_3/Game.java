package Progetto_prog_3;

public class Game implements Runnable{

    private GamePanel gamePanel;
    private GameWindow gameWindow;
    private Thread gameThread;
    private final int SET_FPS = 120;

    public Game(){

        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        StartGameLoop();
    }

    private void StartGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / SET_FPS;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        while (true) {
            
            now = System.nanoTime();

            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;        
            
            }
        }
    }
}
