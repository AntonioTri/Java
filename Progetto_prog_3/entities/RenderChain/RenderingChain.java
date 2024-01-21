package Progetto_prog_3.entities.RenderChain;

public interface RenderingChain {
    
    public void setNextHandler(RenderingChain nextHandler);
    public void renderEntity(int enemyType);

}
