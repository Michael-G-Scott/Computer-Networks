import java.util.*;
public class Errorinjection {
    static String singleBitError(String data){
        int len = data.length();
        StringBuilder temp = new StringBuilder(data);
        Random random = new Random();
        int r = random.nextInt(len);
        if(temp.charAt(r)=='0') temp.setCharAt(r, '1');
        else temp.setCharAt(r, '0');
        data = temp.toString();
        return data;
    }
    static String doubleBitError(String data){
        int len = data.length();
        StringBuilder temp = new StringBuilder(data);
        Random random = new Random();
        int r1 = random.nextInt(len);
        int r2 = random.nextInt(len);
        if(temp.charAt(r1)=='0') temp.setCharAt(r1, '1');
        else temp.setCharAt(r1, '0');
        if(temp.charAt(r2)=='0') temp.setCharAt(r2, '1');
        else temp.setCharAt(r2, '0');
        data = temp.toString();
        return data;
    }
    static String oddErrors(String data){
        int len = data.length();
        StringBuilder temp = new StringBuilder(data);
        for(int i=1;i<len;i+=2){
            if(temp.charAt(i)=='0')
            temp.setCharAt(i, '1');
            else temp.setCharAt(i, '0');
        }
        data = temp.toString();
        return data;
    }
    static String burstErrors(String data){
        int len = data.length();
        StringBuilder temp = new StringBuilder(data);
        Random random = new Random();
        int r = random.nextInt(len);
        for(int i=0;i<=r;i++){
            if(temp.charAt(i)=='0') temp.setCharAt(i, '1');
            else temp.setCharAt(i, '0');
        }
        data = temp.toString();
        return data;
    }
    public static void main(String args[]){

    }
}
