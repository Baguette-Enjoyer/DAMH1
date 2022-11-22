import java.io.*;
// import java.awt.*;
// import javax.swing.*;
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
        slangSearched.add(slang);
        String resultF = map.get(slang);
        if (resultF != null) {
            System.out.println(resultF);
        } else
            System.out.println("Slang not found");
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

    public static void deleteSlang() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter slang to delete: ");
        String slangToDel = sc.nextLine();
        if ((map.get(slangToDel)) != null) {
            System.out.println("Confirm (Y/N)");
            String choice = sc.nextLine();
            if (choice.equals("N")) {
                return;
            } else {
                try {
                    map.remove(slangToDel);
                    BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
                    StringBuffer sb = new StringBuffer();
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.split("`")[0].equals(slangToDel)) {
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
            }

        } else {
            System.out.println("Slang Not Found ");
            return;
        }
    }

    public static void resetDictionary() {
        try {
            map.clear();
            BufferedReader br = new BufferedReader(new FileReader("orgSlang.txt"));
            StringBuffer sb = new StringBuffer();
            String t;
            while ((t = br.readLine()) != null) {
                String[] line = t.split("`");
                map.put(line[0], line[1]);
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
    }

    public static void randomSlang() {
        Random generator = new Random();
        Object[] values = map.keySet().toArray();// tham khao
                                                 // https://stackoverflow.com/questions/929554/is-there-a-way-to-get-the-value-of-a-hashmap-randomly-in-java
        int t = generator.nextInt(values.length);
        String randomValue = values[t].toString() + ": " + map.get(values[t]);
        System.out.println(randomValue);
    }

    public static void randomSlangQuiz() {
        Random generator = new Random();
        // To Array Dictionary
        Object[] values = map.keySet().toArray();
        //
        int quest = generator.nextInt(values.length);
        String ansr = map.get(values[quest]);
        ArrayList<Integer> list = new ArrayList<Integer>();// prevent same
        ArrayList<String> dummyAnsr = new ArrayList<String>();
        System.out.print("Slang: " + values[quest].toString() + '\n');
        list.add(quest);
        String answer[] = ansr.split("\\| ");
        dummyAnsr.add(answer[generator.nextInt(answer.length)]);
        int loop = 0;
        while (loop < 3) {
            int key = generator.nextInt(values.length);
            for (int i : list) {
                if (key == i) {
                    break;
                } else {
                    list.add(key);
                    String ans = map.get(values[key]);
                    String[] anStr = ans.split("\\| ");
                    if (anStr.length == 1) {
                        dummyAnsr.add(ans);
                        loop++;
                        break;
                    } else {
                        dummyAnsr.add(anStr[generator.nextInt(anStr.length)]);
                        loop++;
                        break;
                    }
                }
            }
        }
        loop = 0;
        Collections.shuffle(dummyAnsr);
        for (String i : dummyAnsr) {
            System.out.println(Integer.toString(++loop) + " " + i);
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose Answer: ");
        String decision = sc.nextLine();
        if (!decision.equals("1") && !decision.equals("2") && !decision.equals("3") && !decision.equals("4")) {
            System.out.println("Wrong");
        } else {
            loop = 1;

            for (String i : dummyAnsr) {
                String l = Integer.toString(loop);
                if (!l.equals(decision)) {
                    loop++;
                    continue;
                }
                if (l.equals(decision) && !i.equals(ansr)) {
                    System.out.println("Wrong");
                    return;
                }
                if (l.equals(decision) && ansr.contains(i)) {
                    System.out.println("TRUE");
                    return;
                }

            }
        }
        // System.out.println(dummyAnsr);
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
            } else if (choice.equals("6")) {
                deleteSlang();
            } else if (choice.equals("7")) {
                resetDictionary();
            } else if (choice.equals("8")) {
                randomSlang();
            } else if (choice.equals("9")) {
                randomSlangQuiz();
            } else {
                sc.close();
                break;
            }
        }
        showSearchedSlang();
    }
}
