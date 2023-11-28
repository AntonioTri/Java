package ProgrammiACaso;
import java.text.MessageFormat;
import java.util.logging.Logger;



public class App {

    public static void main(String[] args) throws Exception {
        final var aes = new AesEncryptionUtil("Y2lhbzU2Nzg5MTIzNDU2Cg==");
        final var encryptedText = aes.encrypt("Mammt annur! :D");
        final var decryptedText = aes.decrypt(encryptedText);

        final Logger log = Logger.getLogger(App.class.getName());
        log.info(MessageFormat.format("encryptedText ---> {0}", encryptedText));
        log.info(MessageFormat.format("decryptedText ---> {0}", decryptedText));
    }
}

/**
 * AesEncryptionUtil
 */
