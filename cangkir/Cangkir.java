package cangkir;

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

  /*
   * Complete the 'tebakCangkir' function below.
   *
   * The function is expected to return an INTEGER.
   * The function accepts following parameters:
   * 1. INTEGER N
   * 2. INTEGER T
   * 3. INTEGER P
   * 4. 2D_INTEGER_ARRAY posisi
   */

  private static void printArr(int[] arr) {
    for (int ii = 0; ii < arr.length; ii++) {
      System.out.print(arr[ii]);
      System.out.print(", ");
    }
    System.out.println("");
  }

  public static int tebakCangkir(int N, int T, int P, List<List<Integer>> posisi) {
    int[] arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = i + 1;
    }

    int ans = arr[P - 1];

    for (int i = 0; i < posisi.size(); i++) {
      List<Integer> curr = posisi.get(i);
      int source = curr.get(0) - 1;
      int dest = curr.get(1) - 1;

      // System.out.println(posisi.get(i));
      // printArr(arr);

      if (source > dest) {
        int temp = arr[source];

        for (int j = source; j > dest; j--) {
          arr[j] = arr[j - 1];
        }

        arr[dest] = temp;
      } else {
        int temp = arr[source];

        for (int k = source; k < dest; k++) {
          arr[k] = arr[k + 1];
        }

        arr[dest] = temp;
      }

      // printArr(arr);
    }

    for (int n = 0; n < arr.length; n++) {
      // System.out.println(arr[n]);
      // System.out.println(ans);
      if (arr[n] == ans) {
        return n + 1;
      }
    }

    return 0;
  }

}

public class Cangkir {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader("cangkir/input.txt"));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("cangkir/output.txt"));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int N = Integer.parseInt(firstMultipleInput[0]);

    int T = Integer.parseInt(firstMultipleInput[1]);

    int P = Integer.parseInt(firstMultipleInput[2]);

    List<List<Integer>> posisi = new ArrayList<>();

    IntStream.range(0, T).forEach(i -> {
      try {
        posisi.add(
            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList()));
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    });

    int result = Result.tebakCangkir(N, T, P, posisi);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
