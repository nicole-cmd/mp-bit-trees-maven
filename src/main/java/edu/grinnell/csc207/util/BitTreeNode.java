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
   * Get the left node for traversal.
   *
   * @return the left node.
   */
  public BitTreeNode goLeft();

  /**
   * Get the right node for traversal.
   *
   * @return the left node.
   */
  public BitTreeNode goRight();

} // interface BitTreeNode
