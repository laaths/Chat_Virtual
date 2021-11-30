package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPServerSocket {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket listenSocket = null;
        
        
        try {
            serverSocket = new ServerSocket(6789);
            System.out.println("Aguardando conexao...");
            while(true) {
                listenSocket = serverSocket.accept();
                System.out.println("Cliente conectado!!");
                DataInputStream dis = new DataInputStream(
                listenSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(
                listenSocket.getOutputStream());
            
            
                String nome = dis.readUTF();
                System.out.println("Recebido o numero"+nome);
                int resultado = Integer.parseInt(nome) + 100;
                String hello = "Resultado: "+resultado;
                dos.writeUTF(hello);            
                System.out.println("Enviado "+hello);
                if(resultado == 100 || nome == ""){
                    break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TCPServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if(listenSocket != null){
                try {
                    listenSocket.close();
                    serverSocket.close();
                   
                } catch (IOException ex) {
                    Logger.getLogger(TCPServerSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        }
    
    
}
