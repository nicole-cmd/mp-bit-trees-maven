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
   * Get the value of this node..
   * 
   * @return null.
   */
  public String getValue();

  /**
   * Get the next node.
   *
   * @return the following node.
   */
  public BitTreeNode getNext();

} // interface BitTreeNode
