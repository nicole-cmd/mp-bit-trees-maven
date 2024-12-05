package edu.grinnell.csc207.util;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * Trees intended to be used in storing mappings between fixed-length 
 * sequences of bits and corresponding values.
 *
 * @author Nicole Gorrell
 */
public class BitTree implements Iterable<BitTreeNode>{
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The root of the tree. Will be null, */
  BitTreeNode root;

  /** The size of the tree. Will be either 6, 7 or 8. */
  int size;
  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Builds a new bit tree that stores mappings from strings of n
   * bits to strings.
   * 
   * @param n
   *   The number of bits used to map to strings.
   */
  public BitTree(int n) {
    this.size = n;
    this.root = null;
  } // BitTree(int)

  /**
   * Builds a new empty bit tree.
   *
   */
  public BitTree() {
    this.size = 0;
    this.root = null;
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   * Verify all values in a string bit are either 0 or 1.
   * 
   * @param bit
   *   The string bit whose values we are checking.
   */
  private boolean bitCheck(String bit) {
    for (char c : bit.toCharArray()) {
      if (!((c == '0') || (c == '1'))) {
        return false;
      } // if
    } // for

    return true;
  } // bitCheck(String)

  /**
   * Get an iterator for the tree.
   *
   * @return the iterator.
   */
  public Iterator<BitTreeNode> iterator() {
    return new Iterator<BitTreeNode>() {
      int n = 0;
      BitTreeNode next = root;

      @Override
      public boolean hasNext() {
        return (n < size);
      } // hasNext()

      @Override
      public BitTreeNode next() {
        if (!this.hasNext()) {
          throw new NoSuchElementException();
        } // if

        BitTreeNode current = this.next;
        this.next = this.next.getNext();
        ++this.n;
        return current;
      } // next()
    }; // new Iterator()
  } // iterator()

  /**
   * Find the node with the given letter value, or an exception
   * if we cannot find it.
   *
   * @param letter
   * @return the node where the desired value is stored.
   *
   * @throws NoSuchElementException
   */
  @SuppressWarnings("unlikely-arg-type")
  private BitTreeNode find(String letter) throws NoSuchElementException {
    int i = 0;
    Iterator<BitTreeNode> nodes = this.iterator();

    while (i < this.size) {
      BitTreeNode node = nodes.next();
      BitTreeLeaf check = new BitTreeLeaf(letter);
      if (node.getNext().equals(check)) {
        return node;
      } // if

      i++;
    } // while

    throw new NoSuchElementException("Letter does not exist in the tree.");
  } // find(String)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Follows the bit path through the tree to add or replace the
   * value to the end of the tree.
   *
   * @param bits
   *   The bits we use to traverse the tree path.
   * @param value
   *   The value to be added to the tree. 
   */
  public void set(String bits, String value) throws IndexOutOfBoundsException {
    if (bits.length() < 6 || bits.length() > 8 || bitCheck(bits) != true) {
      throw new IndexOutOfBoundsException("Bit length is inappropriate and/or bit"
                                          + " contains invalid values.");
    } // if

    char[] bitArr = bits.toCharArray();
    BitTreeNode current = null;
    for (char c : bitArr) {
      BitTreeNode inNode = new BitTreeInteriorNode(c);
      current = inNode;
    } // for

    if (current.getNext() == null) {
      try {
        current.insertAfter(value);
      } catch (Exception e) {
        // Should not need to do anything
      } // try/catch
    } // if
  } // set(String, String)

  /**
   *
   */
  public String get(String bits) {
    return "";  // STUB
  } // get(String, String)

  /**
   *
   */
  public void dump(PrintWriter pen) {
    // STUB
  } // dump(PrintWriter)

  /**
   *
   */
  public void load(InputStream source) {
    // STUB
  } // load(InputStream)

} // class BitTree
