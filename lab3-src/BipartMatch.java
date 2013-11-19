import java.util.*;
import java.io.*;

class BipartMatch {
  static int MAX = 102;
  static int x_set;
  static int y_set;
  static int num_edges;
  static int matching;
  static int[][] adj_list;
  static int match[];
  static Scanner scan = null; 
  static ArrayList<String> edges;

  public static void main (String args[]) {
    BipartMatch();
  }
	
	public static void output () {
	  for (int i = 1; i <= x_set; i ++) {
      if (bfs(i)) {
        matching ++;
      }
    }
    System.out.println(matching);
    for (int i = 1+x_set; i <= x_set + y_set; i ++) {
      if (i != 0 && match[i] != 0)
        edges.add(match[i] + " " + i);
    }
    Collections.sort(edges);
    for (String str : edges)
      System.out.println(str);
	}
	
	public static void init () {
	  matching = 0;
	  adj_list = new int[MAX][MAX];
	  match = new int[MAX];
	  edges = new ArrayList<String>();
	}

  public static void BipartMatch () {
    scan = null;
    try {
      scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    } catch(Exception e) {}
    while (true) {
      init();
      read();  
      output();
      int flag = scan.nextInt();
      if (flag == 1)
        break;
      else
        System.out.println(0);
    }
  }
	
  public static void read () {
    x_set = scan.nextInt();
    y_set = scan.nextInt();
    num_edges = scan.nextInt();

    for (int i = 1; i <= num_edges; i ++) {
      int x = scan.nextInt();
      int y = scan.nextInt();
      adj_list[x][ ++adj_list[x][0] ] = y;
    }
  }

  public static boolean bfs (int src) {
    ArrayList<Integer[]> queue = new ArrayList<Integer[]>();
    Integer[] s = new Integer[2];
    s[0] = src; 
    s[1] = -1;
    queue.add(s);
    int index = 0;
    
    boolean[] used = new boolean[MAX];
    Arrays.fill(used, false);
    while (index < queue.size()) {

      Integer[] path = queue.get(index);
      int node = path[0];
      // check if left or right
      if (node <= x_set)
        for (int i = 1; i <= adj_list[node][0]; i ++) {
          int y = adj_list[node][i];
          if (used[y])
            continue;
          Integer[] tmp = new Integer[2];
          tmp[0] = y;
          tmp[1] = index;
          queue.add(tmp);
          used[y] = true;
        }
      else {
        if (match[node] == 0) {
          break;
        }
        else {
          if (!used[match[node]]) {
            Integer[] tmp = new Integer[2];
            tmp[0] = match[node];
            tmp[1] = index;
            queue.add(tmp);
            used[match[node]] = true;  
          }          
        }
      }
      index ++;
    }

    // find path 
    Integer[] last_pair = queue.get(queue.size()-1); 
    int last = last_pair[0]; 
    if (match[last] != 0 || last <= x_set)
      return false;

    ArrayList<Integer> result = new ArrayList<Integer>();
    int father = last_pair[1];
    result.add(last_pair[0]);
    while (father != -1) {
      last_pair = queue.get(father);
      result.add(last_pair[0]);
      father = last_pair[1];
    }

    // update match status
    for (int i = 0; i < result.size()-1; i += 2) {
      match[result.get(i)] = result.get(i+1);
    }
    return true;
  }
}