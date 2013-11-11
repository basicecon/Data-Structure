class Edge implements Comparable<Edge> {
  Vertex u;
  Vertex v;
  int weight;

  public Edge (Vertex u, Vertex v) {
    this.u = u;
    this.v = v;
    weight = 0;
  }

  public int getWeight () {
    return this.weight;
  }

  public void setWeight (int w) {
    this.weight = w;
  } 

  public Vertex getU () {
    return this.u;
  }

  public Vertex getV () {
    return this.v;
  }

  public int compareTo (Edge obj) {
    Edge para = obj;
    if (this.getWeight() < para.getWeight()) {
      return -1;
    }
    else if (this.getWeight() > para.getWeight()) {
      return 1;
    }
    else {
      return 0;
    }
  }
}