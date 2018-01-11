import Bytes.*;

import java.io.IOException;

public class Main{
    public static void main (String[] args)throws IOException{
        Bytes b = new Bytes();
        b.readArays("arrays.in");
        b.show();
        b.printSortedByNames("bytes1.out");
        b.printSortedByElNum("bytes2.out");
        b.printSizes("bytes3.out");
    }


}
