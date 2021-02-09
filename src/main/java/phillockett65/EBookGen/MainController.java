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
		final int id = Integer.parseInt(cb.getId());
        model.setCheckBox(id, cb.isSelected());
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

    private void initCheckBox(CheckBox cb, int id) {
		cb.setId(Integer.toString(id));
    	cb.setSelected(model.isCheckBox(id));
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

		// Set up the Check Boxes - MUST BE FIRST THING DONE WITH CHECBOX.
		initCheckBox(ckbHalfTitle, Model.CKB_HALFTITLE);
		initCheckBox(ckbFrontispiece, Model.CKB_FRONTISPIECE);
		initCheckBox(ckbTitlePage, Model.CKB_TITLEPAGE);
		initCheckBox(ckbCopyright, Model.CKB_COPYRIGHT);
		initCheckBox(ckbDedication, Model.CKB_DEDICATION);
		initCheckBox(ckbEpigraph, Model.CKB_EPIGRAPH);
		initCheckBox(ckbTableOfContents, Model.CKB_TABLEOFCONTENTS);
		initCheckBox(ckbForeword, Model.CKB_FOREWORD);
		initCheckBox(ckbPreface, Model.CKB_PREFACE);
		initCheckBox(ckbAcknowledgments, Model.CKB_ACKNOWLEDGMENTS);
		initCheckBox(ckbIntroduction, Model.CKB_INTRODUCTION);
		initCheckBox(ckbPrologue, Model.CKB_PROLOGUE);
		initCheckBox(ckbEpilogue, Model.CKB_EPILOGUE);
		initCheckBox(ckbOutro, Model.CKB_OUTRO);
		initCheckBox(ckbAfterword, Model.CKB_AFTERWORD);
		initCheckBox(ckbConclusion, Model.CKB_CONCLUSION);
		initCheckBox(ckbPostscript, Model.CKB_POSTSCRIPT);
		initCheckBox(ckbAppendix, Model.CKB_APPENDIX);
		initCheckBox(ckbGlossary, Model.CKB_GLOSSARY);
		initCheckBox(ckbBibliography, Model.CKB_BIBLIOGRAPHY);
		initCheckBox(ckbIndex, Model.CKB_INDEX);
		initCheckBox(ckbBiography, Model.CKB_BIOGRAPHY);
		initCheckBox(ckbColophon, Model.CKB_COLOPHON);
		initCheckBox(ckbPostface, Model.CKB_POSTFACE);

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
