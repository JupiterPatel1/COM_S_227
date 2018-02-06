package mini1;

/**
 * @author Richard Smith
 */

/**
 * Simple model of a strand of DNA.  An instance of this class encapsulates
 * a string of characters.  A character is called <em>valid</em> if it
 * is one of the the letters 'A', 'C', 'G', 'T' (uppercase) and a DNASequence
 * object is called <em>valid</em> if all its characters are valid.
 * The characters 'A' and 'T' are said to be <em>complements</em> of each 
 * other and likewise the characters 'C' and 'G' are complements.  
 * Complementary characters are said to <em>bond</em> with each other.
 * The main operations on this class are for the purpose of determining the 
 * number and locations of bonds that one sequence can form with another
 * depending how they are aligned (shifted) with each other.
 * <p>
 * However, it is entirely possible to construct a DNASequence object
 * containing invalid characters, and all operations should work
 * correctly for arbitrary characters.  Note that a character other than
 * 'A', 'C', 'G', or 'T' is never considered to bond with another character.
 */
public class DNASequence
{
  /**
   * String of data for this sequence. 
   */
  private String dataStr;
  
  /**
   * Constructs DNASequence object with given string of data;
   * this constructor does not check whether given string
   * is valid (see the method allValid).
   * @param givenData
   *   string of characters for this DNASequence
   */
  public DNASequence(String givenData)
  {
    dataStr = givenData;
  }
  
  /**
   * Returns a String representing data for
   * DNASequence.
   * @return
   *   the characters in this DNASequence
   */
  public java.lang.String toString()
  {
    return dataStr;
  }
  
  /**
   * Determines whether all characters in sequence
   * are valid ('A', 'G', 'C', or 'T' uppercase only).
   * @return
   *   true if all characters are valid, false otherwise
   */
  public boolean allValid()
  {
    for (int i = 0; i < dataStr.length(); i++)
    {
      char c = dataStr.charAt(i);
      if (!(c == 'A' || c == 'G' || c == 'C' || c == 'T'))
      {
        return false;
      }
    }
    return true; 
  }
  
  /**
   * Removes all invalid characters from DNASequence. 
   * if object's data is the string "TaGxy*!  Cz", 
   * after calling method, data is "TGC".
   */
  public void fix()
  {
	// creates temp string variable to hold correct characters
    String temp = "";    
    for (int i = 0; i < dataStr.length(); ++i)
    {
      char c = dataStr.charAt(i);
      if (c == 'A' || c == 'G' || c == 'C' || c == 'T')
      {
        temp += c;
      }
    }
    dataStr = temp;
  }
  
  /**
   * Determines whether given sequence is a subsequence
   * of this one.  A string t is a subsequence of another
   * string s if all characters of t can be found in s in the
   * same order.  Visa versa, string t is a subsequence of s
   * if t can be obtained by deleting some of the characters of s.
   * Invalid characters in the given string are ignored.
   * <p>
   * example "TxxAA" is a subsequence of "CTyyGCACA" but 
   * not of "CAAT" nor of "TA".
   * @param other
   *   the given DNASequence
   * @return
   *   true if the given sequence is a subsequence of this one, 
   *   false otherwise
   */
  public boolean isSubsequence(DNASequence other)
  { 
	// creates copy to compare
    DNASequence copy = new DNASequence(other.dataStr);
    copy.fix();
    int p = 0;
    String t = copy.dataStr;
    
    for (int i = 0; i < t.length(); i++	)
    {
      char c = t.charAt(i);
      while (p < dataStr.length() && dataStr.charAt(p) != c)
      {
        p += 1;
      }
      if (p >= dataStr.length())
      {
        return false;
      }   
      p += 1;
    }
    return true;
  }
  
  /**
   * Returns true if two characters are complementary
   * ('A' with 'T' or 'C' with 'G').
   * @param c1
   *   potential character for a base pair
   * @param c2
   *   potential character for a base pair
   * @return
   *   true if two characters are 'A' and 'T' or 'C' and 'G';
   *   false otherwise
   */
  public boolean willBond(char c1, char c2)
  {
    return (c1 == 'A' && c2 == 'T') || (c1 == 'T' && c2 == 'A') ||
           (c1 == 'G' && c2 == 'C') || (c1 == 'C' && c2 == 'G');
  }
    
  /**
   * Replaces object's data with its complement;
   * 'A' is replaced with 'T' and so on.
   * Invalid characters are not modified.
   * if data for this sequence is "AGTT", after
   * this method is called the data would be "TCAA".  
   */
  public void complement()
  {
	// creates string variable to hold new data
    String newDataStr = "";
    for (int i = 0; i < dataStr.length(); i++)
    {
      // Helper method returns character that is complement of the character.
      newDataStr += complement(dataStr.charAt(i)); 
    }
    dataStr = newDataStr;
  }
  
  /**
   * Returns maximum possible number of bonds that can be formed
   * with this sequence when given sequence is shifted left or 
   * right by any amount.
   * @param other
   *   the DNASequence to align with this one
   * @return
   *   maximum possible number of bonds 
   */
  public int findMaxPossibleBonds(DNASequence other)
  {
    // if no matches, max is zero
    int max = 0;
    
    // most you are able to shift right is the length of this sequence minus 1
    for (int shift = 0; shift < dataStr.length() - 1; shift++)
    {
      int c = countBondsWithShift(other, shift);
      if (c > max)
      {
        max = c;
      }
    }
    // most you are able to shift left is length of other minus 1
    for (int shift = 0; shift < other.dataStr.length() - 1; shift++)
    {
      int c = countBondsWithShift(other, -shift);
      if (c > max)
      {
        max = c;
      }
    }
    return max;
  }
  
  /**
   * Returns number of bonds that are formed with this sequence
   * when given sequence is shifted right by the given number
   * of spaces (where a negative number represents a left shift). 
   * Neither this sequence nor the given sequence is modified.
   * @param other
   *   DNASequence to align with this one
   * @param shift
   *   number of spaces to the right that the other sequence is shifted
   * @return
   *   number of bonds formed when the given sequence is 
   *   aligned with this one, with the given shift
   */
  public int countBondsWithShift(DNASequence other, int shift)
  {
	  int c = 0;
	    for (int i = 0; i < dataStr.length(); i++)
	    {
	      char c1 = dataStr.charAt(i);

	      // makes sure that (i - shift) is a valid index in the other sequence
	      if (i >= shift && i < shift + other.dataStr.length())
	      {
	        char c2 = other.dataStr.charAt(i - shift);
	        if (willBond(c1, c2))
	        {
	          c++;
	        }
	      }
	    }
	    return c;
  }
  
  /**
   * Returns a string showing which characters in this sequence
   * are bonded when given sequence is shifted right by the given number
   * of spaces (where a negative number represents a left shift).
   * Non-matching characters are shown as dashes.  For example,
   * if this sequence is "ATATGC" and given sequence is "TCC",
   * shifted right by 2, then this method returns "--A-G-".
   * Neither this sequence nor given sequence is modified.
   * @param other
   *   sequence to which this one is being matched
   * @param shift
   *   number of spaces the other sequence is shifted to the right
   * @return
   *   string indicating where matches occur
   */
  public String findBondsWithShift(DNASequence other, int shift)
  {
	  // empty string to append to
	    String result = "";
	    
	    for (int i = 0; i < dataStr.length(); i++)
	    {
	      char c1 = dataStr.charAt(i);
	      
	      // check if (i - shift) is valid index in other sequence
	      if (i >= shift && i < shift + other.dataStr.length())
	      {       
	        char c2 = other.dataStr.charAt(i - shift);
	        if (willBond(c1, c2))
	        {
	          // append upper-case
	          result += c1;
	        }
	        else
	        {
	           // if no match, append dash
	           result += '-';
	        }
	      }
	      else
	      {
	        // if out of range, append dash character
	        result += '-';
	      }
	    }
	    return result;
  }
  
  /**
   * Helper method returns the character that is the complement of the given 
   * character.
   * @param ch
   *   given character
   * @return
   *   complement of the given character
   */
  private char complement(char ch)
  { 
	// uses switch to swap characters
    switch(ch)
    {
      case 'A': return 'T';
      case 'T': return 'A';
      case 'G': return 'C';
      case 'C': return 'G';
      default: return ch;
    }
  }
}