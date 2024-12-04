package edu.grinnell.csc207.util;

/**
 * Node for the leaf of our bit tree. Stores our string
 * information.
 * 
 * @author Nicole Gorrell
 */
public class BitTreeLeaf {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The letter our tree yields. */
  String letter;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new leaf in the bit tree with our input letter.
   *
   * @param str
   *   The string to be stored in the bit tree.
   */
  public BitTreeLeaf(String str) {
    this.letter = str;
  } // BitTreeLeaf(String)

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get the string in the node.
   * 
   * @return the string stored in the node.
   */
  public String getLetter() {
    return this.letter;
  } // getLetter()
} // class BitTreeLeaf
