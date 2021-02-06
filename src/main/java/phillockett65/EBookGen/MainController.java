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
        int cbid = -1;
        if (cb.equals(ckbHalfTitle))
        	cbid = Model.CKB_HALFTITLE;
        else if (cb.equals(ckbFrontispiece))
        	cbid = Model.CKB_FRONTISPIECE;
    	else if (cb.equals(ckbFrontispiece))
    		cbid = Model.CKB_FRONTISPIECE;
    	else if (cb.equals(ckbTitlePage))
    		cbid = Model.CKB_TITLEPAGE;
    	else if (cb.equals(ckbCopyright))
    		cbid = Model.CKB_COPYRIGHT;
    	else if (cb.equals(ckbDedication))
    		cbid = Model.CKB_DEDICATION;
    	else if (cb.equals(ckbEpigraph))
    		cbid = Model.CKB_EPIGRAPH;
    	else if (cb.equals(ckbTableOfContents))
    		cbid = Model.CKB_TABLEOFCONTENTS;
    	else if (cb.equals(ckbForeword))
    		cbid = Model.CKB_FOREWORD;
    	else if (cb.equals(ckbPreface))
    		cbid = Model.CKB_PREFACE;
    	else if (cb.equals(ckbAcknowledgments))
    		cbid = Model.CKB_ACKNOWLEDGMENTS;
    	else if (cb.equals(ckbIntroduction))
    		cbid = Model.CKB_INTRODUCTION;
    	else if (cb.equals(ckbPrologue))
    		cbid = Model.CKB_PROLOGUE;
    	else if (cb.equals(ckbEpilogue))
    		cbid = Model.CKB_EPILOGUE;
    	else if (cb.equals(ckbOutro))
    		cbid = Model.CKB_OUTRO;
    	else if (cb.equals(ckbAfterword))
    		cbid = Model.CKB_AFTERWORD;
    	else if (cb.equals(ckbConclusion))
    		cbid = Model.CKB_CONCLUSION;
    	else if (cb.equals(ckbPostscript))
    		cbid = Model.CKB_POSTSCRIPT;
    	else if (cb.equals(ckbAppendix))
    		cbid = Model.CKB_APPENDIX;
    	else if (cb.equals(ckbGlossary))
    		cbid = Model.CKB_GLOSSARY;
    	else if (cb.equals(ckbBibliography))
    		cbid = Model.CKB_BIBLIOGRAPHY;
    	else if (cb.equals(ckbIndex))
    		cbid = Model.CKB_INDEX;
    	else if (cb.equals(ckbBiography))
    		cbid = Model.CKB_BIOGRAPHY;
    	else if (cb.equals(ckbColophon))
    		cbid = Model.CKB_COLOPHON;
    	else if (cb.equals(ckbPostface))
    		cbid = Model.CKB_POSTFACE;

        model.setCheckBox(cbid, cb.isSelected());
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

		// Set up the Check Boxes.
		ckbHalfTitle.setSelected(model.isCheckBox(Model.CKB_HALFTITLE));
		ckbFrontispiece.setSelected(model.isCheckBox(Model.CKB_FRONTISPIECE));
		ckbTitlePage.setSelected(model.isCheckBox(Model.CKB_TITLEPAGE));
		ckbCopyright.setSelected(model.isCheckBox(Model.CKB_COPYRIGHT));
		ckbDedication.setSelected(model.isCheckBox(Model.CKB_DEDICATION));
		ckbEpigraph.setSelected(model.isCheckBox(Model.CKB_EPIGRAPH));
		ckbTableOfContents.setSelected(model.isCheckBox(Model.CKB_TABLEOFCONTENTS));
		ckbForeword.setSelected(model.isCheckBox(Model.CKB_FOREWORD));
		ckbPreface.setSelected(model.isCheckBox(Model.CKB_PREFACE));
		ckbAcknowledgments.setSelected(model.isCheckBox(Model.CKB_ACKNOWLEDGMENTS));
		ckbIntroduction.setSelected(model.isCheckBox(Model.CKB_INTRODUCTION));
		ckbPrologue.setSelected(model.isCheckBox(Model.CKB_PROLOGUE));
		ckbEpilogue.setSelected(model.isCheckBox(Model.CKB_EPILOGUE));
		ckbOutro.setSelected(model.isCheckBox(Model.CKB_OUTRO));
		ckbAfterword.setSelected(model.isCheckBox(Model.CKB_AFTERWORD));
		ckbConclusion.setSelected(model.isCheckBox(Model.CKB_CONCLUSION));
		ckbPostscript.setSelected(model.isCheckBox(Model.CKB_POSTSCRIPT));
		ckbAppendix.setSelected(model.isCheckBox(Model.CKB_APPENDIX));
		ckbGlossary.setSelected(model.isCheckBox(Model.CKB_GLOSSARY));
		ckbBibliography.setSelected(model.isCheckBox(Model.CKB_BIBLIOGRAPHY));
		ckbIndex.setSelected(model.isCheckBox(Model.CKB_INDEX));
		ckbBiography.setSelected(model.isCheckBox(Model.CKB_BIOGRAPHY));
		ckbColophon.setSelected(model.isCheckBox(Model.CKB_COLOPHON));
		ckbPostface.setSelected(model.isCheckBox(Model.CKB_POSTFACE));
		
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
