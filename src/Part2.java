import java.io.File;
import java.util.*;

public class Part2{
    //Input: HashMap of ArrayList of Students
    //Output: Sections
    ArrayList<Student> students = new ArrayList<>();
    HashMap<String, Integer> number = new HashMap<>();
    HashMap<String, Integer> sections = new HashMap<>();
    HashMap<String, ArrayList<Pair>> map = new HashMap<>();
    ArrayList<String> courses = new ArrayList<>();
    double MAX_SIZE = 32;
    public static void main(String[] args) throws Exception{
        Part2 troll = new Part2();
        troll.init(); //number is filled
        troll.ALGORITHM();
    }

    public void init() throws Exception{
        Scanner scan = new Scanner(new File("Students.csv"));
        while (scan.hasNextLine()){
            String[] line = scan.nextLine().split(" ");
            for (int i = 3; i < 11; i++){
                if (number.containsKey(line[i])) number.put(line[i], number.get(line[i])+1);
                else number.put(line[i], 1);
            }
        }
        Scanner scan2 = new Scanner(new File("Courses.csv"));
        while (scan2.hasNextLine()) courses.add(scan2.nextLine());
        for (String s: courses){
            System.out.println(s + " " + number.get(s));
            //Each class is either 1, 2, or 3 sections
            if (number.get(s) <= 32) sections.put(s, 1);
            else if (number.get(s) / 2 <= 32 && number.get(s) / 3 > 32) sections.put(s, 2);
            else sections.put(s, 3);
        }
    }

    public void ALGORITHM(){
        StudentsInput GD = new StudentsInput();
        GD.ReadStudentInput("Students.csv");
        students = GD.StudentInfo;
        for (Student s: students){ //500
            String[] courses = s.choicesMain;
            String[] alts = s.choicesAlt;
            Boolean[] check = new Boolean[8];
            for (int i = 0; i < 8; i++) check[i] = false;
            for (String course: courses){ //8
                ArrayList<Pair> updateSize = map.get(course);
                if (updateSize != null){
                    //Pair: section number + # people inside
                    if (updateSize.size() == 1 && updateSize.get(0).b < number.get(course) / sections.get(course) && !check[updateSize.get(0).a]){
                        //Add person
                        check[updateSize.get(0).a] = true; //Section is occupied
                        updateSize.set(0, new Pair(updateSize.get(0).a, updateSize.get(0).b + 1));
                    }else if (updateSize.size() == 1){
                        //Add new one
                    }else if (updateSize.size() > 1){
                        boolean done = false;
                        for (int i = 0; i < updateSize.size(); i++){ //4
                            Pair p = updateSize.get(i);
                            if (p.b < number.get(course) / sections.get(course) && !check[p.b]){
                                //Fill in first spot
                                check[p.a] = true;
                                updateSize.set(i, new Pair(p.a, p.b + 1));
                                done = true;
                                break;
                            }
                        }
                        if (!done){
                            //Use alts
                        }
                    }
                }else{
                    //Create new section
                    ArrayList<Integer> leftover = new ArrayList<>();
                    for (int i = 0; i < 8; i++) if (!check[i]) leftover.add(i);
                }
            }
        }

    }

    //Population: 500 (125 126 114 135)
    //Courses: 15 17 22 24
    //Size: 25-32
    //Maximum: 125 (including alt) => 4 sections maximum

    /*
    public void RUN() throws Exception{
        Scanner courseS = new Scanner(new File("Courses.csv"));
        while (courseS.hasNextLine()){
            String course = courseS.nextLine();
            if (course.charAt(3) == '1') course9.add(course);
            if (course.charAt(3) == '2') course10.add(course);
            if (course.charAt(3) == '3') course11.add(course);
            if (course.charAt(3) == '4') course12.add(course);
        }

        Part1 lol = new Part1();
        lol.adjList();
        HashMap<String, ArrayList<Student>> list = lol.list;
        int[] perSection = new int[8]; //8 sections
        for (String c: course9){
            ArrayList<Student> students = list.get(c);
            //System.out.println(c + " " + Math.ceil(students.size()/MAX_SIZE));
            map.put(c, (int)Math.ceil(students.size()/MAX_SIZE));
        }
        recursion(new int[8], 0);
    }

    public void recursion(int[] perSection, int index){
        //System.out.println(index);
        ArrayList<Integer> tempIndex = new ArrayList<>();
        for (int i = 0; i < 8; i++){
            if (perSection[i] <= 2){
                tempIndex.add(i);
                // MAGIC NUMBER
            }
        }
        int sections = map.get(course9.get(index));
        if (sections == 1){
            for (int i: tempIndex){
                perSection[i]++;
                if (index + 1 < course9.size()) recursion(perSection, index + 1);
                perSection[i]--;
            }
        }else if (sections == 2){
            ArrayList<Pair> realTemp = shuffle2(tempIndex.size());
            for (Pair lol: realTemp){
                perSection[tempIndex.get(lol.a)]++;
                perSection[tempIndex.get(lol.b)]++;
                if (index + 1 < course9.size()) recursion(perSection, index + 1);
                perSection[tempIndex.get(lol.a)]--;
                perSection[tempIndex.get(lol.b)]--;
            }
        }else if (sections == 3){
            ArrayList<Triple> realTemp = shuffle3(tempIndex.size());
            ArrayList<Triple> realIndex = new ArrayList<>();
            for (Triple lol: realTemp){
                perSection[tempIndex.get(lol.x)]++;
                perSection[tempIndex.get(lol.y)]++;
                perSection[tempIndex.get(lol.z)]++;
                if (index + 1 < course9.size()) recursion(perSection, index + 1);
                perSection[tempIndex.get(lol.x)]--;
                perSection[tempIndex.get(lol.y)]--;
                perSection[tempIndex.get(lol.z)]--;
            }
        }
    }

    public ArrayList<Pair> shuffle2(int size){
        ArrayList<Pair> really = new ArrayList<>();
        for (int i = 0; i < size; i++){
            for (int j = i + 1; j < size; j++){
                really.add(new Pair(i, j));
            }
        }
        return really;
    }

    public ArrayList<Triple> shuffle3(int size){
        ArrayList<Triple> really = new ArrayList<>();
        for (int i = 0; i < size; i++){
            for (int j = i + 1; j < size; j++){
                for (int l = j + 1; l < size; l++){
                    really.add(new Triple(i, j, l));
                }
            }
        }
        return really;
    }
     */
}