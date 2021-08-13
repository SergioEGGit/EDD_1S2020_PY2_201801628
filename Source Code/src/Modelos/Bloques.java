
    package Modelos;

    import java.sql.Timestamp;


    public class Bloques
    {
        private int Index;
        private Timestamp TimeStamp;
        private String Data;
        private long NONCE;
        private String Previoushash;
        private String Hash;
        private Bloques Sgte;
        private Bloques Ante;

        public int getIndex()
        {
            return Index;
        }

        public void setIndex(int index)
        {
            Index = index;
        }

        public Timestamp getTimeStamp()
        {
            return TimeStamp;
        }

        public void setTimeStamp(Timestamp timeStamp)
        {
            TimeStamp = timeStamp;
        }

        public String getData()
        {
            return Data;
        }

        public void setData(String data)
        {
            Data = data;
        }

        public long getNONCE()
        {
            return NONCE;
        }

        public void setNONCE(long NONCE)
        {
            this.NONCE = NONCE;
        }

        public String getPrevioushash()
        {
            return Previoushash;
        }

        public void setPrevioushash(String previoushash)
        {
            Previoushash = previoushash;
        }

        public String getHash()
        {
            return Hash;
        }

        public void setHash(String hash)
        {
            Hash = hash;
        }

        public Bloques getSgte()
        {
            return Sgte;
        }

        public void setSgte(Bloques sgte)
        {
            Sgte = sgte;
        }

        public Bloques getAnte()
        {
            return Ante;
        }

        public void setAnte(Bloques ante)
        {
            Ante = ante;
        }

        public Bloques(int index)
        {
            Index = index;
        }
    }
