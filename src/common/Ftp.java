// 
// Decompiled by Procyon v0.5.30
// 

package common;

import java.util.Vector;
import java.util.StringTokenizer;
import java.net.ServerSocket;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.net.Socket;

public class Ftp
{
    private String serverIp;
    private String user;
    private String reply;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private int port;
    private boolean passive;
    
    public Ftp() {
        this.serverIp = "";
        this.user = "";
        this.reply = "";
        this.port = 21;
        this.passive = false;
    }
    
    public boolean isConnect() {
        return this.out != null;
    }
    
    public void ftpConnect(final String serverIp, final String user, final String s) throws Exception {
        this.serverIp = serverIp;
        this.user = user;
        this.socket = new Socket(serverIp, this.port);
        this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        this.out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        this.getRespond();
        this.ftpLogin(user, s);
    }
    
    public String getReply() {
        return this.reply;
    }
    
    public void close() throws IOException {
        this.ftpCommand("QUIT");
        if (!this.reply.substring(0, 3).equals("221")) {
            throw new IOException();
        }
        this.in.close();
        this.out.close();
        this.socket.close();
        this.in = null;
        this.out = null;
        this.socket = null;
        this.serverIp = "";
        this.user = "";
    }
    
    public void getFile(final String s, final String s2, final boolean b) throws Exception {
        Socket dataSocket = null;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            if (this.out == null) {
                return;
            }
            if (b) {
                this.ftpCommand("TYPE A");
            }
            else {
                this.ftpCommand("TYPE I");
            }
            dataSocket = this.getDataSocket(new String[] { "RETR " + s });
            if (dataSocket == null) {
                throw new IOException();
            }
            final File file = new File(s2);
            bufferedInputStream = new BufferedInputStream(dataSocket.getInputStream());
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            this.readData(bufferedInputStream, bufferedOutputStream);
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            }
            catch (Exception ex2) {}
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            }
            catch (Exception ex3) {}
            try {
                if (dataSocket != null) {
                    dataSocket.close();
                }
            }
            catch (Exception ex4) {}
            this.getRespond();
            if (!this.reply.substring(0, 3).equals("226")) {
                throw new IOException();
            }
        }
    }
    
    private void readData(final BufferedInputStream bufferedInputStream, final BufferedOutputStream bufferedOutputStream) throws IOException {
        final byte[] array = new byte[2048];
        int read;
        while ((read = bufferedInputStream.read(array, 0, array.length)) != -1) {
            bufferedOutputStream.write(array, 0, read);
        }
    }
    
    public void putFile(final String s, final String s2, final boolean b) throws Exception {
        if (this.out == null) {
            return;
        }
        if (b) {
            this.ftpCommand("TYPE A");
        }
        else {
            this.ftpCommand("TYPE I");
        }
        Socket dataSocket = null;
        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try {
            dataSocket = this.getDataSocket(new String[] { "STOR " + s });
            final File file = new File(s2);
            bufferedOutputStream = new BufferedOutputStream(dataSocket.getOutputStream());
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            this.outData(bufferedOutputStream, bufferedInputStream);
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            try {
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
            }
            catch (IOException ex2) {}
            try {
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
            }
            catch (IOException ex3) {}
            try {
                if (dataSocket != null) {
                    dataSocket.close();
                }
            }
            catch (IOException ex4) {}
            this.getRespond();
            if (!this.reply.substring(0, 3).equals("226")) {
                throw new IOException();
            }
        }
    }
    
    private void outData(final BufferedOutputStream bufferedOutputStream, final BufferedInputStream bufferedInputStream) throws IOException {
        final byte[] array = new byte[2048];
        int read;
        while ((read = bufferedInputStream.read(array, 0, array.length)) != -1) {
            bufferedOutputStream.write(array, 0, read);
        }
    }
    
    private Socket getDataSocket(final String[] array) throws Exception {
        Socket socket = null;
        ServerSocket activeDataSocket = null;
        try {
            if (this.passive) {
                socket = this.getPassiveDataSocket();
            }
            else {
                activeDataSocket = this.getActiveDataSocket();
            }
            for (int i = 0; i < array.length; ++i) {
                this.ftpCommand(array[i]);
            }
            if (!this.reply.substring(0, 3).equals("150") && !this.reply.substring(0, 3).equals("125")) {
                if (socket != null) {
                    socket.close();
                }
                throw new IOException();
            }
            if (!this.passive) {
                socket = activeDataSocket.accept();
            }
        }
        catch (Exception ex) {
            throw ex;
        }
        return socket;
    }
    
    private Socket getPassiveDataSocket() throws Exception {
        int n = 1;
        do {
            this.ftpCommand("PASV");
            if (++n == 4) {
                throw new Exception("서버 접속에 실패했습니다.");
            }
        } while (!this.reply.substring(0, 3).equals("227"));
        System.out.println(">" + this.reply);
        final String[] array = new String[6];
        final StringTokenizer stringTokenizer = new StringTokenizer(this.reply, ",");
        for (int i = 0; i < 6; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        String string = "";
        int length = 3;
        if (array[5].length() < 3) {
            length = array[5].length();
        }
        for (int j = 0; j < length; ++j) {
            if (Character.isDigit(array[5].charAt(j))) {
                string += array[5].charAt(j);
            }
        }
        array[5] = string;
        final int n2 = (Integer.parseInt(array[4]) << 8) + Integer.parseInt(array[5]);
        System.out.println(">" + n2);
        return new Socket(this.serverIp, n2);
    }
    
    private ServerSocket getActiveDataSocket() throws IOException {
        final int[] array = new int[6];
        final StringTokenizer stringTokenizer = new StringTokenizer(this.socket.getLocalAddress().getHostAddress(), ".");
        for (int i = 0; i < 4; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        final ServerSocket serverSocket = new ServerSocket(0);
        final int localPort = serverSocket.getLocalPort();
        array[4] = (localPort & 0xFF00) >> 8;
        array[5] = (localPort & 0xFF);
        String s = "PORT ";
        for (int j = 0; j < array.length; ++j) {
            s = s.concat(String.valueOf(array[j]));
            if (j < array.length - 1) {
                s = s.concat(",");
            }
        }
        this.ftpCommand(s);
        return serverSocket;
    }
    
    private void ftpLogin(final String s, final String s2) throws IOException {
        this.ftpCommand("USER " + s);
        this.ftpCommand("PASS " + s2);
        if (!this.reply.substring(0, 3).equals("230")) {
            this.close();
            throw new IOException("ftp login 오류");
        }
    }
    
    public void setCurrentDirectory(final String s) throws Exception {
        if (this.out == null) {
            return;
        }
        this.ftpCommand("CWD " + s);
        if (!this.reply.substring(0, 3).equals("250")) {
            throw new Exception(this.getReply());
        }
    }
    
    public Vector getDirectoryContent() throws Exception {
        Socket dataSocket = null;
        BufferedReader bufferedReader = null;
        try {
            if (this.out == null) {
                return null;
            }
            dataSocket = this.getDataSocket(new String[] { "LIST" });
            bufferedReader = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
            final Vector<String> vector = new Vector<String>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                vector.add(line);
            }
            this.getRespond();
            if (!this.reply.substring(0, 3).equals("226")) {
                throw new IOException("List 오류");
            }
            return vector;
        }
        catch (Exception ex) {
            throw ex;
        }
        finally {
            try {
                if (dataSocket != null) {
                    dataSocket.close();
                }
            }
            catch (IOException ex2) {}
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (IOException ex3) {}
        }
    }
    
    public void renameFile(final String s, final String s2) throws Exception {
        if (this.out == null) {
            return;
        }
        this.ftpCommand("RNFR " + s);
        if (!this.reply.substring(0, 3).equals("350")) {
            throw new Exception(this.getReply());
        }
        this.ftpCommand("RNTO " + s2);
        if (!this.reply.substring(0, 3).equals("250")) {
            throw new Exception(this.getReply());
        }
    }
    
    public void deleteFile(final String s) throws Exception {
        if (this.out == null) {
            return;
        }
        this.ftpCommand("DELE " + s);
        if (!this.reply.substring(0, 3).equals("250")) {
            throw new Exception(this.getReply());
        }
    }
    
    public void makeDirectory(final String s) throws Exception {
        if (this.out == null) {
            return;
        }
        this.ftpCommand("MKD " + s);
        if (!this.reply.substring(0, 3).equals("257")) {
            throw new Exception(this.getReply());
        }
    }
    
    public void setPrompt() throws Exception {
        if (this.out == null) {
            return;
        }
        this.ftpCommand("PROMPT");
        Log.debug(this.reply);
    }
    
    private void ftpCommand(final String s) throws IOException {
        if (this.out == null) {
            return;
        }
        this.out.write(s + "\r\n");
        this.out.flush();
        this.getRespond();
    }
    
    private void getRespond() throws IOException {
        String concat = "";
        String line;
        while (true) {
            line = this.in.readLine();
            if (!this.checkReply(line)) {
                break;
            }
            concat = concat.concat(line).concat("\n");
        }
        this.reply = line;
    }
    
    private boolean checkReply(final String s) {
        return s.length() <= 3 || s.charAt(3) != ' ' || !Character.isDigit(s.charAt(0)) || !Character.isDigit(s.charAt(1)) || !Character.isDigit(s.charAt(2));
    }
}
