package edu.grinnell.csc207.util;

/**
 * Nodes for our bit tree.
 * 
 * @author Nicole Gorrell
 */
public interface BitTreeNode {
  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the node.
   * 
   * @return the left/right node created.
   */
  public BitTreeNode getNode();

  /**
   * Get the direction our node goes.
   * 
   * @return the value of the node 
   *         based off the bit value (0 or 1).
   */
  public int getDir();

  /**
   * Get the next node.
   *
   * @return the next node, reflecting the
   *         next level.
   */
  public BitTreeNode getNext();

  /**
   * Insert a new value after this node. Create
   * a new leaf when we're at the end of the tree.
   *
   * @param str
   *   The letter we want to insert.
   *
   * @return the new leaf node.
   * @throws Exception if we are not end of the tree.
   */
  public BitTreeLeaf insertAfter(String str) throws Exception;

  /**
   * Go left if we read in a 0 bit.
   * 
   * @param bit
   *   The bit we read.
   * @return the left node we create.
   */
  public BitTreeNode findDir(char bit);

} // interface BitTreeNode
