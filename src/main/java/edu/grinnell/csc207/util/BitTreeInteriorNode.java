package edu.grinnell.csc207.util;

/**
 * Nodes for the children of our bit tree.
 * Interior nodes do not store values, but information about
 * the children of each node.
 * 
 * @author Nicole Gorrell
 */
public class BitTreeInteriorNode implements BitTreeNode {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** Left (0) subtree. */
  BitTreeNode left;

  /** Right (1) subtree. */
  BitTreeNode right;

  /** The next node (next level). */
  BitTreeNode next;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new node.
   *
   * @param l
   *   The left child.
   * @param r
   *   The right child.
   */
  public BitTreeInteriorNode(BitTreeNode l, BitTreeNode r, 
                             BitTreeNode nextNode) {
    this.left = l;
    this.right = r;
    this.next = nextNode;
  } // BitTreeInteriorNode(BitTreeNode, BitTreeNode, BitTreeNode)

  /**
   * Create a new node with null branches.
   */
  public BitTreeInteriorNode() {
    this(null, null, null);
  } // BitTreeInteriorNode()

  /**
   * Create a new node that branches based on the bit value.
   *
   * @param l
   *   The left child.
   */
  public BitTreeInteriorNode(int bit) {
    if (bit > 0) {
      this.right = null;
    } else {
      this.left = null;
    } // if/else
  } // BitTreeInteriorNode(int)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the node.
   * 
   * @return the left/right node created.
   */
  public BitTreeNode getNode() {
    if (!(this.right.equals(null))) {
      return this.left;
    } // if

    return this.right;
  } // getDir()

  /**
   * Get the direction of the node.
   * 
   * @return the value of the node 
   *         based off the bit value (0 or 1).
   */
  public int getDir() {
    if (!(this.right.equals(null))) {
      return 0;
    } // if

    return 1;
  } // getDir()

  /**
   * Get the next node.
   *
   * @return the next node, reflecting the
   *         next level.
   */
  public BitTreeNode getNext() {
    return this.next;
  } // getNext()

  /**
   * Insert a new value after this node. Or, create
   * a new leaf when we're at the end of the tree.
   *
   * @param str
   *   The letter we want to insert.
   *
   * @return the new leaf node.
   * @throws Exception if we are not end of the tree.
   */
  public BitTreeLeaf insertAfter(String str) throws Exception {
    BitTreeLeaf leaf;
    if (this.next == null) {
      leaf = new BitTreeLeaf(str);
      return leaf;
    } else {
      throw new Exception("Cannot insert leaf here; we are"
                          +  " not at the end of the tree.");
    } // if/else
  } // insertAfter(String)

  /**
   * Determine which direction we go based on the bit we read in.
   * 
   * @param bit
   *   The bit we read.
   * @return the node we create based on the bit; determines our
   *         direction in the tree.
   */
  public BitTreeNode findDir(char bit) {
    return new BitTreeInteriorNode(bit);
  } // findDir(char)

} // class BitTreeInteriorNode
