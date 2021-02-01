/*  EBookGen - a simple application to generate an epub template.
 *
 *  Copyright 2021 Philip Lockett.
 *
 *  This file is part of PTDesigner.
 *
 *  EBookGen is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  EBookGen is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with EBookGen.  If not, see <https://www.gnu.org/licenses/>.
 */

/*
 * MainController is a class that is responsible for centralizing control. It
 * creates the Model and provides access between all the tabs via a callback 
 * mechanism.
 */
package phillockett65.EBookGen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class MainController {

    // Details.
    @FXML    private Label datAuthorName;
    @FXML    private Label datSortName;
    @FXML    private TextField txtGivenNames;
    @FXML    private TextField txtFamilyName;
    @FXML    private TextField txtBookTitle;
    @FXML    private TextField txtBookIdentifier;
    @FXML    private ChoiceBox cbxIdentifierType;
    @FXML    private ChoiceBox cbxLanguage;

    // Front Matter.
    @FXML    private CheckBox ckbHalfTitle;
    @FXML    private CheckBox ckbFrontispiece;
    @FXML    private CheckBox ckbTitlePage;
    @FXML    private CheckBox ckbCopyright;
    @FXML    private CheckBox ckbDedication;
    @FXML    private CheckBox ckbEpigraph;
    @FXML    private CheckBox ckbTableOfContents;
    @FXML    private CheckBox ckbForeword;
    @FXML    private CheckBox ckbPreface;
    @FXML    private CheckBox ckbAcknowledgments;
    @FXML    private CheckBox ckbIntroduction;
    @FXML    private CheckBox ckbPrologue;

    // Body Matter.
    @FXML    private TableView<Chapter> tblChapters;
    @FXML    private TableColumn<Chapter, Integer> colChapterNumber;
    @FXML    private TableColumn<Chapter, String> colChapterTitle;

    // Back Matter.
    @FXML    private CheckBox ckbEpilogue;
    @FXML    private CheckBox ckbOutro;
    @FXML    private CheckBox ckbAfterword;
    @FXML    private CheckBox ckbConclusion;
    @FXML    private CheckBox ckbPostscript;
    @FXML    private CheckBox ckbAppendix;
    @FXML    private CheckBox ckbGlossary;
    @FXML    private CheckBox ckbBibliography;
    @FXML    private CheckBox ckbIndex;
    @FXML    private CheckBox ckbBiography;
    @FXML    private CheckBox ckbColophon;
    @FXML    private CheckBox ckbPostface;

    private ObservableList<String> listIdentifierTypes = FXCollections.observableArrayList("ISDN", "URL");
	private ObservableList<String> listLanguages = FXCollections.observableArrayList("UNDEFINED");
	private ObservableList<Chapter> listChapters = FXCollections.observableArrayList();

	private Model model;


    @FXML
    void actionAppend(ActionEvent event) {
    	model.incChapterCount();
		final int CHAPS = model.getChapterCount();
		listChapters.add(new Chapter(CHAPS, "Chapter " + CHAPS));

//        System.out.println("actionAppend: " + CHAPS);
    }

    @FXML
    void actionGenerate(ActionEvent event) {
        System.out.println("actionGenerate: " + event.toString());
    }

    @FXML
    void actionRemove(ActionEvent event) {
    	model.decChapterCount();
		final int CHAPS = model.getChapterCount();
		listChapters.add(new Chapter(CHAPS, "Chapter " + CHAPS));

//        System.out.println("actionRemove: " + CHAPS);
    }

    private void refreshNames() {
		datAuthorName.setText(model.getAuthorName());
		datSortName.setText(model.getSortName());
    }

    void changedFamilyName() {
    	model.setFamilyName(txtFamilyName.getText());
    	refreshNames();
    }

    void changedGivenName() {
    	model.setGivenNames(txtGivenNames.getText());
    	refreshNames();
    }



    @FXML
    void keyChangedFamilyName(KeyEvent event) {
    	changedFamilyName();
    }

    @FXML
    void keyChangedGivenName(KeyEvent event) {
    	changedGivenName();
    }
    /**
	 * Called by the FXML mechanism to initialize the controller. Creates a 
	 * callback link for all the tab controllers.
	 */
	@FXML public void initialize() {
//		System.out.println("MainController initialized.");
		// Set up Language Selector here because we need acces to main.
		int i = 0;
		int index = 0;
		listLanguages.clear();
		for (String item : model.getLanguageSet()) {
			listLanguages.add(item);
			if (item.compareTo("English") == 0)
				index = i;
			i++;
		}
		cbxLanguage.setItems(listLanguages);
		cbxLanguage.getSelectionModel().select(index);

	    txtBookTitle.setText(model.getTitle());
	    txtGivenNames.setText(model.getGivenNames());
	    txtFamilyName.setText(model.getFamilyName());
	    refreshNames();
	    txtBookIdentifier.setText(model.getIdentifier());

		cbxIdentifierType.setItems(listIdentifierTypes);
		cbxIdentifierType.getSelectionModel().select(0);

		colChapterNumber.setText("Id");
		colChapterNumber.setCellValueFactory(new PropertyValueFactory<>("identifier"));
		colChapterTitle.setText("Chapter Heading");
		colChapterTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

		listChapters.clear();
		final int CHAPS = model.getChapterCount();
		for (i = 1; i <= CHAPS; ++i) {
			listChapters.add(new Chapter(i, "Chapter " + i));
		}
		tblChapters.setItems(listChapters);
	}

	/**
	 * Constructor.
	 * 
	 * Responsible for creating the Model.
	 */
	public MainController() {
//		System.out.println("MainController constructed.");
		model = new Model();
	}

}
