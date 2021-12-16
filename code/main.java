

package encrypt;

import java.io.File;

/**
 *
 * @author SP20-BSE-073
 */
public class Main {
    public static void main(String[] args)
	{
		Encryptor en = Encryptor.getEncrypter(true);
		Decryptor de = Decryptor.getDecrypter(true);
		
		File src = new File("C:\\src\\back.png");
		// File dst = new File("C:\\dst");
		en.encrypt(src,src);
	//de.decrypt(src, src);
}
}
