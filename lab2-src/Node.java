class Node {
  int rank;
  int index;
  Node parent;

  public Node (int r, int i) {
    rank = r;
    index = i;
    parent = this;
  }
}