package Bytes;

import java.util.*;

public class Array{
    private String declaration;
    private String type;
    private String name;
    private List<Integer> sizes;

    public Array (){
        sizes = new ArrayList<>();
    }

    public void readArray (String line){
        declaration = line;
        StringTokenizer st = new StringTokenizer(line, " [];,\n\r");
        type = st.nextToken();
        name = st.nextToken();
        st.nextToken();
        String str = st.nextToken();
        if (str.equals("new")){
            st.nextToken();
            while (st.hasMoreTokens()){
                sizes.add(Integer.parseInt(st.nextToken()));
            }
        }
        else{
            int num = 1;
            while (st.hasMoreTokens()){
                st.nextToken();
                num++;
            }
            sizes.add(num);
        }
    }

    public String getType (){
        return type;
    }

    public String getName (){
        return name;
    }

    public int getElementNum (){
        int num=1;
        for(int item:sizes){
            num*=item;
        }
        return num;
    }

    public void print (){
        StringBuilder sb = new StringBuilder(type);
        sb.append(";").append(name).append(";");
        for (int item : sizes){
            sb.append(item).append(" ");
        }
        System.out.println(sb.toString());
    }

    public long countMemory(){
        long memory = 0;
        byte typeSize=0;

        switch (type){
            case "byte":
                typeSize = 1;
                break;
            case "short":
                typeSize = 2;
                break;
            case "int":
                typeSize = 4;
                break;
            case "long":
                typeSize = 8;
                break;
            case "float":
                typeSize = 4;
                break;
            case "double":
                typeSize = 8;
                break;
            case "char":
                typeSize = 2;
                break;
        }

        if(sizes.size()==1){
            memory+=typeSize*sizes.get(0)+28;
            memory+=8-memory%8;
        }
        else {
            int indx = sizes.size()-1;
            long tmpsize=typeSize;
            while(indx>=0){
                tmpsize=sizes.get(indx)*tmpsize+28;
                tmpsize+=8-tmpsize%8;
                indx--;
            }
            memory+=tmpsize;
        }
        return memory;
    }

    public String getDeclaration (){
        return declaration;
    }
}
