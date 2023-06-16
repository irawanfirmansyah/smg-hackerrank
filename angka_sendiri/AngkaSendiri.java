package angka_sendiri;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

  /*
   * Complete the 'angkaSendiri' function below.
   *
   * The function is expected to return a LONG_INTEGER.
   * The function accepts LONG_INTEGER_ARRAY barisAngka as parameter.
   */

  public static long angkaSendiri(List<Long> barisAngka) {
    Map<Long, Set<Long>> map = new HashMap<Long, Set<Long>>();
    for (int i = 0; i < barisAngka.size(); i++) {
      Long currNum = barisAngka.get(i);
      if (currNum == 0) {
        continue;
      }
      // System.out.println("======= PRINT =======");
      // System.out.println(map);
      // System.out.println(currNum);

      if (map.containsKey(Long.valueOf(1))) {
        Set<Long> set = map.get(Long.valueOf(1));
        if (set.contains(currNum)) {
          // Remove from key 1
          set.remove(currNum);
          if (map.containsKey(Long.valueOf(2))) {
            map.get(Long.valueOf(2)).add(currNum);
            map.put(Long.valueOf(1), set);
          } else {
            Set<Long> newSet = new HashSet<Long>();
            newSet.add(currNum);
            map.put(Long.valueOf(2), newSet);
          }
        } else {
          set.add(currNum);
          map.put(Long.valueOf(1), set);
        }
      } else {
        Set<Long> set = new HashSet<Long>();
        set.add(currNum);

        map.put(Long.valueOf(1), set);
      }
    }
    Set<Long> set = map.get(Long.valueOf(1));
    // System.out.println(map);
    // System.out.println(set);
    if (set.size() <= 0)
      return 0;
    Long[] arr = new Long[set.size()];
    set.toArray(arr);
    return arr[0];
  }

}

public class AngkaSendiri {
  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader("angka_sendiri/input.txt"));
    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("angka_sendiri/output.txt"));

    int N = Integer.parseInt(bufferedReader.readLine().trim());

    String[] barisAngkaTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    List<Long> barisAngka = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      long barisAngkaItem = Long.parseLong(barisAngkaTemp[i]);
      barisAngka.add(barisAngkaItem);
    }

    long answer = Result.angkaSendiri(barisAngka);

    bufferedWriter.write(String.valueOf(answer));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
}
