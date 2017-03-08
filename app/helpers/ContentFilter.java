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
			
			return getNames( path, "directories" );
		}

		/**
		 * Gets the files from the directory content
		 *
		 * @param 	String 	The directory path
		 * @return 	String[]
		 */
		public static String[] getFileNames( String path ) {
			//Trace.ln( "ContentFilter / getFiles()" );

			return getNames( path, "files" );
		}

		/**
		 * Gets the names for files / directories
		 *
		 * @param      path  The path
		 * @param      type  The type to return
		 * @return     The names
		 */
		private static String[] getNames( String path, String type ) {
			//Trace.ln( "ContentFilter / getNames()" );
			//
			String[] arr_res = null;
			File directory 	 = new File( path );

			File[] arr_content = directory.listFiles();
			int len = arr_content.length;

			List<String> res = new ArrayList<>();

			for ( int i = 0; i < len; i ++ ) {
				
				if ( type.equals( "directories" ) && arr_content[ i ].isDirectory( ))
					res.add( arr_content[ i ].getName( ));

				if ( type.equals( "files" ) && arr_content[ i ].isFile( ))
					res.add( arr_content[ i ].getName( ));
			}
			if ( res.size() > 0 )
				arr_res = res.toArray( new String[ 0 ] );
			
			
			return arr_res;
		}

} //class
