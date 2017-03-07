/**
 * @file: //Trace.php
 * 
 * @utor: Moisés Alcocer, 2016
 */

package app.helpers;

//imports

import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;



/**
 * Class to handle files
 * 
 */
public class Files {

	/**********************************/
	/*** Properties declaration *******/

		private static File directory = null;


	/**********************************/
	/*** Methods declaration **********/

		/**
		 * Gets the directory path
		 *
		 * @return     The directory path
		 */
		public static String getDirectoryPath() {

			return ( directory != null )
				? directory.getAbsolutePath()
				: "";
		}

		/**
		 * Reads a directory content
		 *
		 * @return  	String[]
		 */
		public static String[] readDirectoryContent( Stage stage ) {
			//Trace.ln( "Files / doVisibleLayoutElements()" );

			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle( "Seleccionar directorio" );
			directory = chooser.showDialog( stage );


			return ( directory != null )
				? directory.list()
				: null;
		}

		/**
		 * Reads a directory content
		 *
		 * @return  	String[]
		 */
		public static String[] readFileNamesFrom( String directory_path ) {
			//Trace.ln( "Files / readFileNamesFrom()" );
			
			File directory = new File( directory_path );


			return ( directory != null )
				? directory.list()
				: null;
		}

		/**
		 * Rename files in disk
		 * 
		 */
		public static boolean renameFiles(
			String path,
			String[] file_names,
			String[] new_file_names
		) {
			//Trace.ln( "Files / renameFiles()" );


			File f1, f2;
			int len = file_names.length;
			String path1, path2;

			boolean success = true;
			for ( int i = 0; i < len; i++ ) {
				path1 = path + File.separator + file_names[ i ];
				path2 = path + File.separator + new_file_names[ i ];
				////Trace.ln( "---> path1: " + path1 );
				////Trace.ln( "---> path2: " + path2 );

				f1 = new File( path1 );
				f2 = new File( path2 );

				if ( ! f1.renameTo( f2 )) {
					//Trace.ln( "---> Err renombrado fichero: " + i );

					success = false;
					break;
				} /**/
			}


			return success;
		}

	} //class