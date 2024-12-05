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

  /** The value of the interior node. Will always be null. */
  String val = null;

  /** Left (0) subtree. */
  BitTreeNode left;

  /** Right (1) subtree. */
  BitTreeNode right;

  /** The next node. */
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
  public BitTreeInteriorNode(BitTreeNode l, BitTreeNode r, BitTreeNode n) {
    this.left = l;
    this.right = r;
    this.next = n;
  } // BitTreeInteriorNode(BitTreeNode, BitTreeNode, BitTreeNode)

  /**
   * Create a new node with null branches.
   */
  public BitTreeInteriorNode() {
    this(null, null, null);
  } // BitTreeInteriorNode()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the value of this node..
   * 
   * @return null.
   */
  public String getValue() {
    return this.val;
  } // getValue()

  /**
   * Get the next node.
   *
   * @return the following node.
   */
  public BitTreeNode getNext() {
    return this.next;
  } // getNext()

  /**
   * 
   * @return
   */
  public BitTreeNode goLeft() {
    return this.left;
  }

  public BitTreeNode goRight() {
    return this.right;
  }
} // class BitTreeInteriorNode
