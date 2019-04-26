package rockets.model;

import java.util.*;
import java.io.*;

public class FileManager {

    public FileManager()
    { }

    /**
     * Method readManufacturerInfo is to read information of all Manufacturers
     *
     */
    public static ArrayList<LaunchServiceProvider> readManufacturerInfo(String fileName)
    {
        ArrayList<LaunchServiceProvider> manufacturerList = new ArrayList<LaunchServiceProvider> ();
        try
        {
            FileReader inputFile = new FileReader(fileName);
            try
            {
                Scanner parser = new Scanner(inputFile);
                while(parser.hasNextLine())
                {
                    String str = parser.nextLine();
                    String[] parts = str.split(",");

                    String name = parts[0];
                    int yearFounded = Integer.parseInt(parts[1]);
                    String country = parts[2];

                    LaunchServiceProvider manufacturer = new LaunchServiceProvider(name, yearFounded,country);
                    manufacturerList.add(manufacturer);
                }
            }
            finally
            {
                inputFile.close();
            }
        }
        catch(FileNotFoundException exception)
        {
            System.out.println(fileName + " not found.");
        }
        catch(IOException exception)
        {
            System.out.println("Unexpected I/O exception occurs.");
        }
        return manufacturerList;
    }




    /**
     * Method writeFile is to write the content of an Arraylist of String to a text file
     *
     * @param content: the Arraylist of String we are going to write to the text file
     * @param fileName: the name of the text file the method writes to
     */
    public static void writeFile(ArrayList<String> content,String fileName)
    {
        //String file = fileName;
        try
        {
            BufferedWriter outputFile;
            outputFile = new BufferedWriter(new FileWriter(fileName, true));


            for(String str: content){
                outputFile.write(str);
                outputFile.newLine();

            }
            outputFile.close();
        }


        catch(IOException e)
        {
            System.out.println("Unexpected I/O exception occurs.");
        }
    }


}
