package edu.grinnell.csc207.main;

import java.io.PrintWriter;
import java.util.Scanner;
import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 * Running the translation program.
 *
 * @author Nicole Gorrell
 * @author Samuel A. Rebelsky
 */
public class BrailleASCII {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Process input to translate.
   *
   * @param args
   *   The command-line parameters we are supplied. The first is the
   *   translation method we want to use, and the second is the string
   *   we want to translate.
   */
  @SuppressWarnings("resource")
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner in = new Scanner(System.in);
    boolean done = false;
    String method = "";
    String message = "";

    if (args.length != 2) {
      System.err.println("Incorrect amount of inputs. Try again.");
      return;
    } // if

    while (!done) {
      method = in.next();
      message = in.next();
      done = true;
    } // while

    String translated = "";

    switch (method) {
      case "braille":
        char[] msgArr = message.toCharArray();
        for (int i = 0; i < msgArr.length; i++) {
          translated += BrailleAsciiTables.toBraille(msgArr[i]);
        } // for
        break;
      case "ascii":
        translated = BrailleAsciiTables.toAscii(message);
        break;
      case "unicode":
        translated = BrailleAsciiTables.toUnicode(message);
        break;
      default:
        System.err.println("Invalid translation method. Try again.");
        return;
    } // switch

    pen.println(translated);
    pen.flush();
    pen.close();
  } // main(String[]

} // class BrailleASCII
