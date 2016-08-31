
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class CSVEinlesen {

    public static void main(String[] args) {

        CSVEinlesen obj = new CSVEinlesen();
        obj.run();

    }

    LinkedList<String> list = new LinkedList<>();

    public void run(){

        String csvFile = "customers.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        //GET GERMAN PLZ
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                if (country[1].contains(" Germany")) {
                    list.add(country[0]);
                }

            }
            System.out.println(list.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String output = "var data = { \"count\": 10,\n \"photos\": [";
        for(int i=0; i<list.size()-1; i++){
            output+="{\"customerPLZ\": "+list.get(i)+"}\n" +",";
        }
        output+="{\"customerPLZ\": "+list.get(list.size()-1)+"}\n" +"]}";
        try(  PrintWriter out = new PrintWriter( "data.json" )  ){
        out.println(output);
    } catch (FileNotFoundException e) {
        System.out.println("File not found");
    }
        System.out.println("Done");
    }

}
