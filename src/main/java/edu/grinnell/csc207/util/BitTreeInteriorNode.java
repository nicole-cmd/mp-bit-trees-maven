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
  public BitTreeInteriorNode(BitTreeNode l, BitTreeNode r) {
    this.left = l;
    this.right = r;
  } // BitTreeInteriorNode(BitTreeNode, BitTreeNode, BitTreeNode)

  /**
   * Create a new node with null branches.
   */
  public BitTreeInteriorNode() {
    this(null, null);
  } // BitTreeInteriorNode()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the left node for traversal.
   *
   * @return the left node.
   */
  public BitTreeNode goLeft() {
    return this.left;
  } // goLeft()

  /**
   * Get the right node for traversal.
   *
   * @return the right node.
   */
  public BitTreeNode goRight() {
    return this.right;
  } // goRight()

} // class BitTreeInteriorNode
