
    package Metodos;

    import java.math.BigInteger;
    import java.security.MessageDigest;
    import java.util.Arrays;
    import javax.crypto.Cipher;
    import javax.crypto.SecretKey;
    import javax.crypto.spec.SecretKeySpec;
    import org.apache.commons.codec.binary.Base64;
    import java.security.NoSuchAlgorithmException;

    public class MD5
    {
        public static String EncriptarPassword(String Pass)
        {
            try
            {
                MessageDigest Md = MessageDigest.getInstance("MD5");
                byte[] MessageDigest = Md.digest(Pass.getBytes());
                BigInteger Number = new BigInteger(1, MessageDigest);
                String HashText = Number.toString(16);

                while(HashText.length() < 32)
                {
                    HashText = "0" + HashText;
                }
                return HashText;
            }
            catch(NoSuchAlgorithmException Ex)
            {
                throw new RuntimeException(Ex);
            }
        }

        public static String DeesencriptarPassword(String Pass) throws Exception
        {
            String SecretKey = "EDD_201801628";
            String PassEncrypted = "";

            try
            {
                byte[] Message = Base64.decodeBase64(Pass.getBytes("utf-8"));
                MessageDigest Md = MessageDigest.getInstance("MD5");
                byte[] DigestOfPass = Md.digest(SecretKey.getBytes("utf-8"));
                byte[] KeyBytes = Arrays.copyOf(DigestOfPass, 24);
                SecretKey Key = new SecretKeySpec(KeyBytes, "DFSede");

                Cipher Decipher = Cipher.getInstance("DESede");
                Decipher.init(Cipher.DECRYPT_MODE, Key);

                byte[] PlainText = Decipher.doFinal(Message);

                PassEncrypted = new String(PlainText, "UTF-8");
            }
            catch(Exception Ex)
            {
                throw Ex;
            }
            return PassEncrypted;
        }
    }
