package project;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class BirthdayExample {

  //
  // Func: ReadJSONFile
  // Desc: Reads a json file storing an array and returns an object
  // that can be iterated over
  //
  public static JSONArray ReadJSONArrayFile(String fileName) {
    //
    // read the birthday.json file and iterate over it
    //

    // JSON parser object to parse read file
    JSONParser jsonParser = new JSONParser();

    JSONArray birthdayList = null;

    try (FileReader reader = new FileReader(fileName)) {
      // Read JSON file
      Object obj = jsonParser.parse(reader);

      birthdayList = (JSONArray) obj;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return birthdayList;
  }

  public static void main(final String[] args) {
    HashMap<String, String> birthdays = new HashMap<>();

    //
    // reads a json data file
    //

    String pathToFile = "birthday.json";

    JSONArray jsonData = ReadJSONArrayFile(pathToFile);

    // loop over list
    String name;
    String birthday;
    JSONObject obj;
    for (Integer i = 0; i < jsonData.size(); i++) {
      // parse the object and pull out the name and birthday
      obj = (JSONObject) jsonData.get(i);
      birthday = (String) obj.get("birthday");
      name = (String) obj.get("name");

      birthdays.put(name, birthday);
    }

    // get user input
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a name: ");
    name = input.nextLine();

    if(birthdays.containsKey(name)) {
        System.out.println(name + "'s birthday is " + birthdays.get(name));
    } else {
        System.out.println(name + "'s birthday not found");
    }

    // close the scanner
    input.close();
  }
}