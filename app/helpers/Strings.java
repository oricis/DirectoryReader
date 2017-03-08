/** 
 * @file: Strings.php
 * 
 * @utor: Moisés Alcocer, 2016
 */

package app.helpers;

//imports

import java.util.ArrayList;
import java.util.List;



/** 
 * Class for replace string content according to a rules in an array
 * 
 */
public class Strings {

	/**********************************/
	/*** Properties declaration *******/

		private static List<String> res = new ArrayList<>();


	/**********************************/
	/*** Properties declaration *******/

		/**
		 * Cleans the array of empty strings
		 *
		 * @param      arr   The arr
		 * @return     The array
		 */
		public static String[] delEmpties( String[] arr ) {

			res.clear();
			int len = arr.length;

			for ( int i = 0 ; i < len; i ++ ) {

				if ( ! arr[ i ].equals( "" ))
					res.add( arr[ i ] );
			}


			return res.toArray( new String[ 0 ] );
		}

		/**
		 * Filters the array of strings and delete repeat elements
		 *
		 * @param      arr   The arr
		 * @return     The array
		 */
		public static String[] filterRepeated( String[] arr ) {


			int len = arr.length;

			for ( int i = 0 ; i < len; i ++ ) {
				for ( int j = 0; j < ( len - 1 ); j ++ ) {

					if ( i != j ) {
						if ( arr[ i ].equals( arr[ j ] )) {
							arr[ j ] = ""; //replace value by empty string
						} 
					}
				}
			}


			return delEmpties( arr );
		}

} //class
