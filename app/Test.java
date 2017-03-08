/**
 * @file: Test.php
 * @info: Class for tets
 * 
 * @utor: Moisés Alcocer, 2017
 */

package app;

//imports
import app.helpers.ContentFilter;
import app.helpers.Strings;
import app.helpers.Trace;
import java.util.Arrays;


class Test {

		static String path = "d://test/";


		public static void main( String[] args ) {

			
			//test1();
			//test2();
			test3();
		}


		private static void test1() {
			String[] file_names = ContentFilter.getNames( path, "files" );
			String[] file_types = ContentFilter.getFileTypes( file_names );

			Trace.strArray( file_names, "Ficheros originales:" );
			Trace.strArray( file_types, "Tipos de ficheros:" );
		}

		private static void test2() {
			String[] arr = new String[] {
				"txt",
				"a",
				"bmp",
				"bmp",
				"rar",
				"zzzzzz",
				"txt"
			};
			Trace.strArray( arr, "Strings originales:" );
			Arrays.sort( arr ); //order
			Trace.strArray( Strings.filterRepeated( arr ), "Filtradas repeticiones:" );
		}

		private static void test3() {
			Trace.ln( "Testing ContentFilter / getNamesByType()" );
			
			Trace.strArray( ContentFilter.getNamesByType( path ));
		}

} //class
