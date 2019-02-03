
/*TODO
 *add file i/o capabilities
 *make translation fucnction
 *print codon triplets
 *reject/fix multi-letter input as selections
 *sanitize all input 
 *check sequence input for invalid characters (ex. numbers, 'U' instead of 'T' in DNA, etc.)
 *for codon sequence use array or arraylist
 */

import java.io.*;
import java.util.*;

public class sequenceHelp {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		boolean loopCondition = true;
		boolean correctChoice = false;
		char ans;
		char ansIN; // accepts answer and finds char in first position
		// loop through sequence-entering process
		while (loopCondition == true && correctChoice == false) {

			System.out.println(
					"Is your sequence a coding DNA sequence (C), a template DNA sequence (T), or an mRNA (R) sequence?");

			ansIN = scan.next().charAt(0);
			ans = Character.toUpperCase(ansIN);
			char cCAIN;
			char correctChoiceAns;

			if (ans == 'C') {
				System.out.println("You have selected a coding DNA sequence. Is this correct? (Y or N)");
				cCAIN = scan.next().charAt(0);
				correctChoiceAns = Character.toUpperCase(cCAIN);

				if (correctChoiceAns == 'Y') {
					correctChoice = true;
				}

				else {
					correctChoice = false;
					continue;
				}
			}

			else if (ans == 'T') {
				System.out.println("You have selected a template DNA sequence. Is this correct? (Y or N)");
				cCAIN = scan.next().charAt(0);
				correctChoiceAns = Character.toUpperCase(cCAIN);

				if (correctChoiceAns == 'Y') {
					correctChoice = true;
				}

				else {
					correctChoice = false;
					continue;
				}
			}

			else if (ans == 'R') {
				System.out.println("You have selected an mRNA sequence. Is this correct? (Y or N)");
				cCAIN = scan.next().charAt(0);
				correctChoiceAns = Character.toUpperCase(cCAIN);

				if (correctChoiceAns == 'Y') {
					correctChoice = true;
				}

				else {
					correctChoice = false;
					continue;
				}

			}

			else {
				System.out.println("You did not make a valid selection.");
				correctChoice = false;
				continue;
			}

			// get rid of all that junk
			String junk = scan.nextLine();

			// Enter the sequence here: Will have to call functions based on what kind of
			// modification user selects
			System.out.println("Please enter your sequence:"); // will eventually ask if it is in a file and will
																// read/write a file if desired
			String sequence = scan.nextLine().toUpperCase();
			// System.out.println("Your sequence: " + sequence);

			// Coding Strand
			if (ans == 'C') {
				System.out.println("Do you want the template strand (T), the mRNA strand (R), or both (B)?");
				char selectionIN = scan.next().charAt(0);
				char selection = Character.toUpperCase(selectionIN);

				if (selection == 'T') {
					System.out.println("Your new template strand: " + complementStrand(sequence));

				}

				else if (selection == 'R') {
					System.out.println(transcription(sequence));
				}

				else if (selection == 'B') {
					System.out.println("Your new template strand: " + complementStrand(sequence));
					String print = transcription(sequence);
					System.out.println(print);
					tripletSequence(print);
				}

				else {
					System.out.println("You did not make a valid selection.");
				}
			}

			// Template Strand
			else if (ans == 'T') {
				System.out.println("Do you want the coding strand (C), the mRNA strand (R), or both (B)?");
				char selectionIN = scan.next().charAt(0);
				char selection = Character.toUpperCase(selectionIN);

				if (selection == 'C') {
					System.out.println("Your new coding strand: " + complementStrand(sequence));
				}

				else if (selection == 'R') {
					String varSeq = complementStrand(sequence);
					System.out.println(transcription(varSeq));
				}

				else if (selection == 'B') {
					String temSeq = complementStrand(sequence);
					System.out.println("Your new coding strand: " + temSeq);
					System.out.println(transcription(temSeq));
				}

				else {
					System.out.println("You did not make a valid selection.");
				}
			}

			else if (ans == 'R') {
				System.out.println("Do you want the coding strand (C), the template strand (T), or both (B)?");
				char selectionIN = scan.next().charAt(0);
				char selection = Character.toUpperCase(selectionIN);

				if (selection == 'C') {
					System.out.println("Your new coding strand: " + reverseTranscription(sequence));
				}

				else if (selection == 'T') {
					String varSeq = reverseTranscription(sequence);
					System.out.println("Your new template strand: " + complementStrand(varSeq));
				}

				else if (selection == 'B') {
					System.out.println("Your new coding strand: " + reverseTranscription(sequence));
					String varSeq = reverseTranscription(sequence);
					System.out.println("Your new template strand: " + complementStrand(varSeq));

				}
			}

			// ask if user wants to enter another sequence - this should be after the
			// to-be-completed functions are called and that sequence modified
			// while loop ensures valid choice
			boolean validAns = false;
			while (validAns == false) {
				System.out.println("Do you want to enter another sequence? (Y or N)");
				char loopAnsIN = scan.next().charAt(0);
				char loopAns = Character.toUpperCase(loopAnsIN);

				if (loopAns == 'Y') {
					loopCondition = true;
					validAns = true;
					correctChoice = false;
				}

				else if (loopAns == 'N') {
					loopCondition = false;
					System.out.println("Goodbye.");
					validAns = true;
				}

				else {
					System.out.println("Invalid option. Please enter 'Y' or 'N'");
				}

			}

		}

	}

	/*
	 * FUNCTIONS FOR SEQUENCEHELP
	 */
	// function for giving the complement to any DNA strand
	public static String complementStrand(String codingStrand) {

		// loop to take sequence entered and place it in an array
		char[] codingStrandSequence = new char[codingStrand.length()]; // can use method codingStrand.toCharArray()
		for (int i = 0; i < codingStrand.length(); i++) {
			codingStrandSequence[i] = codingStrand.charAt(i);
		}

		// loop to take array sequence and modify the sequence --> could use RegExp ex.)
		// string1Low = string1Low.replaceAll("[.,!?;:']", ""); --> says replace all
		// those with ""
		for (int i = 0; i < codingStrand.length(); i++) {
			if (codingStrandSequence[i] == 'A') {
				codingStrandSequence[i] = 'T';
			}

			else if (codingStrandSequence[i] == 'T') {
				codingStrandSequence[i] = 'A';
			}

			else if (codingStrandSequence[i] == 'G') {
				codingStrandSequence[i] = 'C';
			}

			else if (codingStrandSequence[i] == 'C') {
				codingStrandSequence[i] = 'G';
			}
		}

		/*
		 * System.out.print("Your new complement sequence: "); for (int i = 0; i <
		 * codingStrand.length(); i++) { System.out.print(codingStrandSequence[i]); }
		 * 
		 * System.out.println(" ");
		 */

		String complement = "";
		for (int i = 0; i < codingStrand.length(); i++) {
			complement += codingStrandSequence[i];
		}

		return complement;

		// System.out.println(complement);

	}

	// function for transcribing the CODING strand to an mRNA strand
	public static String transcription(String codingStrand) {

		// use regex to replace a substring "T" with another substring "U"
		String mRNA = codingStrand.replaceAll("T", "U");
		// System.out.println("Your new mRNA sequence: " + mRNA);
		return mRNA;
	}

	// function to replace all 'U' in RNA with 'T'
	public static String reverseTranscription(String mRNA) {

		// Regex
		String codingSeq = mRNA.replaceAll("U", "T");
		return codingSeq;
	}

	public static void tripletSequence(String sequenceIN) {

		// locates start codon by finding "AUG" --> could probably find a string within
		// the sequence string instead of characters (is there any benefit one way or
		// another?)
		int startPosition = 0;
		for (int i = 0; i < sequenceIN.length(); i++) {
			if (sequenceIN.charAt(i) == 'A') {
				if (sequenceIN.charAt(i + 1) == 'U') {
					if (sequenceIN.charAt(i + 2) == 'G') {
						startPosition = i;
						break; // need to break or else off-by-n errors can occur
					}
				}
			}
		}

		// see "Coding strand, both, mRNA print-out"
		System.out.println("" + startPosition);

		String truncSeq = sequenceIN.substring(startPosition);
		System.out.println(truncSeq);

		int rowNum = 0;
		if ((truncSeq.length() % 3) == 0) {
			rowNum = truncSeq.length() / 3;
		}

		else {
			rowNum = (truncSeq.length() / 3) + 1;
		}

		char[] truncArray = truncSeq.toCharArray(); // problem is if truncArray.length % 3 != 0, adding it to
													// sequenceArray gives error

		// array with 3 columns (triplets) and as many columns as a multiple of 3 that
		// the truncated sequence can provide
		// this won't necessarily be needed after stop codons are introduced
		char[][] sequenceArray = new char[rowNum][3];
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < 3; j++) {
				sequenceArray[i][j] = truncArray[i * 3 + j]; // if you forget how this works, write it down; if i and j
																// = 0 --> 0 * 3 + 0 = 0 (index 0 of truncArray is A),
																// etc.
			}
		}
		System.out.println(rowNum);

		System.out.println(Arrays.deepToString(sequenceArray));
	}

}