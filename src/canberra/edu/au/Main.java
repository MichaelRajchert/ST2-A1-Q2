package canberra.edu.au;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
    public static ArrayList<String> inputData = new ArrayList<String>();
    public static ArrayList<String> exportData = new ArrayList<String>();
    public static String inputPath = "C:/Users/Michael Rajchert/Desktop/files/unsortedList.csv";
    public static String outputPath = "C:/Users/Michael Rajchert/Desktop/files/sortedList.csv";
    public static StudentInfo_Class[] studentInfo = new StudentInfo_Class[21];

    //SORTING
    public static void Sort(){
        for (int pass = 0; pass != studentInfo.length - 1; pass++){
            for (int step = pass; step != studentInfo.length -1; step++){
                if((compareSmaller(studentInfo[step].finalMark, studentInfo[pass].finalMark) == studentInfo[pass].finalMark)){
                    swapClassData(step,pass);
                }
            }
        }
    }

    //UTILITIES
    public static ArrayList<String> readFile(String path) throws IOException{
        //Establish vars
        ArrayList<String> fileData = new ArrayList<String>();
        String lineData = null;

        //call file and buffered readers
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //If line has data, add to ArrayList
        while ((lineData = bufferedReader.readLine()) != null){
            fileData.add(lineData);
        }

        //stop reading and return ArrayList
        bufferedReader.close();
        return fileData;
    }
    public static void writeFile(String path, ArrayList<String> dataToWrite, boolean appendToFile) throws IOException{
        //call file and print writers
        FileWriter fileWriter = new FileWriter(path, appendToFile);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("StudentID,FirstName,LastName,FinalMark,FinalGrade");

        for (int line = 0; line < studentInfo.length - 1; line++){
            printWriter.print(studentInfo[line].id + ",");
            printWriter.print(studentInfo[line].firstName + ",");
            printWriter.print(studentInfo[line].lastName + ",");
            printWriter.print(studentInfo[line].finalMark + ",");
            printWriter.println(studentInfo[line].finalGrade);
        }

        //stop reading
        printWriter.close();
    }
    public static int fileLineCount(String path) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(path));
        int lines = 0;
        while (reader.readLine() != null) lines++;
        reader.close();
        return lines;
    }
    public static void importData(){
        //for each line
        for (int i = 1; i < inputData.size(); i++){
            //split each line into is individual parts and form a holding array
            String[] splitInputHolder = (inputData.get(i)).split(",");
            //access class
            studentInfo[i-1] = new StudentInfo_Class();
            //record data
            studentInfo[i-1].id = Integer.parseInt(splitInputHolder[0]);
            studentInfo[i-1].firstName = splitInputHolder[1];
            studentInfo[i-1].lastName = splitInputHolder[2];
            studentInfo[i-1].finalMark = Integer.parseInt(splitInputHolder[3]);
            studentInfo[i-1].finalGrade = splitInputHolder[4];
        }
    }
    public static void exportData(){
        for (int line = 0; line < 20; line++){
            String id = String.valueOf(studentInfo[line].id);
            String finalMark = String.valueOf(studentInfo[line].finalMark);
            exportData.add(id);
            exportData.add(studentInfo[line].firstName);
            exportData.add(studentInfo[line].lastName);
            exportData.add(studentInfo[line].finalGrade);
            exportData.add(finalMark);
        }
    }
    public static void swapClassData(int classIndex1, int classIndex2){

        int id;
        String firstName;
        String lastName;
        int finalMark;
        String finalGrade;

        int id_toSwap;
        String firstName_toSwap;
        String lastName_toSwap;
        int finalMark_toSwap;
        String finalGrade_toSwap;

        //Step 1, copy all data from classIndex1 to holding set
        //studentInfo[classIndex1] = new StudentInfo_Class();
        id = studentInfo[classIndex1].id;
        firstName = studentInfo[classIndex1].firstName;
        lastName = studentInfo[classIndex1].lastName;
        finalMark = studentInfo[classIndex1].finalMark;
        finalGrade = studentInfo[classIndex1].finalGrade;

        //Step2, copy all data from classIndex2 to holding set to swap
        //studentInfo[classIndex2] = new StudentInfo_Class();
        id_toSwap = studentInfo[classIndex2].id;
        firstName_toSwap = studentInfo[classIndex2].firstName;
        lastName_toSwap = studentInfo[classIndex2].lastName;
        finalMark_toSwap = studentInfo[classIndex2].finalMark;
        finalGrade_toSwap = studentInfo[classIndex2].finalGrade;

        //Step3, copy all data from toSwap to classIndex1
        //studentInfo[classIndex1] = new StudentInfo_Class();
        studentInfo[classIndex1].id = id_toSwap;
        studentInfo[classIndex1].firstName = firstName_toSwap;
        studentInfo[classIndex1].lastName = lastName_toSwap;
        studentInfo[classIndex1].finalMark = finalMark_toSwap;
        studentInfo[classIndex1].finalGrade = finalGrade_toSwap;

        //Step4, copy all data from holding set to classIndex2
        studentInfo[classIndex2] = new StudentInfo_Class();
        studentInfo[classIndex2].id = id;
        studentInfo[classIndex2].firstName = firstName;
        studentInfo[classIndex2].lastName = lastName;
        studentInfo[classIndex2].finalMark = finalMark;
        studentInfo[classIndex2].finalGrade = finalGrade;
    }
    static <T extends Comparable<T>> T compareSmaller(T x, T y){
        if (x.compareTo(y) < 0){
            return x;
        }else{
            return y;
        }
    }

    //DEBUG
    public static void printDataFromIndex(int index){
        System.out.println(studentInfo[index].id);
        System.out.println(studentInfo[index].firstName);
        System.out.println(studentInfo[index].lastName);
        System.out.println(studentInfo[index].finalMark);
        System.out.println(studentInfo[index].finalGrade);
    }
    public static void printDataFromClass(){
        for (int i = 0; i < 20; i++){
            System.out.println(studentInfo[i].id + studentInfo[i].firstName + studentInfo[i].lastName + studentInfo[i].finalGrade + studentInfo[i].finalMark);
        }
    }

    //MAIN
    public static void main(String[] args) throws IOException{
        inputData = readFile(inputPath);
        importData();
        Sort();
        exportData();
        writeFile(outputPath, exportData, false);
    }
}

class StudentInfo_Class{
    int id;
    String firstName;
    String lastName;
    int finalMark;
    String finalGrade;
}