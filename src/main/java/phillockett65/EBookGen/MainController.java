/*  EBookGen - a simple application to generate an epub template.
 *
 *  Copyright 2021 Philip Lockett.
 *
 *  This file is part of EBookGen.
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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;

public class MainController {

    // Details.
    @FXML    private Label datAuthorName;
    @FXML    private Label datSortName;
    @FXML    private TextField txtGivenNames;
    @FXML    private TextField txtFamilyName;
    @FXML    private TextField txtBookTitle;
    @FXML    private TextField txtBookIdentifier;
    @FXML    private ChoiceBox<String> cbxIdentifierType;
    @FXML    private ChoiceBox<String> cbxLanguage;
    @FXML    private TextField txtPublisher;
    @FXML    private TextField txtYear;

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

	private Model model;


    @FXML
    void actionAppend(ActionEvent event) {
//      System.out.println("actionAppend()");
        model.addChapter();
    }

    @FXML
    void actionGenerate(ActionEvent event) {
//        System.out.println("actionGenerate: " + event.toString());
        // Get Choice Box selections.
        model.setLanguage(cbxLanguage.getValue().toString());
        model.setIdentifierType(cbxIdentifierType.getValue().toString());
        model.setIdentifier(txtBookIdentifier.getText());
        model.setPublisher(txtPublisher.getText());
        model.setYear(txtYear.getText());
        model.generate();
    }

    @FXML
    void actionRemove(ActionEvent event) {
//      System.out.println("actionRemove()");
        model.removeChapter();
    }

    @FXML
    void actionCheckBox(ActionEvent event) {
//        System.out.println("actionGenerate: " + event.toString());

        CheckBox cb = (CheckBox) event.getSource();
        model.setCheckBox(cb);
    }
    private void updateCombinedNameLabels() {
		datAuthorName.setText(model.getAuthorName());
		datSortName.setText(model.getSortName());
    }

    private void changedFamilyName() {
    	model.setFamilyName(txtFamilyName.getText());
    	updateCombinedNameLabels();
    }

    private void changedGivenName() {
    	model.setGivenNames(txtGivenNames.getText());
    	updateCombinedNameLabels();
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
		// Set up Language Selector here because we need access to main.
		cbxLanguage.setItems(model.getListLanguages());
		cbxLanguage.getSelectionModel().select(model.getInitialLanguageMapIndex());

		// Set up Identifier Types Selector here because we need access to main.
		cbxIdentifierType.setItems(model.getListIdentifierTypes());
		cbxIdentifierType.getSelectionModel().select(model.getInitialIdTypeMapIndex());

		// Initialize the fields.
		txtBookTitle.setText(model.getTitle());
		txtGivenNames.setText(model.getGivenNames());
		txtFamilyName.setText(model.getFamilyName());
		txtBookIdentifier.setText(model.getIdentifier());
		txtPublisher.setText(model.getPublisher());
		txtYear.setText(model.getYear());
		updateCombinedNameLabels();

		// Set up the Columns and Table for the Chapters.
		colChapterNumber.setCellValueFactory(new PropertyValueFactory<>("identifier"));
		colChapterTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		tblChapters.setItems(model.getListChapters());

		// Make Chapter Title column editable. Note editable property has been set in the FXML.
		colChapterTitle.setCellFactory(TextFieldTableCell.forTableColumn());
		colChapterTitle.setOnEditCommit(e -> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setTitle(e.getNewValue());
		});

		// Set up the Check Box ids - MUST BE FIRST THING DONE WITH CHECBOX.
		ckbHalfTitle.setId(Integer.toString(Model.CKB_HALFTITLE));
		ckbFrontispiece.setId(Integer.toString(Model.CKB_FRONTISPIECE));
		ckbTitlePage.setId(Integer.toString(Model.CKB_TITLEPAGE));
		ckbCopyright.setId(Integer.toString(Model.CKB_COPYRIGHT));
		ckbDedication.setId(Integer.toString(Model.CKB_DEDICATION));
		ckbEpigraph.setId(Integer.toString(Model.CKB_EPIGRAPH));
		ckbTableOfContents.setId(Integer.toString(Model.CKB_TABLEOFCONTENTS));
		ckbForeword.setId(Integer.toString(Model.CKB_FOREWORD));
		ckbPreface.setId(Integer.toString(Model.CKB_PREFACE));
		ckbAcknowledgments.setId(Integer.toString(Model.CKB_ACKNOWLEDGMENTS));
		ckbIntroduction.setId(Integer.toString(Model.CKB_INTRODUCTION));
		ckbPrologue.setId(Integer.toString(Model.CKB_PROLOGUE));
		ckbEpilogue.setId(Integer.toString(Model.CKB_EPILOGUE));
		ckbOutro.setId(Integer.toString(Model.CKB_OUTRO));
		ckbAfterword.setId(Integer.toString(Model.CKB_AFTERWORD));
		ckbConclusion.setId(Integer.toString(Model.CKB_CONCLUSION));
		ckbPostscript.setId(Integer.toString(Model.CKB_POSTSCRIPT));
		ckbAppendix.setId(Integer.toString(Model.CKB_APPENDIX));
		ckbGlossary.setId(Integer.toString(Model.CKB_GLOSSARY));
		ckbBibliography.setId(Integer.toString(Model.CKB_BIBLIOGRAPHY));
		ckbIndex.setId(Integer.toString(Model.CKB_INDEX));
		ckbBiography.setId(Integer.toString(Model.CKB_BIOGRAPHY));
		ckbColophon.setId(Integer.toString(Model.CKB_COLOPHON));
		ckbPostface.setId(Integer.toString(Model.CKB_POSTFACE));

		// Initialize the Check Box state with data from the model.
		ckbHalfTitle.setSelected(model.isCheckBox(ckbHalfTitle));
		ckbFrontispiece.setSelected(model.isCheckBox(ckbFrontispiece));
		ckbTitlePage.setSelected(model.isCheckBox(ckbTitlePage));
		ckbCopyright.setSelected(model.isCheckBox(ckbCopyright));
		ckbDedication.setSelected(model.isCheckBox(ckbDedication));
		ckbEpigraph.setSelected(model.isCheckBox(ckbEpigraph));
		ckbTableOfContents.setSelected(model.isCheckBox(ckbTableOfContents));
		ckbForeword.setSelected(model.isCheckBox(ckbForeword));
		ckbPreface.setSelected(model.isCheckBox(ckbPreface));
		ckbAcknowledgments.setSelected(model.isCheckBox(ckbAcknowledgments));
		ckbIntroduction.setSelected(model.isCheckBox(ckbIntroduction));
		ckbPrologue.setSelected(model.isCheckBox(ckbPrologue));
		ckbEpilogue.setSelected(model.isCheckBox(ckbEpilogue));
		ckbOutro.setSelected(model.isCheckBox(ckbOutro));
		ckbAfterword.setSelected(model.isCheckBox(ckbAfterword));
		ckbConclusion.setSelected(model.isCheckBox(ckbConclusion));
		ckbPostscript.setSelected(model.isCheckBox(ckbPostscript));
		ckbAppendix.setSelected(model.isCheckBox(ckbAppendix));
		ckbGlossary.setSelected(model.isCheckBox(ckbGlossary));
		ckbBibliography.setSelected(model.isCheckBox(ckbBibliography));
		ckbIndex.setSelected(model.isCheckBox(ckbIndex));
		ckbBiography.setSelected(model.isCheckBox(ckbBiography));
		ckbColophon.setSelected(model.isCheckBox(ckbColophon));
		ckbPostface.setSelected(model.isCheckBox(ckbPostface));
		
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
