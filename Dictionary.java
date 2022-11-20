import java.io.*;
import java.util.HashMap;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Dictionary {
    public static ArrayList<String> slangSearched = new ArrayList<String>();
    // public static ArrayList<String> definitionSearched = new ArrayList<String>();

    public static HashMap<String, String> handleFile() {
        HashMap<String, String> dictionary = new HashMap<String, String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
            String t = new String();

            // int num = 1;
            try {
                // handle line in slang.txt
                while (((t = br.readLine()) != null)) {
                    String[] splittedLine = t.split("`");
                    dictionary.put(splittedLine[0], splittedLine[1]);
                    // System.out.print(splittedLine[0]);
                    // System.out.println(" " + splittedLine[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                br.close();
            }

            // System.out.println(dictionary.get("BOMB"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public static void showSearchedSlang() {
        for (String i : slangSearched) {
            System.out.print(i + " ");
        }
        System.out.println('\n');
    }

    public static HashMap<String, String> map = handleFile();

    public static void findSlang() {
        Scanner sc = new Scanner(System.in);
        String slang = new String();
        slang = sc.nextLine();
        String resultF = map.get(slang);
        if (resultF == null) {
            String result = new String();
            for (String i : map.keySet()) {
                if (i.toLowerCase().equals(slang)) {
                    result = slang;
                    slangSearched.add(slang);
                    System.out.println(map.get(i));
                    return;
                }
            }
        } else
            System.out.println(resultF);
    }

    public static void findDef() {
        Scanner sc = new Scanner(System.in);
        String definition = new String();
        definition = sc.nextLine();
        // if (result != null)
        // System.out.println(result);
        for (String i : map.keySet()) {
            if (map.get(i).contains(definition)) {
                System.out.println(i);
            }
        }
    }

    public static void addSlangWord() {
        Scanner sc = new Scanner(System.in);

    }

    public static void main(String[] arg) {
        // findSlang();
        // findDef();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String choice = sc.nextLine();
            if (choice.equals("1")) {
                findSlang();
            } else if (choice.equals("2")) {
                findDef();
            } else if (choice.equals("3")) {
                showSearchedSlang();
            } else {
                sc.close();
                break;
            }
        }
        showSearchedSlang();
    }
}
