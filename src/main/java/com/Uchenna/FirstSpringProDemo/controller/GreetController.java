package com.Uchenna.FirstSpringProDemo.controller;

import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/greeting")
public class GreetController {

    int currentCount;
    int prevCount;
    File file = new File("src/main/java/com/Uchenna/FirstSpringProDemo/message.txt");

    @PostMapping("/post")
    public String postMessage(@RequestBody String message){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
            bw.write(message + "#");
            currentCount++;

        }
        catch(IOException e){
            System.out.println(e.getMessage());

        }
        return "message sucessful";
    }

    @GetMapping
    public String readFromFile(){
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String x;
            while((x = br.readLine()) != null){
               stringBuilder.append(x);
            }

        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return stringBuilder.toString();
    }



    @GetMapping("/count")
    public  int getcount(){
        return currentCount;
    }


    @GetMapping("/log")
    public int writeToLog(){
        while(prevCount < currentCount){
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/java/com/Uchenna/FirstSpringProDemo/log.txt"))){
                bufferedWriter.write("New message created");
                prevCount++;
            }
            catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
        return prevCount;

    }






}


