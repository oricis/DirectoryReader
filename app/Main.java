
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
		private Label label			= new Label( "Contenido directorio " );
		private Label results		= new Label(); //To show the results
		private Hyperlink link_web  = new Hyperlink();
		private Text feedback		= new Text(); //Shows the feedbacks with the results

		private final String VERSION = "0.1 - 07.03.2017";
		private final String AUTHOR  = "http://www.ironwoods.es";

	   
	/**********************************/
	/*** Methods declaration **********/

		/**
		 * Main method
		 *
		 * @param args the command line arguments
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


	/**********************************/
	/*** Internal classes *************/

		class Events {
			
			private String[] file_names = null;


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
					setFileNames();
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
			private void setFileNames() {
				//Trace.ln( "Events / setFileNames()" );

				file_names = Files.readDirectoryContent( stage );

				if ( file_names != null ) {
					gui.setPath();  //Puts text with the directoy path in GUI

					//Changes button text
					btn.setText( "Ver otro directorio" );

					//Does visible the hidden elements
					gui.doVisibleElements( true );

					String res = "";
					for ( int i = 0; i < file_names.length; i++ ) {

						res = res + " ---> " + file_names[ i ] + "\n";
					}

					results.setText( res );
				}
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
				
				//Elements not visible in the beginning
				doVisibleElements( false );

				//Button event
				new Events().set();

				//Adds elements
				layout.getChildren().addAll( 
					btn,		//button
					label,		//text
					results,	//text
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
				results.setFont(  
					Font.font( "Tahoma", FontWeight.NORMAL, 15 ) 
				);
			}

			/**
			 * Shows / hiddens the layout elements (hidden in the beginnig)
			 * 
			 */
			private void doVisibleElements( boolean x ) {
				//Trace.ln( "DirectoryReader / doVisibleElements()" );

				feedback.setVisible( x );
				label.setVisible(	 x );
				results.setVisible(	 x );
			}

			/**
			 * Shows the directory path in the GUI
			 * 
			 */
			private void setPath() {
				String path = Files.getDirectoryPath();

				label.setTextFill( Color.GREEN ); 
				label.setText( label.getText() + "\n\"" + path  + "\":" );
			}

		} //class

} //class