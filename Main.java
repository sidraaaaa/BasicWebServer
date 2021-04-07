import java.io.*;
import java.net.Socket; 
import java.net.ServerSocket;

class Main {
  public static void main(String[] args) throws Exception {
    // Start receiving messages - ready to receive messages!
    try(ServerSocket serverSocket=new ServerSocket(8080)){
      System.out.println("Server started\nListening msgs");
    
    while(true){

    // Handle a new incoming message
    try(Socket client=serverSocket.accept()){
      //client<--messages queued up in here
    
      System.out.println("Debug: Got a new msg"+ client.toString());
      // Read the request - listen to the message
      InputStreamReader isr= new InputStreamReader(client.getInputStream());
     
     BufferedReader br=new BufferedReader(isr);
     StringBuilder request= new StringBuilder();
     String line;
     line=br.readLine();
     while(!line.isBlank()){
       request.append(line+"\r\n");
       line=br.readLine();
     }
System.out.println("___REQUEST____");
System.out.println(request);

// Decide how we'd like to respond
    //Get the first line of the request

    String firstLine=request.toString().split("\n")[0];
      //Get the second thing "resource" from the first line (separated by spaces)
    //System.out.println(firstLine);
    String resource=firstLine.split(" ")[1];
    System.out.println(resource);
     //compare the resource to our list of things
     if(resource.equals("/img")){
       //System.out.println(resource.equals("/jess"));
               // Send back an image?
        //load image from file System
        FileInputStream image= new FileInputStream("fav.jpg");
        //System.out.println(image.toString());
        //turn the image into getBytes
        //set the content type
        OutputStream clientOutput = client.getOutputStream();
        //clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
        clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
        clientOutput.write("\r\n".getBytes());
        clientOutput.write(image.readAllBytes());
        //clientOuput.write(image.readAllBytes());
        clientOutput.flush();
     }
     else if(resource.equals("/hello")){
         // Just send back a simple "Hello World"
        //OutputStream clientOutput = client.getOutputStream();
        OutputStream clientOutput = client.getOutputStream();

        //clientOuput.write("HTTP/1.1 200 OK \r\n".getBytes());
        clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
        //clientOuput.write("\r\n".getBytes());
        clientOutput.write("\r\n".getBytes());
        //clientOuput.write("Hello World".getBytes());
        clientOutput.write("Hello World".getBytes());
        clientOutput.flush();
     }

     else{
        //OutputStream clientOutput = client.getOutputStream();
        OutputStream clientOutput = client.getOutputStream();
        //clientOuput.write("HTTP/1.1 200 OK \r\n".getBytes());
        clientOutput.write("HTTP/1.1 200 OK\r\n".getBytes());
        //clientOuput.write("\r\n".getBytes());
        clientOutput.write("\r\n".getBytes());
        //clientOuput.write("What ya looking for".getBytes());
        clientOutput.write("What ya looking for".getBytes());
        clientOutput.flush();
     }
    //send back the appropriate thing based on resource



       

//helps us to send multiple msgs


//OutputStream clientOutput = client.getOutputStream();

        // Change response based on route?

      // Send a response - send our reply

    // Get ready for the next message
    client.close();
  }
}
}
}
}
