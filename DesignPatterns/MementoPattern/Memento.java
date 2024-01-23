package DesignPatterns.MementoPattern;

//Questa classe contiene tutte le variabili da salvare, con tutti i corrispettivi getters e setters
//Per settare ed otenere i valori
public class Memento {
    
    private String article;

    public Memento(String articleSave){
        article = articleSave;
    }

    public String getSavedArticle(){
        return article;
    }

}
