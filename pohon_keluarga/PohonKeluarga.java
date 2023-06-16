package pohon_keluarga;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


class Result {

    private class FamilyNode {
        public String name;
        public String bornDate;
		public String parentName;

		public FamilyNode(String name, String bornDate, String parentName){
			this.name = name;
            this.bornDate = bornDate;
			this.parentName = parentName;
		}
    }

    private class Generation{
		public ArrayList<FamilyNode> nodes;

		public Generation(){
			nodes =  new ArrayList<FamilyNode>();
		}

		// public Generation(String name, String parent){
		// 	nodes =  new ArrayList<FamilyNode>();
		// 	nodes.add(0, new FamilyNode(name, parent));
		// }

		public Generation(ArrayList<FamilyNode> children){
			nodes = new ArrayList<FamilyNode>();
			nodes.addAll(children);
		}
		// public void addNode(String name, String parent){
		// 	nodes.add(new FamilyNode(name, parent));
		// }

		public boolean areChildren(ArrayList<FamilyNode> parent){
			for(FamilyNode child : nodes){
				for(FamilyNode par : parent) {
					if(child.parentName.equals(par.name)){
						return true;
					}
				}
			}
			return false;
		}
	}
    

    private ArrayList<Generation> genTree;


    /*
     * Complete the 'daftar_keluarga' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER N
     *  2. STRING_ARRAY list
     */

    public static void daftar_keluarga(int N, List<String> list) {
        for(int i=0; i<list.size(); i++) {
            
        }
    }

    /*
     * Complete the 'cari' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING query as parameter.
     */

    public static String cari(String query) {
        return "";
    }

}

public class PohonKeluarga {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("input.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        int N = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> list = IntStream.range(0, N).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        Result.daftar_keluarga(N, list);

        int Q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, Q).forEach(QItr -> {
            try {
                String query = bufferedReader.readLine();

                String hasil = Result.cari(query);

                bufferedWriter.write(hasil);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
