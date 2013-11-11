import java.util.*;
import java.io.*;

class MSTKruskal {
  static int num_vertex;
  static int num_edges;
  static UnionFind d;
  static ArrayList<Vertex> vertices;
  static List<Edge> edges;
  static ArrayList<Edge> tree;
  static ArrayList<String> result;
  static Scanner scan;

  public static void main (String args[]) {
    //min_mt();
    two_msts();
  }

  public static void init () {
    vertices = new ArrayList<Vertex>();
    edges = new ArrayList<Edge>();
  }

  public static void init_kruskal () {
    tree = new ArrayList<Edge>();
    result = new ArrayList<String>();
  } 

  public static void output () {
    Collections.sort(result);
    for (String str : result) {
      System.out.println(str);
    }       
  } 

  public static void min_mt () {
    scan = null;
    try {
      scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    } catch(Exception e) {}
    while (true) {
      init();
      read();
      int sum = kruskal();
      System.out.println(sum);
      output();
      int flag = scan.nextInt();
      if (flag == 1)
        break;
      else
        System.out.println(0);
    } 
  }

  public static void read () {
    num_vertex = scan.nextInt();
    num_edges = scan.nextInt();
    for (int i = 1; i <= num_vertex; i ++) {
      Vertex v = new Vertex();
      v.setValue(i);
      vertices.add(v);
    }

    for (int i = 0; i < num_edges; i ++) {
      int u = scan.nextInt();
      int v = scan.nextInt();
      int w = scan.nextInt();
      Vertex uu = vertices.get(u-1);
      Vertex vv = vertices.get(v-1);
      Edge e = new Edge(uu, vv);
      e.setWeight(w);
      edges.add(e);
    }
  }

  public static int kruskal () {
    init_kruskal();
    int sum = 0;
    Collections.sort(edges);
    d = new UnionFind(vertices);
    for (Edge e : edges) {
      Vertex u = e.getU();
      Vertex v = e.getV();
      if (d.find(u.getNode()) != d.find(v.getNode())) {
        int i = u.getNode().index + 1;
        int j = v.getNode().index + 1;
        String str = i + " " + j;
        result.add(str);
        tree.add(e);
        d.union(u.getNode(), v.getNode());
      }
    }
    for (int i = 0; i < tree.size(); i ++) {
    //for (Edge e : tree) {
    //using iterator: "ConcurrentModificationException"
      Edge e = tree.get(i);
      sum += e.getWeight();
    }
    return sum;
  }

  public static void find_mst () {
    int tmp_sum = kruskal();
    System.out.println(tmp_sum);
    output();
    ArrayList<Edge> forest = new ArrayList<Edge>(tree); //deep copy

    for (int i = 0; i < forest.size(); i ++) {
    //for (Edge e : tree) { 
    //using iterator: "ConcurrentModificationException"
      Edge e = forest.get(i);
      int tmp_w = e.getWeight();
      e.setWeight(99999); 
      int s = kruskal();
      if (s == tmp_sum) { 
        System.out.println(2);
        output();
        break;
      }
      e.setWeight(tmp_w);
    }
  }

  public static void two_msts () {
    scan = null;
    try {
      scan = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    } catch(Exception e) {}
    while (true) {
      init();
      read();
      find_mst();

      int flag = scan.nextInt();
      if (flag == 1)
        break;
      else
        System.out.println(0);
    }
  }
}