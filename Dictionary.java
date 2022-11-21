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
            System.out.println("Slang not found");
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
        String slangToAdd = new String();
        System.out.println("New Slang: ");
        slangToAdd = sc.nextLine();
        System.out.println("Definition: ");
        String def = sc.nextLine();
        String s;
        if ((s = map.get(slangToAdd)) != null) {
            // neu trung
            System.out.println("Slang already exist !!!");
            System.out.println("Overwrite / Duplicate (1/2) ");
            String choice = sc.nextLine();
            if (choice.equals("1")) {
                map.put(slangToAdd, def);
                try {
                    BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
                    StringBuffer sb = new StringBuffer();
                    String t;
                    while ((t = br.readLine()) != null) {
                        if (t.split("`")[0].equals(slangToAdd)) {
                            sb.append(slangToAdd + '`' + def);
                            sb.append('\n');
                            continue;
                        }
                        sb.append(t);
                        sb.append('\n');
                    }
                    br.close();
                    String toS = sb.toString();
                    BufferedWriter bw = new BufferedWriter(new FileWriter("slang.txt"));
                    bw.write(toS);
                    bw.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (choice.equals("2")) {
                return;
            }
        } else {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("slang.txt", true));

                map.put(slangToAdd, def);
                bw.write(slangToAdd + '`' + def + '\n');
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void editSlangWord() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Edit slang: ");
        String slangToEdit = sc.nextLine();
        if ((map.get(slangToEdit)) != null) {
            System.out.println("Edit to: ");
            String newSlang = sc.nextLine();
            String t = map.get(slangToEdit);
            map.remove(slangToEdit);
            map.put(newSlang, t);
            try {
                BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
                StringBuffer sb = new StringBuffer();
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.split("`")[0].equals(slangToEdit)) {
                        line = newSlang + "`" + line.split("`")[1];
                        sb.append(line);
                        sb.append('\n');
                        continue;
                    }
                    sb.append(line);
                    sb.append('\n');
                }
                br.close();
                String toS = sb.toString();
                BufferedWriter bw = new BufferedWriter(new FileWriter("slang.txt"));
                bw.write(toS);
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Slang Not Found ");
            return;
        }
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
            } else if (choice.equals("4")) {
                addSlangWord();
            } else if (choice.equals("5")) {
                editSlangWord();
            } else {
                sc.close();
                break;
            }
        }
        showSearchedSlang();
    }
}
