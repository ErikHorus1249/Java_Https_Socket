import javax.imageio.IIOException;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HtttpsSocketServer {

//    public  static void main(String[] args) throws Exception{
//        SSLServerSocketFactory ssf = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
//        ServerSocket ss = ssf.createServerSocket(1249);
//        System.out.println("Server is running . . .");
//        while (true){
//            try {
//                Socket s = ss.accept();
//                OutputStream out = s.getOutputStream();
//                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//                String line = null;
//                while(((line = in.readLine()) != null) && (!("".equals(line)))) {
//                    System.out.println(line);
//                }
//                StringBuffer buffer = new StringBuffer();
//                buffer.append("<HTML><HEAD><TITLE>HTTPS Server</TITLE></HEAD>\n");
//                buffer.append("<BODY>\n<H1>Success!</H1></BODY></HTML>\n");
//
//                String string = buffer.toString();
//                byte[] data = string.getBytes();
//                out.write("HTTP/1.0 200 OK\n".getBytes());
//                out.write(new String("Content_length : " + data.length +"\n").getBytes());
//                out.write("Content-Type: text/html\n\n".getBytes());
//                out.write(data);
//                out.flush();
//
//                out.close();
//                in.close();
//                s.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
    public static void main(String[] args) throws IOException {
        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        ServerSocket ss = ssf.createServerSocket(1249);
        System.out.println("Server is running . . .");
        while (true) {
            try {
                Socket s = ss.accept();
                OutputStream out = s.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

                String line = null;
                while (((line = in.readLine()) != null) && (!("".equals(line)))) {
                    System.out.println(line);
                }
                StringBuffer buffer = new StringBuffer();
                buffer.append("<HTML><HEAD><TITLE>HTTPS Server</TITLE></HEAD>\n");
                buffer.append("<BODY>\n<H1>Success!</H1></BODY></HTML>\n");

                String string = buffer.toString();
                byte[] data = string.getBytes();
                out.write("HTTP/1.0 200 OK\n".getBytes());
                out.write(new String("Content-Length: " + data.length + "\n").getBytes());
                out.write("Content-Type: text/html\n\n".getBytes());
                out.write(data);
                out.flush();

                out.close();
                in.close();
                s.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
