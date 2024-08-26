import java.net.*;
import java.io.*;
import java.util.*;

class Client{
    private Socket socket= null;
    private PrintWriter writer= null;
    private BufferedReader reader= null;
    static Scanner sc = new Scanner(System.in);
    Client(String ip, int port){
        try{
        socket = new Socket(ip, port);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Socket is established.");
        }
        catch(UnknownHostException u){
            System.out.println(u);
            return;
        }
        catch(IOException i){
            System.out.println(i);
            return;
        }
        
    }
    void closeSocket(){
        try{
            socket.close();
            writer.close();
            reader.close();
            System.out.println("Socket closed.");
        }
        catch(IOException i){
            System.out.println(i);
        }
    }
    void sendMessage(String originaldata, boolean iferrorReqd, String key) throws IOException{
        //encode data
        String csum = checksum.checkSum(originaldata, 16);
        String data=CRC.EncodeData(originaldata, key);
        if(iferrorReqd){
            System.out.println("Enter 1 for single bit error, 2 for double bit error, 3 for odd error and 4 for burst error");
            int err = sc.nextInt();
            if(err==1) {data = Errorinjection.singleBitError(data); originaldata = Errorinjection.singleBitError(originaldata);}
            else if(err==2) {data = Errorinjection.doubleBitError(data);originaldata = Errorinjection.doubleBitError(originaldata);}
            else if(err==3) {data = Errorinjection.oddErrors(data);originaldata = Errorinjection.oddErrors(originaldata);}
            else {data = Errorinjection.burstErrors(data); originaldata = Errorinjection.burstErrors(originaldata);}
        }
        
        writer.println(data);
        writer.println(key);
        writer.println(originaldata);
        writer.println(csum);
        System.out.println("Data sent");
    }
    public static void main(String args[]) throws IOException{
        final int port = 3000;
        String ip="";
        try{
        ip=InetAddress.getLocalHost().getHostAddress();
        }
        catch(Exception e){
            System.out.println("failed to get ip");
        }
        //socket is created
        Client client = new Client(ip, port);
        //read data from file
        FileReader obj = new FileReader("C:\\Users\\Abhi\\Dropbox\\PC\\Downloads\\chasha\\abhi\\Myasmnt\\Ass1\\data.txt");
        BufferedReader br = new BufferedReader(obj);
        String data="", key = "1101";    
          while(br.ready()){  
          data+=br.readLine();  
          }  
          br.close();    
          obj.close();
          //ask about error injection, type of error
          System.out.println("Enter 1 to inject error, 2 to not inject error");
          int choice = sc.nextInt();
          boolean iferrorReqd = choice==1?true:false; 
        client.sendMessage(data,iferrorReqd,key);
        client.closeSocket();
    }
}
