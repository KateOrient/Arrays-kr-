package Bytes;


import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Bytes{
    private Set<Array> arrays;

    public Bytes (){
        arrays = new TreeSet<Array>(new Comparator<Array>(){
            @Override
            public int compare (Array o1, Array o2){
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public void readArays (String fileName) throws IOException{
        Scanner scanner = new Scanner(new File(fileName)).useDelimiter(";");
        Array arr;
        while (scanner.hasNext()){
            arr = new Array();
            arr.readArray(scanner.next());
            arrays.add(arr);
        }
        scanner.close();
    }

    public void show (){
        for (Array item : arrays){
            item.print();
        }
    }

    public void printSortedByNames (String fileName) throws IOException{
        PrintStream ps = new PrintStream(fileName);
        Iterator it = arrays.iterator();
        while (it.hasNext()){
            ps.println(((Array)it.next()).getName());
        }
        ps.close();
    }

    public void printSortedByElNum (String fileName) throws IOException{
        PrintStream ps = new PrintStream(fileName);
        for (Array item : arrays.stream().sorted(new MyComparator()).collect(Collectors.toList())){
            ps.println(item.getName() + " - " + item.getElementNum());
        }
        ps.close();
    }

    private class MyComparator implements Comparator<Array>{
        @Override
        public int compare (Array o1, Array o2){
            return o2.getElementNum() - o1.getElementNum();
        }
    }

    public void printSizes (String fileName) throws IOException{
        PrintStream ps = new PrintStream(fileName);
        for(Array item:arrays){
            ps.println(item.getDeclaration()+" - "+item.countMemory());
        }
    }
}
