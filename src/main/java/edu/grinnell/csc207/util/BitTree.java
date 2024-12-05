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
public class BitTree implements Iterable<BitTreeNode> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The root of the tree. Will be null. */
  BitTreeNode root;

  /** The size of the tree. Will be either 6, 7 or 8. */
  int size;

  /** The leaf of the tree that contains a value. */
  BitTreeLeaf leaf;

  /** The next node, or level, in the tree. */
  BitTreeNode next;

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
    setNull(this.root, this.leaf);
    setInterior(n);
  } // BitTree(int)

  /**
   * Builds a new empty bit tree.
   *
   */
  public BitTree() {
    this(0);
    setNull(this.root, this.leaf);
    setInterior(0);
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   * Set tree root and next node fields to null.
   *
   * @param r
   *   Tree root.
   * @param l
   *   The leaf in the tree.
   */
  private void setNull(BitTreeNode r, BitTreeLeaf l) {
    r = null;
    l = null;
  } // setNull(BitTreeNode, BitTreeNode)

  /**
   * Set next nodes.
   *
   * @param bitSize
   *   How many next nodes we will have.
   *
   */
  private void setInterior(int bitSize) {
    this.next = this.root;
    for (int i = 0; i < bitSize; i++) {
      this.next = new BitTreeInteriorNode();
    } // for
  } // setInterior(int)

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
   * See if there is a next node in the tree.
   *
   * @return true if there is a next node; false if not.
   */
  public boolean containsNext() {
    BitTreeNode check = new BitTreeInteriorNode();
    if (!(this.next.getNode().equals(check))) {
      return false;
    } // if

    return true;
  } // containsNext()

  // /**
  //  * Find the node with the given letter value, or an exception
  //  * if we cannot find it.
  //  *
  //  * @param letter
  //  *
  //  * @return the node where the desired value is stored.
  //  * @throws NoSuchElementException
  //  */
  // @SuppressWarnings("unlikely-arg-type")
  // private BitTreeNode find(String letter) throws NoSuchElementException {
  //   int i = 0;
  //   Iterator<BitTreeNode> nodes = this.iterator();

  //   while (i < this.size) {
  //     BitTreeNode node = nodes.next();
  //     BitTreeLeaf check = new BitTreeLeaf(letter);
  //     if (node.getNext().equals(check)) {
  //       return node;
  //     } // if

  //     i++;
  //   } // while

  //   throw new NoSuchElementException("Letter does not exist in the tree.");
  // } // find(String)

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
  // private BitTreeLeaf insertAfter(String str) throws Exception {
  //   BitTreeLeaf leaf;
  //   Iterator<BitTreeNode> n = this.iterator();

  //   while (n.hasNext()) {
  //     if (n.next() == null) {
  //       leaf = new BitTreeLeaf(str);
  //       return leaf;
  //     } // if
  //   } // while
  //   throw new Exception("Cannot insert leaf here; we are"
  //                           +  " not at the end of the tree.");
  // } // insertAfter(String)

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
   *
   * @throws IndexOutofBoundsException if bits isn't an appropriate
   *                                   length or doesn't contain
   *                                   valid bit values.
   */
  public void set(String bits, String value) throws IndexOutOfBoundsException {
    if (bits.length() < 6 || bits.length() > 8 || bitCheck(bits) != true) {
      throw new IndexOutOfBoundsException("Bit length is inappropriate and/or bit"
                                          + " contains invalid values.");
    } // if

    char[] bitArr = bits.toCharArray();
    int b = 0; // index for bitArr

    //if (!(current.getNext()))
    Iterator<BitTreeNode> nodes = this.iterator();
    while (nodes.hasNext()) {
      if (bitArr[b] == '0') {
        nodes.next().getNode() = goLeft();
      } else {
        nodes.next().getNode() = goRight();
      }
      b++;
    } // while

    this.leaf = new BitTreeLeaf(value);
  
  } // set(String, String)

  /**
   * Follows the path provided by input bits to get the value at the end.
   *
   * @param bits
   *   The bit value that specifies our path.
   *
   * @return the letter value at the end.
   * @throws IndexOutofBoundsException if bits isn't an appropriate length
   *                                   or desired path doesn't exist.
   */
  public String get(String bits) throws IndexOutOfBoundsException {
    char[] bitArr = bits.toCharArray();

    for (char c : bitArr) {
      Iterator<BitTreeNode> n = this.iterator();

      while (n.hasNext()) {
        BitTreeNode current = n.next();
        if (current.getDir() == c) {
          if (current.getNext() != null) {
            return current.getString();
          } // if
        } else {
          throw new IndexOutOfBoundsException("Desired path doesn't exist.");
        } // if/else
      } // while
    } // for
    return "";  // STUB
  } // get(String)

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
