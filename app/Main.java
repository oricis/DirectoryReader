
/**
 * @file: DirectoryReader.php
 * @info: App en JavaFx para listar los ficheros de un directorio
 * 
 * @utor: Moisés Alcocer, 2017
 * @version: 0.1 - 07.03.2017
 */

package app;

//imports
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.stage.Stage;

import app.helpers.ContentFilter;
import app.helpers.Files;
import app.helpers.Trace;


/**
 * Class to construct the App´s GUI
 * 
 */
public class Main extends javafx.application.Application {

	/**********************************/
	/*** Properties declaration *******/

		private Stage stage;

		//Root node
		private VBox layout = new VBox();
		private Layout gui  = new Layout();


		//Elements
		private Button btn			= new Button( "Seleccionar directorio" );
		private Label label			= new Label();
		private Label label_dirs	= new Label( "Directorios:" );
		private Label label_files	= new Label( "Ficheros:");
		private Label res_dirs		= new Label();
		private Label res_files		= new Label();
		private Hyperlink link_web  = new Hyperlink();
		private Text feedback		= new Text(); //Shows the feedbacks with the results

		private String str_for_label = "Contenido directorio ";
		private final String VERSION = "0.1 - 07.03.2017";
		private final String AUTHOR  = "http://www.ironwoods.es";

	   
	/**********************************/
	/*** Methods declaration **********/

		/**
		 * Main method
		 *
		 * @param      args the command line arguments
		 */
		public static void main( String[] args ) {
			
			launch( args );
		}

		@Override 
		public void start( Stage primaryStage ) {
			//Trace.ln( "DirectoryReader / start()" );
			this.stage = primaryStage;
			
			gui.create();

			//Adds the escene to stage
			primaryStage.setScene( new Scene( layout, 320, 340 ));

			//Configs the stage
			primaryStage.setTitle( "DirectoryReader " + VERSION );

			//Shows the stage
			primaryStage.show();
		}

		/**
		 * Changes the App GUI
		 *
		 * @param      path   The dirpath for the search
		 * @param      dirs   The dir names to show
		 * @param      files  The file names to show
		 */
		private void changeGUI( String path, String dirs, String files ) {

			btn.setText( "Ver otro directorio" ); //Changes button text
			gui.doVisibleElements( true ); //Does visible the hidden elements
			gui.setPath( path ); //Puts text with the directoy path in GUI
			
			//Sets content in the GUI
			res_dirs.setText( dirs );
			res_files.setText( files );


			Trace.ln( "Directorios: \n" + dirs );
			Trace.ln( "Ficheros: \n" + files );
		}


	/**********************************/
	/*** Internal classes *************/

		class Events {

			/**
			 * Configs the events
			 * 
			 */
			private void set() {
				link_web.setOnAction( e -> {
					//Trace.ln( "This link is clicked" );
					openWepPage( AUTHOR );
				});

				btn.setOnAction( e -> {
					showResults();
				});
			}

			/**
			 * Opens a wep page in default browser
			 *
			 * @param   url
			 */
			private void openWepPage( String url ) {
				//Trace.ln( "Events / openWepPage()" );
				
				try {
					Desktop.getDesktop().browse(
						new URI( url )
					);

				} catch ( URISyntaxException | IOException ex ) {

					System.out.println( ex.toString( ));
				}
			}

			/**
			 * Selects a directory path
			 * Reads files from directory and save in a class property
			 * Shows hidden elements
			 * 
			 */
			private void showResults() {
				//Trace.ln( "Events / showResults()" );
				String[] all_contents = Files.readDirectoryContent( stage );
				String path = Files.getDirectoryPath();

				String[] dir_names  = ContentFilter.getDirectoryNames( path );
				String[] file_names = ContentFilter.getFileNames( path, true );

				String files = "";
				String dirs  = "";

				if ( dir_names != null ) {
					
					for ( String dir_name : dir_names ) {

						dirs = dirs + dir_name + "\n";
					}

				} else
					dirs = "Sin resultados";


				if ( file_names != null ) {
					
					for ( String file_name : file_names ) {

						files = files + file_name + "\n";
					}

				} else
					files = "Sin resultados";


				//Shows results from the search
				changeGUI( path, dirs, files );
			}

		} //class
		
		class Feedback {

			public void err( String msg ) {
			
				feedback.setFill( Color.FIREBRICK ); 
				feedback.setText( "Error leyendo directorio. \n" + msg );
			}

		} //class

		class Layout {

			/**
			 * Sets the layout (and components) for the App
			 * 
			 */
			private void create() {
				//Trace.ln( "DirectoryReader / create" );

				//Configs the layout
				layout.setAlignment( Pos.CENTER );
				layout.setPadding( new Insets( 10, 10, 10, 10 ));
				layout.setSpacing( 8 ); //Vertical spacing between the nodes

				//Configs elements
				link_web.setText( AUTHOR );
				configFonts();
				label.setText( str_for_label );

				
				//Elements not visible in the beginning
				doVisibleElements( false );

				//Button event
				new Events().set();

				//Adds elements
				layout.getChildren().addAll( 
					btn,		//button
					label,		//text
					label_dirs,	//text
					res_dirs,	//text
					label_files,	//text
					res_files,	//text
					feedback	//text
				);
			}

			/**
			 * Configs the fonts
			 * 
			 */
			private void configFonts() {

				feedback.setFont(  
					Font.font( "Tahoma", FontWeight.NORMAL, 15 ) 
				);
				label.setFont(  
					Font.font( "Tahoma", FontWeight.NORMAL, 15 ) 
				);
				label_dirs.setFont(  
					Font.font( "Tahoma", FontWeight.NORMAL, 15 ) 
				);
				label_files.setFont(  
					Font.font( "Tahoma", FontWeight.NORMAL, 15 ) 
				);
				res_dirs.setFont(  
					Font.font( "Tahoma", FontWeight.NORMAL, 15 ) 
				);
				res_files.setFont(  
					Font.font( "Tahoma", FontWeight.NORMAL, 15 ) 
				);

				label.setTextFill( Color.GREEN );
				label_dirs.setTextFill( Color.BLUE );
				label_files.setTextFill( Color.BLUE );
			}

			/**
			 * Shows / hiddens the layout elements (hidden in the beginnig)
			 * 
			 *  @param 	x -> indicates if the elements will be visible or not
			 */
			private void doVisibleElements( boolean x ) {
				//Trace.ln( "DirectoryReader / doVisibleElements()" );

				feedback.setVisible(  x );
				label.setVisible( x );
				label_dirs.setVisible( x );
				label_files.setVisible( x );
				res_dirs.setVisible( x );
				res_files.setVisible( x );
			}

			/**
			 * Shows the directory path in the GUI
			 * 
			 * @param 	path
			 */
			private void setPath( String path ) {

				label.setText( str_for_label + "\n\"" + path  + "\":" );
			}

		} //class

} //class