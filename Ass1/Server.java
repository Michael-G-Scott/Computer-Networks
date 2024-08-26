import java.net.*;
import java.io.*;
import java.util.*;

class Server{
    private Socket socket= null;
    private ServerSocket server=null;
    private PrintWriter writer= null;
    private BufferedReader reader= null;
    Server(int port){
        try{
        server = new ServerSocket(port);
        socket=server.accept();
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("Socket is established. ");
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
    void closeSocket() {
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
    void rcvMessage() throws IOException{
        ArrayList<String> s = new ArrayList<>();
        String packet;
        while((packet=reader.readLine())!= null){
            s.add(packet);
        }
        //msg read, now check for error
        String data = s.get(0);
        String key = s.get(1);
        String originaldata = s.get(2);
        String csum = s.get(3);
        boolean CRCdatacorrect = CRC.ifCorrect(data, key);
        boolean Checksumdatacorrect = checksum.checker(originaldata, csum, 16);
        if(CRCdatacorrect) System.out.println("CRC: Data is error free");
        else System.out.println("CRC: Data is erroneous");
        if(Checksumdatacorrect) System.out.println("CHECKSUM: Data is error free");
        else System.out.println("CHECKSUM: Data is erroneous");
    }
    public static void main(String args[]) throws IOException{
        int port = 3000;
        Server server = new Server(port);
        server.rcvMessage();
        server.closeSocket();
    }
}
