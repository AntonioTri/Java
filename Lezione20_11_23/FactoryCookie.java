package Java.Lezione20_11_23;

class FactoryCookie {

    public static void main(String[] args){

        Cookie Temp = null;
        Cookie prot = new CoconutCookie();
        CookieMachine cm = new CookieMachine(prot);
        
        for (int i = 0; i < 100; i++) {
            Temp = cm.makeCookie();
            Temp.SayHello();
        }

    }

}