package Java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
	
	
	static RSA rsa;
    static byte[] bFile;
    private String mass;
    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
        rsa.setMass(mass);
    }
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static String getWin() throws IOException{
        FileInputStream fis=null;
        File file = new File("E:\\demo.wav");
        bFile = new byte[(int) file.length()];
        byte [] chuoi = new byte[1024];
        try {
            // convert file into array of bytes
            fis = new FileInputStream(file);
            fis.read(bFile);
            fis.close();
            String encode = rsa.getEncode();
            chuoi = encode.getBytes();
            Ky(chuoi,bFile);
        } catch (Exception e) {
            System.out.print("Loi");
        }
        return "Win";
    }
    public static void Ky(byte [] chuoi,byte[] mang){
        int [] so = new int[8];
        int k=0;
        for (int i = 0 ; i<chuoi.length;i++){
            so = trans(so,(int)chuoi[i]);
            for(int j=0;j<mang.length;j++){
                int sogoc = mang[j]%2;
                int kq =  so[k]%2;
                if(kq !=sogoc){
                    if(kq==0 && mang[j]>=0){
                        mang[j] -=1;
                    }
                    else if(kq==0&& mang[j]<0){
                        mang[j] +=1;
                    }else if(kq == 1 && mang [j]>0){
                        mang[j]-=1;
                    }else mang[j]+=1;
                }
            }
            k++;
            k %=8;
            if(k==0) break;
        }
    }
    public static int[] trans(int [] so,int tmp){
        for(int i = 0;i<8;i++){
            so[i]=tmp%2;
            tmp /=2;
        }
        return so;
    }
}
