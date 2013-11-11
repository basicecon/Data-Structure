class Vertex {
  Node n;  // pointer
  int value;
  public Vertex () {
    n = null;
    value = 0;
  }

  public void setNode (Node n) {
    this.n = n;
  }

  public Node getNode () {
    return this.n;
  }

  public void setValue (int val) {
    this.value = val;
  }
}