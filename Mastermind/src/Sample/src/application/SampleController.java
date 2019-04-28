package application;

import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class SampleController implements Initializable{
	

	int gameSize = 5; //How many tries to guess the correct code
	final ArrayList<String> COLORKEY = new ArrayList<String>(
		      Arrays.asList("#0b7ef2","#f80314","#e0ed0c","#53f209","#f56a07","#100b96"));
	
	static int rowCounter = 0;
	int correctPlace = 0;
	int correctColor = 0;
	ArrayList<String> masterCode = new ArrayList<String>();
	

	 @FXML
	 private Button lockButton;
	 
	 @FXML
	 private TextField mcMessage;
	 
	 @FXML
	 private GridPane mcPane;
	 
	 @FXML
	 private Label mcLabel;
	 
		// Input Row 1
		@FXML
	    private Circle c10;
		@FXML
	    private Circle c20;
		@FXML
	    private Circle c30;
		@FXML
	    private Circle c40;
		
		// Input Row 2
		@FXML
	    private Circle c11;
		@FXML
	    private Circle c21;
		@FXML
	    private Circle c31;
		@FXML
	    private Circle c41;
		
		// Input Row 3
		@FXML
	    private Circle c12;
		@FXML
	    private Circle c22;
		@FXML
	    private Circle c32;
		@FXML
	    private Circle c42;
		
		// Input Row 4
		@FXML
	    private Circle c13;
		@FXML
	    private Circle c23;
		@FXML
	    private Circle c33;
		@FXML
	    private Circle c43;
		
		// Input Row 5
		@FXML
	    private Circle c14;
		@FXML
	    private Circle c24;
		@FXML
	    private Circle c34;
		@FXML
	    private Circle c44;		
		
		//Combining all the row arrays into 1 array.
		ArrayList<ArrayList<Circle> > inputCircles = new ArrayList<ArrayList<Circle> >();
		
		//6 Color Keys
		@FXML
	    private Circle key1;
		@FXML
	    private Circle key2;
		@FXML
	    private Circle key3;
		@FXML
	    private Circle key4;
		@FXML
	    private Circle key5;
		@FXML
	    private Circle key6;
		
		//MasterCode Circles
		@FXML
	    private Circle mc0;
		@FXML
	    private Circle mc1;
		@FXML
	    private Circle mc2;
		@FXML
	    private Circle mc3;
		ArrayList <Circle> mcList;
		
		// Answer Key Row 1
		@FXML
		private Circle ak10;
		@FXML
		private Circle ak11;
		@FXML
		private Circle ak12;
		@FXML
		private Circle ak13;
		
		// Answer Key Row 2
		@FXML
		private Circle ak20;
		@FXML
		private Circle ak21;
		@FXML
		private Circle ak22;
		@FXML
		private Circle ak23;
		
		// Answer Key Row 3
		@FXML
		private Circle ak30;
		@FXML
		private Circle ak31;
		@FXML
		private Circle ak32;
		@FXML
		private Circle ak33;
		
		// Answer Key Row 4
		@FXML
		private Circle ak40;
		@FXML
		private Circle ak41;
		@FXML
		private Circle ak42;
		@FXML
		private Circle ak43;
		
		// Answer Key Row 5
		@FXML
		private Circle ak50;
		@FXML
		private Circle ak51;
		@FXML
		private Circle ak52;
		@FXML
		private Circle ak53;


/*********************************************************************************************************
* Name: compareInput
* Description: This function is called when the user clicks on the "Lock In!" button to enter his answers
* Calls: N/A
* Called: N/A
*/	
	@FXML
    void compareInput(ActionEvent event) {
		
		correctPlace = 0;
		correctColor = 0;
		
		//compare the users guesses to the MasterCode
		ArrayList<Integer> skip = new ArrayList<Integer>(4);
    	skip.add(0,0);
    	skip.add(1,0);
    	skip.add(2,0);
    	skip.add(3,0);
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(inputCircles.get(rowCounter).get(j).getFill().toString().compareTo(mcList.get(i).getFill().toString()) == 0 && i==j) {
					correctPlace++;
					skip.add(i, 1);
					if(i<5) {
						i++;
						j = 0;
					}
					
				}
				
			}
		}
			
			for (int i = 0; i < 4 ; i++) {
				for (int j = 0; j < 4; j++) {
					if(inputCircles.get(rowCounter).get(j).getFill().toString().compareTo(mcList.get(i).getFill().toString()) == 0 && i!=j && skip.get(i) != 1) {
						correctColor++;
						skip.add(i,1);
						if (j==3) {
							i++;
							j=0;
						}
					}
					
				}
			}
			System.out.println(correctPlace);
			System.out.println(correctColor);

		// Show how many the user got correct place and color or just correct color
		displayState();
		
		//Check if player won, lost or can continue.
		if(correctPlace == 4) {
			won();
		}
		else if(rowCounter == gameSize-1) {
			lost();
		}
		
		correctPlace = 0;
		correctColor = 0;
		//lockRow(); // locks the edit-ability of the current row.
		rowCounter++;
		//unlockNextRow(); //unlocks the edit-ability of the next row.
    }
	
	
	void displayState() {
		
		// Answer Keys
		ArrayList<Circle> akRow1 = new ArrayList<Circle>(Arrays.asList(ak10,ak11,ak12,ak13));
		ArrayList<Circle> akRow2 = new ArrayList<Circle>(Arrays.asList(ak20,ak21,ak22,ak23));
		ArrayList<Circle> akRow3 = new ArrayList<Circle>(Arrays.asList(ak30,ak31,ak32,ak33));
		ArrayList<Circle> akRow4 = new ArrayList<Circle>(Arrays.asList(ak40,ak41,ak42,ak43));
		ArrayList<Circle> akRow5 = new ArrayList<Circle>(Arrays.asList(ak50,ak51,ak52,ak53));	
		
		// 2D Input Circles
		ArrayList<ArrayList<Circle> > akCircles = new ArrayList<ArrayList<Circle> >(Arrays.asList(akRow1,akRow2,akRow3,akRow4,akRow5));		
		
		int i = 0;
		while (correctPlace != 0) {
			akCircles.get(rowCounter).get(i).setFill(Color.RED);
			correctPlace--;
			i++;
		}
		while (correctColor != 0) {
			akCircles.get(rowCounter).get(i).setFill(Color.WHITE);
			i++;
			correctColor--;
			
		}
	
	}

	
/*********************************************************************************************************
 * Name: lockRow
 * Description: locks the input row that the user just used.
 * Calls: N/A
 * Called: N/A
 */
	void lockRow() {
		
	//	Circle array[][] = {{c10}};
	//	array[0][0].setFill(Color.valueOf("#0b7ef2"));
		
		inputCircles.get(rowCounter).get(0).setDisable(true);
		inputCircles.get(rowCounter).get(1).setDisable(true);
		inputCircles.get(rowCounter).get(2).setDisable(true);
		inputCircles.get(rowCounter).get(3).setDisable(true);
	}
	
/*********************************************************************************************************
* Name: unlockNextRow
* Description: Unlocks the next row for the user to input.
* Calls: N/A
* Called: N/A
*/	
	void unlockNextRow() {
		inputCircles.get(rowCounter).get(0).setDisable(false);
		inputCircles.get(rowCounter).get(1).setDisable(false);
		inputCircles.get(rowCounter).get(2).setDisable(false);
		inputCircles.get(rowCounter).get(3).setDisable(false);
	}
	
	/*********************************************************************************************************
	 * Name: randomMasterCode
	 * Description: locks the input row that the user just used.
	 * Calls: N/A
	 * Called: N/A
	 */
	void randomMasterCode() {
		int randomNum;
		SecureRandom rand = new SecureRandom();
		
		randomNum  = rand.nextInt(5);
		masterCode.add(0,COLORKEY.get(randomNum));
		mc0.setFill(Color.valueOf(COLORKEY.get(randomNum)));
		
		randomNum  = rand.nextInt(5);
		masterCode.add(1, COLORKEY.get(randomNum));
		mc1.setFill(Color.valueOf(COLORKEY.get(randomNum)));
		
		randomNum  = rand.nextInt(5);
		masterCode.add(2, COLORKEY.get(randomNum));
		mc2.setFill(Color.valueOf(COLORKEY.get(randomNum)));
		
		randomNum  = rand.nextInt(5);
		masterCode.add(3, COLORKEY.get(randomNum));
		mc3.setFill(Color.valueOf(COLORKEY.get(randomNum)));
		
	}
	
	/*********************************************************************************************************
	 * Name: won
	 * Description: displays won case
	 * Calls: N/A
	 * Called: N/A
	 */
	void won() {
		mcPane.setVisible(true);
		mcLabel.setVisible(true);
		mcMessage.setVisible(true);
		mcMessage.setText("YOU WON!");
	}
	
/*********************************************************************************************************
* Name: lost
* Description: Displays lost case
* Calls: N/A
* Called: N/A
*/
	void lost() {
		mcPane.setVisible(true);
		mcLabel.setVisible(true);
		mcMessage.setVisible(true);
		mcMessage.setText("YOU LOST :( ");
		lockButton.setDisable(true);
	}
	
/*********************************************************************************************************
* Name: initialize
* Description: Initial function to run before the game is played.
* Calls: N/A
* Called: N/A
*/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		randomMasterCode(); // call the function to set the MASTER CODE
		
		//Input Circles
		ArrayList<Circle> row1 = new ArrayList<Circle>(Arrays.asList(c10,c20,c30,c40));
		ArrayList<Circle> row2 = new ArrayList<Circle>(Arrays.asList(c11,c21,c31,c41));
		ArrayList<Circle> row3 = new ArrayList<Circle>(Arrays.asList(c12,c22,c32,c42));
		ArrayList<Circle> row4 = new ArrayList<Circle>(Arrays.asList(c13,c23,c33,c43));
		ArrayList<Circle> row5 = new ArrayList<Circle>(Arrays.asList(c14,c24,c34,c44));	
		
		// 2D Input Circles
		inputCircles = new ArrayList<ArrayList<Circle> >(Arrays.asList(row1,row2,row3,row4,row5));
		
		
		//MastreCose circles
		mcList = new ArrayList <Circle>(Arrays.asList(mc0,mc1,mc2,mc3));
		
		//Color Keys
		ArrayList<Circle> colorKeys = new ArrayList(Arrays.asList(key1,key2,key3,key4,key5,key6));
		
				



		
		//Allow the blue circle to be dragged
		key1.setOnDragDetected(e ->{
			//Make the item dragable
			Dragboard db = key1.startDragAndDrop(TransferMode.MOVE);
			
			//Create a clipboard to store the information we want to drag
	    	ClipboardContent content = new ClipboardContent();
	    	
	    	//attach the color of the circle as a string to th clipboard
	    	content.putString(key1.getFill().toString());
	    	
	    	//put the clipboard in the dragboard
	    	db.setContent(content);
	    	
	    	//close the event
	    	e.consume();
		});
		
		
		
		key2.setOnDragDetected(e ->{
			Dragboard db = key2.startDragAndDrop(TransferMode.MOVE);
	    	ClipboardContent content = new ClipboardContent();
	    	content.putString(key2.getFill().toString());
	    	db.setContent(content);
	    	e.consume();
		});
		
		
		
		key3.setOnDragDetected(e ->{
			Dragboard db = key3.startDragAndDrop(TransferMode.MOVE);
	    	ClipboardContent content = new ClipboardContent();
	    	content.putString(key3.getFill().toString());
	    	db.setContent(content);
	    	e.consume();
		});
		
		
		
		key4.setOnDragDetected(e ->{
			Dragboard db = key4.startDragAndDrop(TransferMode.MOVE);
	    	ClipboardContent content = new ClipboardContent();
	    	content.putString(key4.getFill().toString());
	    	db.setContent(content);
	    	e.consume();
		});
		
		
		
		key5.setOnDragDetected(e ->{
			Dragboard db = key5.startDragAndDrop(TransferMode.MOVE);
	    	ClipboardContent content = new ClipboardContent();
	    	content.putString(key5.getFill().toString());
	    	db.setContent(content);
	    	e.consume();
		});
		
		
		
		key6.setOnDragDetected(e ->{
			Dragboard db = key6.startDragAndDrop(TransferMode.MOVE);
	    	ClipboardContent content = new ClipboardContent();
	    	content.putString(key6.getFill().toString());
	    	db.setContent(content);
	    	e.consume();
		});
		
		
		
		inputCircles.get(rowCounter).get(0).setOnDragOver(e -> {
			e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	    	e.consume();
		});
		
		
		
		inputCircles.get(rowCounter).get(0).setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();
			boolean success = false;
	        if (db.hasString()) {
	        	inputCircles.get(rowCounter).get(0).setFill(Color.valueOf(db.getString()));
	            success = true;
	        }
	        e.setDropCompleted(success);
	        
	        e.consume();
		});
		
		inputCircles.get(rowCounter).get(0).setOnMouseClicked(e -> {
			inputCircles.get(rowCounter).get(0).setFill(Color.valueOf("#000000"));
			e.consume();
		});
		
		inputCircles.get(rowCounter).get(1).setOnDragOver(e -> {
			e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	    	e.consume();
		});
		
		
		inputCircles.get(rowCounter).get(1).setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();
			boolean success = false;
	        if (db.hasString()) {
	        	inputCircles.get(rowCounter).get(1).setFill(Color.valueOf(db.getString()));
	            success = true;
	        }
	        e.setDropCompleted(success);
	        
	        e.consume();
		});
		
		inputCircles.get(rowCounter).get(1).setOnMouseClicked(e -> {
			inputCircles.get(rowCounter).get(1).setFill(Color.valueOf("#000000"));
		});
		
		inputCircles.get(rowCounter).get(2).setOnDragOver(e -> {
			e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	    	e.consume();
		});
		
		
		inputCircles.get(rowCounter).get(2).setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();
			boolean success = false;
	        if (db.hasString()) {
	        	inputCircles.get(rowCounter).get(2).setFill(Color.valueOf(db.getString()));
	            success = true;
	        }
	        e.setDropCompleted(success);
	        
	        e.consume();
		});
		
		inputCircles.get(rowCounter).get(2).setOnMouseClicked(e -> {
			inputCircles.get(rowCounter).get(2).setFill(Color.valueOf("##000000"));
		});
		
		inputCircles.get(rowCounter).get(3).setOnDragOver(e -> {
			e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
	    	e.consume();
		});
		
		
		inputCircles.get(rowCounter).get(3).setOnDragDropped(e -> {
			Dragboard db = e.getDragboard();
			boolean success = false;
	        if (db.hasString()) {
	        	inputCircles.get(rowCounter).get(3).setFill(Color.valueOf(db.getString()));
	            success = true;
	        }
	        e.setDropCompleted(success);
	        
	        e.consume();
		});
		
		inputCircles.get(rowCounter).get(3).setOnMouseClicked(e -> {
			inputCircles.get(rowCounter).get(3).setFill(Color.valueOf("##000000"));
		}); 
		
	}

	

	
}
