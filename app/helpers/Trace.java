/**
 * @file: Trace.php
 * 
 * @utor: Moisés Alcocer, 2016
 */

package app.helpers;

//imports


/**
 * Class for replace string content according to a rules in an array
 * 
 */
public class Trace {

	/**********************************/
	/*** Properties declaration *******/



	/**********************************/
	/*** Methods declaration **********/

		/**
		 * Prints a string and jump to next line
		 *
		 * @param      str   The string
		 */
		public static void ln( String str ) {

			System.out.println( str );
		}

		/**
		 * Prints the strings stored into an array 
		 * 
		 * @param 	arr_strs
		 */
		public static void strArray( String[] arr_strs ) {
			System.out.println( "Trace / strArray()" );

			int len = arr_strs.length;
			String str;

			for ( int i = 0; i < len; i++ ) {

				str = arr_strs[i];
				System.out.println( str );
			}
			System.out.println( "-------------------------" );
			//System.exit( 0 );
		}

		
	/**********************************/
	/*** Internal classes *************/


} //class