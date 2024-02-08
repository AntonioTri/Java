package DesignPatterns.MementoPattern;

public class Originator {
    
    private String article;

    public void set(String newArticle){
        System.out.println("From Originator: Curent version\n"+ newArticle + "\n");
        article = newArticle;
    }
    
    public Memento storeInMemento(){
        System.out.println("From riginator: Saving to Memento");
        return new Memento(article);
    }

    public String restoreFroomMemento(Memento memento){
        article = memento.getSavedArticle();
        System.out.println("From OOriginator: Previuos article in Memento\n"+ article + "\n");
        return article;
    
    }

}
