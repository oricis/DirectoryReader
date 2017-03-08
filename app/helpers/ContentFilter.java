/**
 * @file: //ContentFilter.php
 *
 * @utor: Moisés Alcocer, 2017
 */
package app.helpers;

//imports
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to filter content
 *
 */
public class ContentFilter {

	/**********************************/
	/*** Properties declaration *******/



	/**********************************/
	/*** Methods declaration **********/
	
		/**
		 * Gets the directories from the directory content
		 *
		 * @param 	String 	The directory path
		 * @return 	String[]
		 */
		public static String[] getDirectoryNames( String path ) {
			//Trace.ln( "ContentFilter / getDirectories()" );

			File directory = new File( path );

			File[] arr_content = directory.listFiles();
			int len = arr_content.length;

			List<String> res = new ArrayList<>();

			for ( int i = 0; i < len; i ++ ) {
				
				if ( arr_content[ i ].isDirectory( ))
					res.add( arr_content[ i ].getName( ));
			}
			String[] arr_res = res.toArray( new String[ 0 ] );
			
			
			return arr_res;
		}

		/**
		 * Gets the files from the directory content
		 *
		 * @param 	String 	The directory path
		 * @return 	String[]
		 */
		public static String[] getFileNames( String path ) {
			//Trace.ln( "ContentFilter / getFiles()" );

			File directory = new File( path );

			File[] arr_content = directory.listFiles();
			int len = arr_content.length;

			List<String> res = new ArrayList<>();

			for ( int i = 0; i < len; i ++ ) {
				
				if ( arr_content[ i ].isFile( ))
					res.add( arr_content[ i ].getName( ));
			}
			String[] arr_res = res.toArray( new String[ 0 ] );
			
			
			return arr_res;
		}

} //class
