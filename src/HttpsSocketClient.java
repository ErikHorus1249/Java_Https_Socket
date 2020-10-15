import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.cert.CertPath;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class HttpsSocketClient {
//    public static void main(String[] args) throws Exception{
//        System.setProperty("javax.net.ssl.trustStore", "clienttrust");
//        SSLSocketFactory ssf = (SSLSocketFactory)SSLSocketFactory.getDefault();
//        Socket s = ssf.createSocket("localhost",1249);
//
//        OutputStream outs = s.getOutputStream();
//        PrintStream out = new PrintStream(outs);
//        InputStream ins = s.getInputStream();
//        BufferedReader in = new BufferedReader(new InputStreamReader(ins));
//
//        out.println("hi, How are u\n");
//        String line = null;
//        while((line = in.readLine()) != null){
//            System.out.printf(line);
//        }
//        in.close();
//        out.close();
//    }
    public static void main(String args[]) throws Exception {
        SSLSocketFactory factory = HttpsURLConnection.getDefaultSSLSocketFactory();
        SSLSocket socket = (SSLSocket) factory.createSocket("127.0.0.1", 1249);
        socket.startHandshake();
        SSLSession session = socket.getSession();
        java.security.cert.Certificate[] servercerts = session.getPeerCertificates();

        List mylist = new ArrayList();
        for (int i = 0; i < servercerts.length; i++) {
            mylist.add(servercerts[i]);
        }

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        CertPath cp = cf.generateCertPath(mylist);

        FileOutputStream f = new FileOutputStream("CertPath.dat");
        ObjectOutputStream b = new ObjectOutputStream(f);
        b.writeObject(cp);

    }
}
//https://www.baeldung.com/java-ssl-handshake-failures
//http://www.java2s.com/Tutorial/Java/0490__Security/CertificationforHTTPS.htm
