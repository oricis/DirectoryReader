/**
 * @file: //ContentFilter.php
 *
 * @utor: Moisés Alcocer, 2017
 */
package app.helpers;

//imports
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

/**
 * Class to filter content
 *
 */
public class ContentFilter {

	/**********************************/
	/*** Properties declaration *******/

		private static List<String> res = new ArrayList<>();
		private static String[] arr_res = null;


	/**********************************/
	/*** Methods declaration **********/
	
	/**
	 * Public methods
	 * 
	 */

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
		 * @param 	path	The directory path
		 * @return 	String[]
		 */
		public static String[] getFileNames( String path ) {
			//Trace.ln( "ContentFilter / getFiles()" );

			return getNames( path, "files" );
		}

		/**
		 * Gets the files from the directory content
		 *
		 * @param 	path	The directory path
		 * @param 	order_by_type
		 * @return 	String[]
		 */
		public static String[] getFileNames( 
			String path,
			boolean order_by_type
		) {
			//Trace.ln( "ContentFilter / getFiles()" );

			if ( order_by_type )
				return getNamesByType( path ); 


			return getNames( path, "files" );
		}

		/**
		 * Gets the file types from array of file names
		 *
		 * @param	  names  The names
		 *
		 * @return	 The file types.
		 */
		public static String[] getFileTypes( String[] names ) {

			int size = names.length;
			String file_type = "";

			res.clear(); //remove previous content
			
			for ( int i = 0; i < size; i ++ ) {

				file_type = getFileType( names[ i ] );
				res.add( file_type );
			}

			if ( res.size() > 0 ) {
				arr_res = res.toArray( new String[ 0 ] );
				Arrays.sort( arr_res );
				arr_res = Strings.filterRepeated( arr_res );
			}

			
			return arr_res;
		}

		/**
		 * Gets the names for files / directories
		 *
		 * @param	  path  The path
		 * @param	  type  The type to return
		 * @return	 The names
		 */
		public static String[] getNames( String path, String type ) {
			//Trace.ln( "ContentFilter / getNames()" );
			
			File directory = new File( path );

			File[] arr_content = directory.listFiles();
			int len = arr_content.length;
			res.clear(); //remove previous content

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

		/**
		 * Gets the file types
		 *
		 * @param	  path  The path
		 * @return	 The file types
		 */
		public static String[] getNamesByType( String path ) {
			String[] file_names = getNames( path, "files" );
			String[] file_types = getFileTypes( file_names );
			Arrays.sort( file_types );
 

			//Groups file names by file extension
			Hashtable<String, String[]> groups = getGroups( 
				file_names, 
				file_types 
			);
			String[] arr_res = getArrayFromGroups( groups );


			return arr_res;
		}

	/**
	 * Private methods
	 * 
	 */

		private static String getFileType( String name ) {

			int pos;
			String type = name;

			while (( pos = type.indexOf( "." )) > -1 ) {
				//Trace.ln( pos + "" );
				
				type = name.substring(
					pos + ".".length(),
					name.length()
				);
			}


			return type;
		}

		/**
		 * Groups file names by file extension
		 * 
		 */
		private static Hashtable<String, String[]> getGroups( 
			String[] file_names, 
			String[] file_types 
		) {
			//Trace.ln( "ContentFilter / getGroups()" );

			int size_file_names = file_names.length;
			int size_file_types = file_types.length;

			String file_name, file_type;

			Hashtable<String, String[]> groups = new Hashtable<>();
			//groups.put( "Ciudades España", new String[] { "Madrid", "Teruel" } );

			//Trace.ln( "Num. file names: " + size_file_names );
			//Trace.ln( "Num. file types: " + size_file_types );

			//Iterates over file extensions
			for ( int i = 0; i < size_file_types; i ++ ) {
				res.clear();
				file_type = file_types[ i ];
				//Trace.ln( "\nFile type: " + file_type );

				//Iterates over file names
				for ( int j = 0; j < size_file_names; j ++ ) {

					file_name = file_names[ j ];
					//Trace.ln( "File name: " + file_name );

					//Finded file extension inside the file name
					if ( file_name.indexOf( file_type ) > -1 ) {
						//Trace.ln( "Añadiendo: " + file_name );
						res.add( file_name );
					}
				}

				//Trace.ln( "Guardando grupo: " + file_type );
				groups.put( 
					file_type,
					res.toArray( new String[ 0 ] ) //String[] with file names of one type
 				);
			}

			//Trace.ln( "Devolviendo grupos..." );
			return groups;
		}

		private static String[] getArrayFromGroups(
				Hashtable<String, String[]> groups
		) {
			res.clear();

			//Gets the keys
			Enumeration<String> e = groups.keys();
			List<String> temp = Collections.list( e );
			String[] keys = temp.toArray( new String[ 0 ] );

			Arrays.sort( keys );
			int keys_size = keys.length;

			//Trace.strArray( keys, "Trazando keys..." ); System.exit( 0 );

			for ( int i = 0; i < keys_size; i ++ ) {

				String key = keys[ i ];
				//Trace.ln( "Key: " + key );

				String[] temp_arr = groups.get( key );
				//Trace.ln( "Elementos: " + temp_arr.length );

				for ( String str : temp_arr ) {
					//Trace.ln( "--> " + str );
					res.add( str );
				}
			}

			String[] arr_res = res.toArray( new String[ 0 ] );
			//Trace.strArray( arr_res, "Nombres ficheros:" ); System.exit( 0 );


			return arr_res;
		}

} //class
