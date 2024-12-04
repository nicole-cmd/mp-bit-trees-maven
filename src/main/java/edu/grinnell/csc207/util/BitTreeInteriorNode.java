package edu.grinnell.csc207.util;
import edu.grinnell.csc207.util.BitTreeLeaf;

/**
 * Nodes for the children of our bit tree.
 * Interior nodes do not store, but information about
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

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

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
   * Get the direction our node goes.
   * 
   * @return the left/right node created 
   *         based off the bit value (0 or 1).
   */
  public BitTreeNode getDir() {
    if (!(this.right.equals(null))) {
      return this.left;
    } // if

    return this.right;
  } // getDir()

} // class BitTreeInteriorNode
