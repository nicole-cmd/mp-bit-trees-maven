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
   * Get the direction our node goes.
   * 
   * @return the left/right node created 
   *         based off the bit value (0 or 1).
   */
  public BitTreeNode getDir();

} // interface BitTreeNode
