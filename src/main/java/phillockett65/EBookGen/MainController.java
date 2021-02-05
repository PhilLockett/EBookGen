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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    @FXML    private ChoiceBox cbxIdentifierType;
    @FXML    private ChoiceBox cbxLanguage;
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
        	cbid = model.CKB_HALFTITLE;
        else if (cb.equals(ckbFrontispiece))
        	cbid = model.CKB_FRONTISPIECE;
    	else if (cb.equals(ckbFrontispiece))
    		cbid = model.CKB_FRONTISPIECE;
    	else if (cb.equals(ckbTitlePage))
    		cbid = model.CKB_TITLEPAGE;
    	else if (cb.equals(ckbCopyright))
    		cbid = model.CKB_COPYRIGHT;
    	else if (cb.equals(ckbDedication))
    		cbid = model.CKB_DEDICATION;
    	else if (cb.equals(ckbEpigraph))
    		cbid = model.CKB_EPIGRAPH;
    	else if (cb.equals(ckbTableOfContents))
    		cbid = model.CKB_TABLEOFCONTENTS;
    	else if (cb.equals(ckbForeword))
    		cbid = model.CKB_FOREWORD;
    	else if (cb.equals(ckbPreface))
    		cbid = model.CKB_PREFACE;
    	else if (cb.equals(ckbAcknowledgments))
    		cbid = model.CKB_ACKNOWLEDGMENTS;
    	else if (cb.equals(ckbIntroduction))
    		cbid = model.CKB_INTRODUCTION;
    	else if (cb.equals(ckbPrologue))
    		cbid = model.CKB_PROLOGUE;
    	else if (cb.equals(ckbEpilogue))
    		cbid = model.CKB_EPILOGUE;
    	else if (cb.equals(ckbOutro))
    		cbid = model.CKB_OUTRO;
    	else if (cb.equals(ckbAfterword))
    		cbid = model.CKB_AFTERWORD;
    	else if (cb.equals(ckbConclusion))
    		cbid = model.CKB_CONCLUSION;
    	else if (cb.equals(ckbPostscript))
    		cbid = model.CKB_POSTSCRIPT;
    	else if (cb.equals(ckbAppendix))
    		cbid = model.CKB_APPENDIX;
    	else if (cb.equals(ckbGlossary))
    		cbid = model.CKB_GLOSSARY;
    	else if (cb.equals(ckbBibliography))
    		cbid = model.CKB_BIBLIOGRAPHY;
    	else if (cb.equals(ckbIndex))
    		cbid = model.CKB_INDEX;
    	else if (cb.equals(ckbBiography))
    		cbid = model.CKB_BIOGRAPHY;
    	else if (cb.equals(ckbColophon))
    		cbid = model.CKB_COLOPHON;
    	else if (cb.equals(ckbPostface))
    		cbid = model.CKB_POSTFACE;

        model.setCheckBox(cbid, cb.isSelected());
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
		// Set up Language Selector here because we need access to main.
		cbxLanguage.setItems(model.getListLanguages());
		cbxLanguage.getSelectionModel().select(model.getInitialLanguageMapIndex());

		// Set up Identifier Types Selector here because we need access to main.
		cbxIdentifierType.setItems(model.getListIdentifierTypes());
		cbxIdentifierType.getSelectionModel().select(model.getInitialIdTypeMapIndex());

	    txtBookTitle.setText(model.getTitle());
	    txtGivenNames.setText(model.getGivenNames());
	    txtFamilyName.setText(model.getFamilyName());
	    refreshNames();
	    txtBookIdentifier.setText(model.getIdentifier());

		colChapterNumber.setText("Id");
		colChapterNumber.setCellValueFactory(new PropertyValueFactory<>("identifier"));
		colChapterTitle.setText("Chapter Heading");
		colChapterTitle.setCellValueFactory(new PropertyValueFactory<>("title"));

		tblChapters.setItems(model.getListChapters());

		// Set up the Check Boxes.
		ckbHalfTitle.setSelected(model.isCheckBox(model.CKB_HALFTITLE));
		ckbFrontispiece.setSelected(model.isCheckBox(model.CKB_FRONTISPIECE));
		ckbTitlePage.setSelected(model.isCheckBox(model.CKB_TITLEPAGE));
		ckbCopyright.setSelected(model.isCheckBox(model.CKB_COPYRIGHT));
		ckbDedication.setSelected(model.isCheckBox(model.CKB_DEDICATION));
		ckbEpigraph.setSelected(model.isCheckBox(model.CKB_EPIGRAPH));
		ckbTableOfContents.setSelected(model.isCheckBox(model.CKB_TABLEOFCONTENTS));
		ckbForeword.setSelected(model.isCheckBox(model.CKB_FOREWORD));
		ckbPreface.setSelected(model.isCheckBox(model.CKB_PREFACE));
		ckbAcknowledgments.setSelected(model.isCheckBox(model.CKB_ACKNOWLEDGMENTS));
		ckbIntroduction.setSelected(model.isCheckBox(model.CKB_INTRODUCTION));
		ckbPrologue.setSelected(model.isCheckBox(model.CKB_PROLOGUE));
		ckbEpilogue.setSelected(model.isCheckBox(model.CKB_EPILOGUE));
		ckbOutro.setSelected(model.isCheckBox(model.CKB_OUTRO));
		ckbAfterword.setSelected(model.isCheckBox(model.CKB_AFTERWORD));
		ckbConclusion.setSelected(model.isCheckBox(model.CKB_CONCLUSION));
		ckbPostscript.setSelected(model.isCheckBox(model.CKB_POSTSCRIPT));
		ckbAppendix.setSelected(model.isCheckBox(model.CKB_APPENDIX));
		ckbGlossary.setSelected(model.isCheckBox(model.CKB_GLOSSARY));
		ckbBibliography.setSelected(model.isCheckBox(model.CKB_BIBLIOGRAPHY));
		ckbIndex.setSelected(model.isCheckBox(model.CKB_INDEX));
		ckbBiography.setSelected(model.isCheckBox(model.CKB_BIOGRAPHY));
		ckbColophon.setSelected(model.isCheckBox(model.CKB_COLOPHON));
		ckbPostface.setSelected(model.isCheckBox(model.CKB_POSTFACE));
		
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
