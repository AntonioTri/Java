package Java.Lezione20_11_23;

class FactoryCookie {

    public static void main(String[] args){

        Cookie cookie = new Cookie();
        System.out.println("Ciao, sono il biscotto originale :D");

        for (int i = 0; i < 100; i++){

            Cookie cookie2 = (Cookie)cookie.clone();
            cookie2.SayHello();
        }

    }

}