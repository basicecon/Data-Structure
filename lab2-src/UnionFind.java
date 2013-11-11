import java.util.*;

class UnionFind {
  int setCnt = 0;       // the number of set
  ArrayList<Node> allNode; // all orgi arranged by index

  public UnionFind (List<Vertex> vertices) {
    this.allNode = new ArrayList<Node>(vertices.size());
    makeSet(vertices);
  }

  public void makeSet (List<Vertex> vertices) {
    for (Vertex v : vertices) {
      Node n = new Node(0, allNode.size());
      v.setNode(n);
      this.allNode.add(n);
      this.setCnt ++;
    }
  }

  public int find (Node n) {
    Node cur = n;
    while (cur.parent != cur) {
      cur = cur.parent;
    }
    Node root = cur;
    // path compression
    cur = n;
    while (cur != root) {
      Node tmp = cur.parent;
      cur.parent = root;
      cur = tmp;
    }
    return root.index;
  }

  public void union (Node a, Node b) {
    if (find(a) == find(b)) {
      return;
    }
    Node first = this.allNode.get(find(a));
    Node second = this.allNode.get(find(b));

    if (first.rank < second.rank) {
      first.parent = second;
    }
    else if (first.rank > second.rank) {
      second.parent = first;
    }
    else {
      second.parent = first;
      first.rank ++;
    }
    this.setCnt --; 
  }
}