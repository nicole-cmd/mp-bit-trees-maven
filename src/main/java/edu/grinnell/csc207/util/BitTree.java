package edu.grinnell.csc207.util;

import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.String;
import java.util.Scanner;

/**
 * Trees intended to be used in storing mappings between fixed-length 
 * sequences of bits and corresponding values.
 *
 * @author Nicole Gorrell
 */
public class BitTree {
  // +-----------+------------------------------------------------
  // | Constants |
  // +-----------+

  /** The initial index of a bit string input. */
  private static final int BEG_BIT = 0;

  /** The last index of a bit string input. */
  private static final int END_BIT = 6;
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /** The root of the tree. Will be null. */
  BitTreeNode root;

  /** The size of the tree. Will be either 6, 7 or 8. */
  int size;

  /** The leaf of the tree that contains a value. */
  BitTreeLeaf leaf;

  /** 
   * Our current node. Will be set to the root when we want to
   * traverse the tree. 
   */
  BitTreeNode current;

  /** Node following the current one. */
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
    this.current = this.root;
    setInterior(n);
  } // BitTree(int)

  /**
   * Builds a new empty bit tree.
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
   * Set the interior nodes of the tree.
   *
   * @param bitSize
   *   How many next nodes we will have.
   */
  private void setInterior(int bitSize) {
    this.next = this.current;
    for (int level = 0; level < bitSize; level++) {
      this.next = new BitTreeInteriorNode();
      this.current = this.next;
    } // for
  } // setInterior(int)

  /**
   * Verify all values in a string bit are either 0 or 1.
   * 
   * @param bits
   *   The string bit whose values we are checking.
   */
  private boolean bitCheck(String bits) {
    for (char c : bits.toCharArray()) {
      if (!((c == '0') || (c == '1'))) {
        return false;
      } // if
    } // for

    return true;
  } // bitCheck(String)

  /**
   * Verify the bit is an appropriate length.
   *
   * @param bits
   *
   * @return true if the length is appropriate; false if not.
   */
  private boolean bitLengthCheck(String bits) {
    if (!(bits.length() < 6 || bits.length() > 8)) {
      return false;
    } // if

    return true;
  } // bitLengthCheck(String)

  /**
   * Traverse this tree to get to the leaf.
   *
   * @param bits
   *   The bits we process to traverse the tree.
   *
   * @return the current node we are at.
   */
  private BitTreeLeaf traverse(String bits) {
    char[] bitArr = bits.toCharArray();
    this.current = this.root;

    for (int i = 0; i < this.size - 1; i++) {
      if (bitArr[i] == '0') {
        this.next = current.goLeft();
      } else {
        this.next = current.goRight();
      } // if/else
    } // for

    return (BitTreeLeaf) current;
  } // traverse()

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
    if (!(bitLengthCheck(bits) || bitCheck(bits))) {
      throw new IndexOutOfBoundsException("Bit length is inappropriate and/or bit"
                                          + " contains invalid values.");
    } // if

    this.leaf = this.traverse(bits);
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
    if (!(bitLengthCheck(bits))) {
      throw new IndexOutOfBoundsException("Bit length is inappropriate.");
    } // if
    String goal = "";
    BitTreeLeaf end = null;
    end = this.traverse(bits);

    if (end != null) {
      goal = end.getLetter();
      return goal;
    } // if

    throw new IndexOutOfBoundsException("Desired path doesn't exist.");
  } // get(String)

  /**
   * Print out the tree in CSV format.
   *
   * @param pen
   *   The pen we use to print.
   */
  public void dump(PrintWriter pen) {
    String fin = "";
    this.current = this.root;
    for (int i = 0; i < this.size - 1; i++) {
      if (!(this.next.equals(current.goLeft()))) {
        fin.concat("1");
      } else {
        fin.concat("0");
      } // if/else
    } // for

    fin.concat(", " + ((BitTreeLeaf) this.current).getLetter());
    pen.println(fin);
  } // dump(PrintWriter)

  /**
   * Reads a series of lines to store in the bit tree.
   *
   * @param source
   *   Where we get our bit lines from. Bits are in the form
   *   'bits,value'.
   */
  @SuppressWarnings("resource")
  public void load(InputStream source) {
    Scanner in = new Scanner(source);
    boolean done = false;
    String temp = "";
    String bits = "";
    String value = "";

    while (!done) {
      temp = in.nextLine();
      bits = temp.substring(BEG_BIT, END_BIT);
      value = temp.substring(END_BIT + 2); // so we don't grab the comma
      this.set(bits, value);
      done = true;
    } // while
  } // load(InputStream)

} // class BitTree
