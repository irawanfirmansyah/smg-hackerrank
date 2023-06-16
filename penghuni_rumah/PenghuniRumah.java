package penghuni_rumah;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class HomeNode {
  Map<String, String> data;
  HomeNode left;
  HomeNode right;

}

class Result {

  /**
   * name {
   * joni: node1
   * sinta: node2
   * jojo: node3
   * }
   * 
   * number {
   * 1:node1
   * 2:node2
   * 3:node3
   * }
   * 
   * color {
   * red:node1
   * blue:node2
   * green:node3
   * }
   * 
   */

  /*
   * Complete the 'perumahan' function below.
   *
   * The function is expected to return a STRING.
   * The function accepts following parameters:
   * 1. INTEGER N
   * 2. STRING_ARRAY situasi
   */

  // private static Map<String, Map<String, HomeNode>> myMap;

  private static OptionalInt getNodePosFromList(ArrayList<HomeNode> nodes, String attr, String val) {
    return IntStream.range(0, nodes.size())
        .filter(i -> nodes.get(i).data.containsKey(attr) && nodes.get(i).data.get(attr) == val)
        .findFirst();
  }

  public static String perumahan(int N, List<String> situasi) {
    // Write your code here
    ArrayList<HomeNode> nodes = new ArrayList<HomeNode>();

    for (int i = 0; i < N; i++) {
      nodes.add(null);
    }

    situasi.forEach(s -> {
      String[] split = s.split(" ");
      String attr = split[2];
      String val = split[3];

      if (s.contains("tinggal di sebelah")) {
        HomeNode node = null;
        HomeNode neighborNode = null;

        String position = split[7];
        String neighborAttr = split[10];
        String neighborVal = split[11];

        OptionalInt index = getNodePosFromList(nodes, attr, val);
        OptionalInt neighborIndex = getNodePosFromList(nodes, neighborAttr, neighborVal);

        if (index.isPresent()) {
          node = new HomeNode();
        }
        if (!index.isPresent()) {
          node = nodes.get(index.getAsInt());
        }

        if (neighborIndex.isPresent()) {
          neighborNode = new HomeNode();
        } else {
          neighborNode = nodes.get(neighborIndex.getAsInt());
        }

        node.data.put(attr, val);
        neighborNode.data.put(neighborAttr, neighborVal);

        if (position == "kiri") {
          node.right = neighborNode;
          neighborNode.left = node;
        }
        if (position == "kanan") {
          node.left = neighborNode;
          neighborNode.right = node;
        }

      } else {
        HomeNode node = null;
        OptionalInt index = getNodePosFromList(nodes, attr, val);
      }

    });

    return "";
  }

}

public class PenghuniRumah {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader("penghuni_rumah/input.txt"));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("penghuni_rumah/output.txt"));

    int N = Integer.parseInt(bufferedReader.readLine().trim());

    int T = Integer.parseInt(bufferedReader.readLine().trim());

    List<String> situasi = IntStream.range(0, T).mapToObj(i -> {
      try {
        return bufferedReader.readLine();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    })
        .collect(toList());

    String result = Result.perumahan(N, situasi);

    bufferedWriter.write(result);
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
