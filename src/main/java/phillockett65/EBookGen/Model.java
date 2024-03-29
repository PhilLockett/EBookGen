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
 * Model is a class that captures the dynamic shared data plus some supporting 
 * constants and provides access via getters and setters.
 */
package phillockett65.EBookGen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Model {

	public final static int INITIAL_CHAPTER_COUNT = 20;

	public final static int CKB_HALFTITLE = 0;
	public final static int CKB_FRONTISPIECE = 1;
	public final static int CKB_TITLEPAGE = 2;
	public final static int CKB_COPYRIGHT = 3;
	public final static int CKB_DEDICATION = 4;
	public final static int CKB_EPIGRAPH = 5;
	public final static int CKB_TABLEOFCONTENTS = 6;
	public final static int CKB_FOREWORD = 7;
	public final static int CKB_PREFACE = 8;
	public final static int CKB_ACKNOWLEDGMENTS = 9;
	public final static int CKB_INTRODUCTION = 10;
	public final static int CKB_PROLOGUE = 11;
	public final static int CKB_EPILOGUE = 12;
	public final static int CKB_OUTRO = 13;
	public final static int CKB_AFTERWORD = 14;
	public final static int CKB_CONCLUSION = 15;
	public final static int CKB_POSTSCRIPT = 16;
	public final static int CKB_APPENDIX = 17;
	public final static int CKB_GLOSSARY = 18;
	public final static int CKB_BIBLIOGRAPHY = 19;
	public final static int CKB_INDEX = 20;
	public final static int CKB_BIOGRAPHY = 21;
	public final static int CKB_COLOPHON = 22;
	public final static int CKB_POSTFACE = 23;
	public final static int CKB_CHECK_BOX_COUNT = 24;		// Number of Check Boxes.
	
	private boolean[] checkBoxes = new boolean[CKB_CHECK_BOX_COUNT];

	public final static int ADD_CONTENTS = 1;
	public final static int ADD_NAVMAP = 2;
	public final static int ADD_MANIFEST = 4;
	public final static int ADD_SPINE = 8;

	public final static String MT_XHTML ="application/xhtml+xml";
	public final static String MT_CSS ="text/css";
	public final static String MT_JPG ="image/jpeg";
	public final static String MT_NCX ="application/x-dtbncx+xml";
	
	public final static int ISBN = 0;
	public final static int UUID = 1;
	public final static int DOI = 2;
	public final static int JDCN = 3;

	private String familyName = "Dickens";
	private String givenNames = "Charles";
	private String title = "Oliver Twist";

	private String identifier = "isbn-123-1-12-123456-1";
	private String publisher = "Handmade Books";
	private String year = "2021";

	private int chapterCount = INITIAL_CHAPTER_COUNT;

	private ArrayList<String> contents = new ArrayList<>();
	private ArrayList<String> navMap = new ArrayList<>();
	private ArrayList<String> manifest = new ArrayList<>();
	private ArrayList<String> spine = new ArrayList<>();

	private Map<String, String> mapLang = new TreeMap<>();
	private ObservableList<String> listIdentifierTypes = FXCollections.observableArrayList("UNDEFINED");
	private String language = "English";
	private int initialLanguageMapIndex = 0;

	private Map<String, Integer> mapIdType = new TreeMap<>();
	private ObservableList<String> listLanguages = FXCollections.observableArrayList("UNDEFINED");
	private String identifierType = "ISBN";
	private int initialIdTypeMapIndex = 0;

	private ObservableList<Chapter> listChapters = FXCollections.observableArrayList();


	/**
	 * Getters and Setters.
	 */

	public ObservableList<Chapter> getListChapters() {
		return listChapters;
	}
	public int addChapter() {
		chapterCount++;
		listChapters.add(new Chapter(chapterCount, "Chapter " + chapterCount));

		return chapterCount;
	}
	public int removeChapter() {
		chapterCount--;
		listChapters.remove(chapterCount);

		return chapterCount;
	}

	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public int getInitialIdTypeMapIndex() {
		return initialIdTypeMapIndex;
	}
	public ObservableList<String> getListIdentifierTypes() {
		return listIdentifierTypes;
	}
	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	public int getInitialLanguageMapIndex() {
		return initialLanguageMapIndex;
	}
	public ObservableList<String> getListLanguages() {
		return listLanguages;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getGivenNames() {
		return givenNames;
	}
	public void setGivenNames(String givenNames) {
		this.givenNames = givenNames;
	}
	public String getAuthorName() {
		return givenNames + " " + familyName;
	}
	public String getSortName() {
		return familyName + ", " + givenNames;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}

	public boolean isCheckBox(int id) {
//		System.out.println("isCheckBox(" + id + ") -> " + checkBoxes[id]);
		return checkBoxes[id];
	}
	public void setCheckBox(int id, boolean state) {
//		System.out.println("setCheckBox(" + id + ", " + state + ")");
		checkBoxes[id] = state;
	}


	/**
	 * Copy and Generate E-Book directories and files.
	 */

	private void copyFile(String sourceFile, String targetDirectory) {
//		System.out.println("copyFile(" + sourceFile + " to " + targetDirectory + "\\" + sourceFile + ")");

		File target = new File(targetDirectory + "/" + sourceFile);
		if (target.exists())
			return;

		String sourcePath = App.class.getResource("copyfiles/" + sourceFile).getFile();
		File source = new File(sourcePath);
		try {
			Files.copy(source.toPath(), target.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void genHeader(BufferedWriter bw, String title, String bodyClass, String divClass) throws IOException {
//		System.out.println("genHeader() " + title);
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
        bw.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
        bw.write("<head>\n");
        bw.write("<title>" + title + "</title>\n");
        bw.write("\n");
        bw.write("<meta content=\"http://www.w3.org/1999/xhtml; charset=utf-8\" http-equiv=\"Content-Type\"/>\n");
        bw.write("<link href=\"stylesheet.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
        bw.write("</head>\n");
        bw.write("\n");
        bw.write("<body class=\"" + bodyClass + "\">\n");
        bw.write("  <div class=\"" + divClass + "\">\n");
        bw.write("\n");
	}

	private void genFooter(BufferedWriter bw) throws IOException {
//		System.out.println("genFooter()");
        bw.write("\n");
        bw.write("  </div>\n");
        bw.write("</body>\n");
        bw.write("</html>\n");
	}

	private void genTitlePage(String target, String path, boolean full) {
		final String file = path + "\\" + target;
//		System.out.println("genTitlePage " + file);
        try (FileWriter writer = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(writer)) {
            genHeader(bw, title, "mainbody", "centeraligntext");

            bw.write("	<h2>\n");
            bw.write("	" + title + "\n");
            bw.write("	</h2>\n");
            if (full) {
                bw.write("\n");
                bw.write("	<br/>\n");
                bw.write("	<br/>\n");
                bw.write("	<div class=\"centeraligntext\">\n");
                bw.write("	<h3>\n");
                bw.write("	by " + getAuthorName() + "\n");
                bw.write("	</h3>\n");
                if (publisher != null && publisher.length() != 0) {
                    bw.write("	<h3>\n");
                    bw.write("	" + publisher + "\n");
                    bw.write("	</h3>\n");
                }
                if (year != null && year.length() != 0) {
                    bw.write("	<h3>\n");
                    bw.write("	" + year + "\n");
                    bw.write("	</h3>\n");
                }
                bw.write("	</div>\n");
            }

            genFooter(bw);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void genCopyrightPage(String target, String path) {
		final String file = path + "\\" + target;
//		System.out.println("genTitlePage " + file);
        try (FileWriter writer = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(writer)) {
            genHeader(bw, "Copyright", "mainbody", "leftaligntext");

            bw.write("	<h1>\n");
            bw.write("	Copyright &#169; " + year + " " + getAuthorName() + ". All Rights Reserved.\n");
            bw.write("	</h1>\n");
            bw.write("\n");
            bw.write("	<br/>\n");
            bw.write("	<br/>\n");
            bw.write("	Note all the information below is optional. Only the H1 tag above is required.\n");
            bw.write("	<br/>\n");
            bw.write("	<br/>\n");
            bw.write("	All rights reserved under the Digital Millennium Copyright Act, the Universal Copyright Convention and the Berne Convention For The Protection Of Literary And Artistic Works. The author of this book, John Doe, secures all rights to this book, including the right to reproduce this book in whole or in part, in any form whatsoever, and extends such privileges to absolutely no other parties, individuals or companies. Not including eBook exemptions of the Digital Millennium Copyright Act, no part of this book may be reproduced or transmitted in any form, in whole or in part, by any electronic, mechanical, or other means including, but not limited to, all existing and yet to be invented information duplicating, storage or retrieval systems, without specific permission in writing from the author, except by a reviewer who may quote brief passages.\n");
            bw.write("	<br/>\n");
            bw.write("	<br/>\n");
            bw.write("	<p>First International eBook Printing: " + year + "</p>\n");

            genFooter(bw);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void genChapterPage(Mother mother, String path) {
		final String file = path + "\\" + mother.getFile();
//		System.out.println("genChapterPage " + file);
        try (FileWriter writer = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(writer)) {
            genHeader(bw, mother.getTitle(), "mainbody", "paragraphtext");

            bw.write("	<h1>\n");
            bw.write("	" + mother.getTitle() + "\n");
            bw.write("	</h1>\n");
            bw.write("	<br/>\n");
            bw.write("	<br/>\n");
            bw.write("	<p>&#8220;Lorem ipsum dolor sit amet&#8221;, consectetuer adipiscing elit. <i>Nunc</i></p><p> If these free tutorial files helped you, show your appreciation by buying a novel or book written by author <a href=\"http://www.EricMuss-Barnes.com\">Eric Muss-Barnes</a> at <a href=\"http://www.DubhSithInk.com\">http://www.DubhSithInk.com</a> and check out his blog at <a href=\"http://www.InkShard.com\">http://www.InkShard.com</a>.</p><p>Cras vitae sem ut mi commodo scelerisque. Nulla tellus dolor, lacinia in, luctus sed, hendrerit id, massa. Sed sollicitudin, orci id fringilla dapibus, nisi massa cursus justo, nec tincidunt arcu sem et diam. Ut vel massa. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. In justo neque, semper cursus, mattis euismod, viverra rhoncus, libero. Cras feugiat, mauris vel dapibus mollis, ipsum dui auctor lectus, in vulputate eros nulla eu dui. In eu augue. Phasellus vel magna. Aliquam feugiat ultrices quam. &#8220;Quisque et nulla.&#8221; Sed varius, turpis sit amet aliquam gravida, sem urna mattis massa, non nonummy mi dolor quis pede. Donec a erat vel lectus condimentum nonummy. Nunc id diam ac odio congue scelerisque. Proin semper pretium mauris. Etiam mollis, nulla eu sodales suscipit, tellus tortor accumsan sapien, in ornare nibh dolor at lorem. Donec varius ultricies ligula. Nullam ornare odio eget justo.</p>\n");
            bw.write("\n");
            bw.write("<p>If these free tutorial files helped you, show your appreciation by buying a novel or book written by author <a href=\"http://www.EricMuss-Barnes.com\">Eric Muss-Barnes</a> at <a href=\"http://www.DubhSithInk.com\">http://www.DubhSithInk.com</a> and check out his blog at <a href=\"http://www.InkShard.com\">http://www.InkShard.com</a>.</p><p>Suspendisse quis diam. Vivamus ullamcorper, neque non tincidunt gravida, lectus turpis fringilla neque, a bibendum ante enim et mi. &#8220;Aliquam erat volutpat.&#8221; &#8220;Aliquam erat volutpat.&#8221; Suspendisse et dui. Cras sit amet risus. Donec pharetra consequat purus. Curabitur massa ante, luctus non, tincidunt et, rhoncus ut, sem. Sed rutrum mi sit amet velit. Sed eget ante. Sed tellus lorem, vehicula vel, mollis quis, venenatis quis, elit. Donec nibh. Ut in odio consequat ipsum commodo pellentesque. In nec quam. Curabitur commodo libero in neque. </p>\n");
            bw.write("\n");
            bw.write("<p>If these free tutorial files helped you, show your appreciation by buying a novel or book written by author <a href=\"http://www.EricMuss-Barnes.com\">Eric Muss-Barnes</a> at <a href=\"http://www.DubhSithInk.com\">http://www.DubhSithInk.com</a> and check out his blog at <a href=\"http://www.InkShard.com\">http://www.InkShard.com</a>.</p><p>DO NOT FORGET TO CLOSE YOUR PARAGRAPH TAGS! This is a requirement in XHTML.. Curabitur nibh justo, posuere condimentum, pulvinar nec, congue ac, urna. Aliquam non dolor at lectus ultrices elementum. Vivamus sodales diam sed massa. Sed vel lectus in nunc luctus viverra. Nulla varius commodo leo. Nullam viverra iaculis ligula. Sed aliquam arcu nec nulla. &#8220;Aliquam erat volutpat.&#8221; Praesent mollis suscipit ante. Curabitur tristique auctor felis. </p>\n");
            bw.write("\n");
            bw.write("<p>Bold can be done with <b>regular bold tags</b> while italics <i>can be done with italics tags</i>.. Ut erat turpis, vehicula et, rutrum quis, tincidunt eu, urna. Vestibulum blandit, neque et laoreet sagittis, leo nunc ultricies metus, at faucibus purus ante sed enim. Duis rutrum. Quisque eleifend erat vel lacus. Maecenas elementum nulla sed erat. Donec vulputate dapibus ligula. Nunc neque pede, malesuada sit amet, ultricies vel, sollicitudin ut, neque. Praesent ultrices nunc at elit. Proin lorem sem, blandit in, pulvinar interdum, cursus a, arcu.</p> \n");
            bw.write("\n");
            bw.write("<ul><li>&#169; (copyright)</li><li>&#167; (section)</li><li>&#8212; (em dash)</li><li>&#8211; (en dash)</li><li>&#8226; (bullet)</li><li>&#8230; (ellipsis)</li></ul>. <p>Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos hymenaeos. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris et libero condimentum mi eleifend tempus. Ut faucibus, arcu eget lacinia placerat, odio neque interdum ante, nec adipiscing justo velit non turpis. Nullam nec est quis massa dictum ornare. Sed est massa, tempor sit amet, tristique nec, fringilla nec, mi. Curabitur sollicitudin dictum tellus. Nunc urna. </p><p>If these free tutorial files helped you, show your appreciation by buying a novel or book written by author <a href=\"http://www.EricMuss-Barnes.com\">Eric Muss-Barnes</a> at <a href=\"http://www.DubhSithInk.com\">http://www.DubhSithInk.com</a> and check out his blog at <a href=\"http://www.InkShard.com\">http://www.InkShard.com</a>.</p>\n");

            genFooter(bw);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void genContentsPage(String target, String path) {
		final String file = path + "\\" + target;
//		System.out.println("genContentsPage " + file);
        try (FileWriter writer = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(writer)) {
            genHeader(bw, "Table of Contents", "mainbody", "paragraphtext");

            bw.write("	<h1>\n");
            bw.write("	Table of Contents\n");
            bw.write("	</h1>\n");
            bw.write("	<br/>\n");
            bw.write("	<br/>\n");
            bw.write("	<div class=\"centeraligntext\">\n");
            bw.write("	  <h2>" + title + "</h2>\n");
            bw.write("	  <h3>by " + getAuthorName() + "</h3>\n");
            bw.write("	</div>\n");
            bw.write("	<br/>\n");

            for (String entry : contents)
                bw.write(entry);

            genFooter(bw);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void genNavigationControl(String target, String path) {
		final String file = path + "\\" + target;
//		System.out.println("genNavigationControl " + file);
        try (FileWriter writer = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
            bw.write("<ncx xmlns=\"http://www.daisy.org/z3986/2005/ncx/\" version=\"2005-1\" xml:lang=\"en\">\n");
            bw.write("	<head>\n");
            bw.write("		<meta content=\"" + identifier + "\" name=\"dtb:uid\"/>\n");
            bw.write("		<meta content=\"1\" name=\"dtb:depth\"/>\n");
            bw.write("		<meta content=\"0\" name=\"dtb:totalPageCount\"/>\n");
            bw.write("		<meta content=\"0\" name=\"dtb:maxPageNumber\"/>\n");
            bw.write("	</head>\n");
            bw.write("\n");
            bw.write("	<docTitle>\n");
            bw.write("		<text>" + title + "</text>\n");
            bw.write("	</docTitle>\n");
            bw.write("\n");
            bw.write("	<docAuthor>\n");
            bw.write("		<text>" + getAuthorName() + "</text>\n");
            bw.write("	</docAuthor>\n");
            bw.write("	<navMap>\n");

            for (String navPoint : navMap)
                bw.write(navPoint);

            bw.write("  </navMap>\n");
            bw.write("</ncx>\n");

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void genContent(String target, String path) {
		final String file = path + "\\" + target;
//		System.out.println("genContent " + file);
        try (FileWriter writer = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\" ?>\n");
            bw.write("<package xmlns=\"http://www.idpf.org/2007/opf\" unique-identifier=\"BookId\" version=\"2.0\">\n");
            bw.write("	<metadata xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:dcterms=\"http://purl.org/dc/terms/\" xmlns:opf=\"http://www.idpf.org/2007/opf\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n");
            bw.write("		<dc:title>" + title + "</dc:title>\n");
            bw.write("		<dc:language>" + mapLang.get(language) + "</dc:language>\n");
            bw.write("		<dc:identifier id=\"BookId\" opf:scheme=\"" + identifierType + "\">" + identifier + "</dc:identifier>\n");
            bw.write("		<dc:creator>" + getAuthorName() + "</dc:creator>\n");
            bw.write("		<dc:publisher>" + publisher + "</dc:publisher>\n");
            bw.write("		<dc:date>" + year + "</dc:date>\n");
            bw.write("		<meta name=\"cover\" content=\"my-cover-image\"/>\n");
            bw.write("	</metadata>\n");
            bw.write("\n");
            bw.write("	<manifest>\n");

            for (String item : manifest)
                bw.write(item);

            bw.write("  </manifest>\n");
            bw.write("	<spine toc=\"tableofcontents\">\n");

            for (String itemRef : spine)
                bw.write(itemRef);

            bw.write("  </spine>\n");
            bw.write("	<guide>\n");
            bw.write("		<reference href=\"bookcover.xhtml\" title=\"Cover Image\" type=\"cover\"/>\n");
            bw.write("		<reference href=\"toc.xhtml\" title=\"Table Of Contents\" type=\"toc\"/>\n");
            bw.write("	</guide>\n");
            bw.write("</package>\n");

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	private void genScripts(String target, int version) {
		String file = String.format("build%02d.bat", version);
		File root = new File(file);
		if (!root.exists()) {
//			System.out.println("genScripts " + file);
			try (FileWriter writer = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write("cd " + target + "\n");
				bw.write("del " + target + ".epub\n");
				bw.write("\"c:\\Program Files\\7-Zip\\7z.exe\" a " + target + ".epub mimetype META-INF\\ OEBPS\\\n");
				bw.write("cd ..\n");

				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		file = String.format("build%02d.sh", version);
		root = new File(file);
		if (!root.exists()) {
//			System.out.println("genScripts " + file);
			try (FileWriter writer = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(writer)) {

				bw.write("#! /bin/bash\n");
				bw.write("\n");
				bw.write("cd " + target + "\n");
				bw.write("rm " + target + ".epub\n");
				bw.write("'/cygdrive/c/Program Files/7-Zip/7z.exe' a " + target + ".epub mimetype META-INF/ OEBPS/\n");

				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void add(Mother mother, int targets) {
		if (targets >= ADD_SPINE) {
			targets -= ADD_SPINE;
			spine.add(mother.getItemRef());
		}
		if (targets >= ADD_MANIFEST) {
			targets -= ADD_MANIFEST;
			manifest.add(mother.getItem());
		}
		if (targets >= ADD_NAVMAP) {
			targets -= ADD_NAVMAP;
			navMap.add(mother.getNavPoint());
		}
		if (targets >= ADD_CONTENTS) {
			targets -= ADD_CONTENTS;
			contents.add(mother.getEntry());
		}
	}

	public void generate() {
//		System.out.println("Book Title: " + title);
//		System.out.println("Book Identifier: " + identifier);
//		System.out.println("Identifier Type: " + identifierType);
//		System.out.println("Language: " + mapLang.get(language));
//		System.out.println("Author Name: " + getAuthorName());
//		System.out.println("Sort Name: " + getSortName());
//		System.out.println("Chapter Count: " + chapterCount);
//		System.out.println("Publisher: " + publisher);
//		System.out.println("Year: " + year);

		contents.clear();
		navMap.clear();
		manifest.clear();
		spine.clear();
		Mother.resetPlayOrder();

		// Find next available root path.
		String path = "";
		int version = 1;
		for (; version < 100; ++version) {
			path = String.format("Template%02d", version);
			File rootDir = new File(path);
			if (!rootDir.exists())
				break;
		}

		if (version == 100) {
			System.err.println("Failed to make directory.");
			return;
		}

		// Build the directory structure
		File rootDir = new File(path);
		if (!rootDir.mkdir()) {
			System.err.println("Failed to make directory: " + rootDir.getPath());
			return;
		}
		File metaDir = new File(path + "/META-INF");
		if (!metaDir.mkdir()) {
			System.err.println("Failed to make directory: " + metaDir.getPath());
			return;
		}
		File dir = new File(path + "/OEBPS");
		if (!dir.mkdir()) {
			System.err.println("Failed to make directory: " + dir.getPath());
			return;
		}

		// Copy mime type file.
		copyFile("mimetype", rootDir.getPath());

		// Copy meta file.
		copyFile("container.xml", metaDir.getPath());

		// Copy content files.
		Mother father = new Mother("bookcover.xhtml", "bookcover", "Bookcover", MT_XHTML);
		father.setLinear(false);
		copyFile(father.getFile(), dir.getPath());
		add(father, ADD_MANIFEST + ADD_SPINE);
		copyFile("cover.jpg", dir.getPath());
		copyFile("backcover.xhtml", dir.getPath());
		copyFile("backcover.jpg", dir.getPath());
		copyFile("stylesheet.css", dir.getPath());

		if (checkBoxes[CKB_HALFTITLE]) {
			Mother mother = new Mother("halftitle.xhtml", "halftitle", "Half Title", MT_XHTML);
			genTitlePage(mother.getFile(), dir.getPath(), false);
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_FRONTISPIECE]) {
			Mother mother = new Mother("frontispiece.xhtml", "frontispiece", "Frontispiece", MT_XHTML);
			copyFile(mother.getFile(), dir.getPath());
			copyFile("frontispiece.jpg", dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_TITLEPAGE]) {
			Mother mother = new Mother("title.xhtml", "title", "Title", MT_XHTML);
			genTitlePage(mother.getFile(), dir.getPath(), true);
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_COPYRIGHT]) {
			Mother mother = new Mother("copyright.xhtml", "copyright", "Copyright", MT_XHTML);
			genCopyrightPage(mother.getFile(), dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_DEDICATION]) {
			Mother mother = new Mother("dedication.xhtml", "dedication", "Dedication", MT_XHTML);
			copyFile(mother.getFile(), dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_EPIGRAPH]) {
			Mother mother = new Mother("epigraph.xhtml", "epigraph", "Epigraph", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_TABLEOFCONTENTS]) {
			Mother mother = new Mother("toc.xhtml", "toc", "Table of Contents", MT_XHTML);
			add(mother, ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_FOREWORD]) {
			Mother mother = new Mother("foreword.xhtml", "foreword", "Foreword", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_PREFACE]) {
			Mother mother = new Mother("preface.xhtml", "preface", "Preface", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_ACKNOWLEDGMENTS]) {
			Mother mother = new Mother("acknowledgments.xhtml", "acknowledgments", "Acknowledgments", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_INTRODUCTION]) {
			Mother mother = new Mother("introduction.xhtml", "introduction", "Introduction", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_PROLOGUE]) {
			Mother mother = new Mother("prologue.xhtml", "prologue", "Prologue", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}

		for (int i = 1; i <= chapterCount; ++i) {
			String file = String.format("chap%02d.xhtml", i);
			String id = String.format("chap%02d", i);
			String title =  listChapters.get(i-1).getTitle();
			Mother mother = new Mother(file, id, title, MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}

		if (checkBoxes[CKB_EPILOGUE]) {
			Mother mother = new Mother("epilogue.xhtml", "epilogue", "Epilogue", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_OUTRO]) {
			Mother mother = new Mother("outro.xhtml", "outro", "Outro", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_AFTERWORD]) {
			Mother mother = new Mother("afterword.xhtml", "afterword", "Afterword", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_CONCLUSION]) {
			Mother mother = new Mother("conclusion.xhtml", "conclusion", "Conclusion", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_POSTSCRIPT]) {
			Mother mother = new Mother("postscript.xhtml", "postscript", "Postscript", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_APPENDIX]) {
			Mother mother = new Mother("appendix.xhtml", "appendix", "Appendix", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_GLOSSARY]) {
			Mother mother = new Mother("glossary.xhtml", "glossary", "Glossary", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_BIBLIOGRAPHY]) {
			Mother mother = new Mother("bibliography.xhtml", "bibliography", "Bibliography", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_INDEX]) {
			Mother mother = new Mother("index.xhtml", "index", "index", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_BIOGRAPHY]) {
			Mother mother = new Mother("biography.xhtml", "biography", "Biography", MT_XHTML);
			copyFile(mother.getFile(), dir.getPath());
			copyFile("author.jpg", dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_COLOPHON]) {
			Mother mother = new Mother("colophon.xhtml", "colophon", "Colophon", MT_XHTML);
			copyFile(mother.getFile(), dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}
		if (checkBoxes[CKB_POSTFACE]) {
			Mother mother = new Mother("postface.xhtml", "postface", "Postface", MT_XHTML);
			genChapterPage(mother, dir.getPath());
			add(mother, ADD_CONTENTS + ADD_NAVMAP + ADD_MANIFEST + ADD_SPINE);
		}

		if (checkBoxes[CKB_BIOGRAPHY]) {
			Mother mother = new Mother("author.jpg", "my-author-image", "Postface Image", MT_JPG);
			add(mother, ADD_MANIFEST);
		}
		if (checkBoxes[CKB_FRONTISPIECE]) {
			Mother mother = new Mother("frontispiece.jpg", "frontispiece-image", "Frontispiece Image", MT_JPG);
			add(mother, ADD_MANIFEST);
		}

		father = new Mother("backcover.xhtml", "backcover", "Backcover", MT_XHTML);
		add(father, ADD_MANIFEST + ADD_SPINE);

		father = new Mother("cover.jpg", "my-cover-image", "Cover Image", MT_JPG);
		add(father, ADD_MANIFEST);

		father = new Mother("backcover.jpg", "backcover-image", "Back Cover Image", MT_JPG);
		add(father, ADD_MANIFEST);

		father = new Mother("stylesheet.css", "cascadingstylesheet", "Stylesheet", MT_CSS);
		add(father, ADD_MANIFEST);

		father = new Mother("toc.ncx", "tableofcontents", "Stylesheet", MT_NCX);
		add(father, ADD_MANIFEST);

		genContentsPage("toc.xhtml", dir.getPath());
		genNavigationControl("toc.ncx", dir.getPath());
		genContent("content.opf", dir.getPath());
		genScripts(path, version);
	}


	/**
	 * Initialization.
	 */

	private void initIdTypeMap() {
		mapIdType.put("ISBN", ISBN);
		mapIdType.put("UUID", UUID);
		mapIdType.put("DOI", DOI);
		mapIdType.put("JDCN", JDCN);

		int i = 0;
		listIdentifierTypes.clear();
		for (String item : mapIdType.keySet()) {
			listIdentifierTypes.add(item);
			if (item.compareTo(identifierType) == 0)
				initialIdTypeMapIndex = i;
			i++;
		}
	}

	private void initLanguageMap() {
		mapLang.put("Abkhazian", "ab");
		mapLang.put("Afar", "aa");
		mapLang.put("Afrikaans", "af");
		mapLang.put("Akan", "ak");
		mapLang.put("Albanian", "sq");
		mapLang.put("Amharic", "am");
		mapLang.put("Arabic", "ar");
		mapLang.put("Aragonese", "an");
		mapLang.put("Armenian", "hy");
		mapLang.put("Assamese", "as");
		mapLang.put("Avaric", "av");
		mapLang.put("Avestan", "ae");
		mapLang.put("Aymara", "ay");
		mapLang.put("Azerbaijani", "az");
		mapLang.put("Bambara", "bm");
		mapLang.put("Bashkir", "ba");
		mapLang.put("Basque", "eu");
		mapLang.put("Belarusian", "be");
		mapLang.put("Bengali", "bn");
		mapLang.put("Bihari languages", "bh");
		mapLang.put("Bislama", "bi");
		mapLang.put("Bosnian", "bs");
		mapLang.put("Breton", "br");
		mapLang.put("Bulgarian", "bg");
		mapLang.put("Burmese", "my");
		mapLang.put("Catalan", "ca");
		mapLang.put("Chamorro", "ch");
		mapLang.put("Chechen", "ce");
		mapLang.put("Chichewa, Chewa, Nyanja", "ny");
		mapLang.put("Chinese", "zh");
		mapLang.put("Chuvash", "cv");
		mapLang.put("Cornish", "kw");
		mapLang.put("Corsican", "co");
		mapLang.put("Cree", "cr");
		mapLang.put("Croatian", "hr");
		mapLang.put("Czech", "cs");
		mapLang.put("Danish", "da");
		mapLang.put("Divehi, Dhivehi, Maldivian", "dv");
		mapLang.put("Flemish", "nl");
		mapLang.put("Dzongkha", "dz");
		mapLang.put("English", "en");
		mapLang.put("Esperanto", "eo");
		mapLang.put("Estonian", "et");
		mapLang.put("Ewe", "ee");
		mapLang.put("Faroese", "fo");
		mapLang.put("Fijian", "fj");
		mapLang.put("Finnish", "fi");
		mapLang.put("French", "fr");
		mapLang.put("Fulah", "ff");
		mapLang.put("Galician", "gl");
		mapLang.put("Georgian", "ka");
		mapLang.put("German", "de");
		mapLang.put("Greek", "el");
		mapLang.put("Guarani", "gn");
		mapLang.put("Gujarati", "gu");
		mapLang.put("Haitian, Haitian Creole", "ht");
		mapLang.put("Hausa", "ha");
		mapLang.put("Hebrew", "he");
		mapLang.put("Herero", "hz");
		mapLang.put("Hindi", "hi");
		mapLang.put("Hiri Motu", "ho");
		mapLang.put("Hungarian", "hu");
		mapLang.put("Interlingua", "ia");
		mapLang.put("Indonesian", "id");
		mapLang.put("Interlingue</a", "ie");
		mapLang.put("Irish", "ga");
		mapLang.put("Igbo", "ig");
		mapLang.put("Inupiaq", "ik");
		mapLang.put("Ido", "io");
		mapLang.put("Icelandic", "is");
		mapLang.put("Italian", "it");
		mapLang.put("Inuktitut", "iu");
		mapLang.put("Japanese", "ja");
		mapLang.put("Javanese", "jv");
		mapLang.put("Kalaallisut, Greenlandic", "kl");
		mapLang.put("Kannada", "kn");
		mapLang.put("Kanuri", "kr");
		mapLang.put("Kashmiri", "ks");
		mapLang.put("Kazakh", "kk");
		mapLang.put("Central Khmer", "km");
		mapLang.put("Kikuyu, Gikuyu", "ki");
		mapLang.put("Kinyarwanda", "rw");
		mapLang.put("Kirghiz, Kyrgyz", "ky");
		mapLang.put("Komi", "kv");
		mapLang.put("Kongo", "kg");
		mapLang.put("Korean", "ko");
		mapLang.put("Kurdish", "ku");
		mapLang.put("Kuanyama, Kwanyama", "kj");
		mapLang.put("Latin", "la");
		mapLang.put("Luxembourgish, Letzeburgesch", "lb");
		mapLang.put("Ganda", "lg");
		mapLang.put("Limburgan, Limburger, Limburgish", "li");
		mapLang.put("Lingala", "ln");
		mapLang.put("Lao", "lo");
		mapLang.put("Lithuanian", "lt");
		mapLang.put("Luba-Katanga", "lu");
		mapLang.put("Latvian", "lv");
		mapLang.put("Manx", "gv");
		mapLang.put("Macedonian", "mk");
		mapLang.put("Malagasy", "mg");
		mapLang.put("Malay", "ms");
		mapLang.put("Malayalam", "ml");
		mapLang.put("Maltese", "mt");
		mapLang.put("Maori", "mi");
		mapLang.put("Marathi", "mr");
		mapLang.put("Marshallese", "mh");
		mapLang.put("Mongolian", "mn");
		mapLang.put("Nauru", "na");
		mapLang.put("Navajo, Navaho", "nv");
		mapLang.put("North Ndebele", "nd");
		mapLang.put("Nepali", "ne");
		mapLang.put("Ndonga", "ng");
		mapLang.put("Norwegian Bokmål", "nb");
		mapLang.put("Norwegian Nynorsk", "nn");
		mapLang.put("Norwegian", "no");
		mapLang.put("Sichuan Yi, Nuosu", "ii");
		mapLang.put("South Ndebele", "nr");
		mapLang.put("Occitan", "oc");
		mapLang.put("Ojibwa", "oj");
		mapLang.put("Church Slavic", "cu");
		mapLang.put("Oromo", "om");
		mapLang.put("Oriya", "or");
		mapLang.put("Ossetian, Ossetic", "os");
		mapLang.put("Punjabi, Panjabi", "pa");
		mapLang.put("Pali", "pi");
		mapLang.put("Persian", "fa");
		mapLang.put("Polish", "pl");
		mapLang.put("Pashto, Pushto", "ps");
		mapLang.put("Portuguese", "pt");
		mapLang.put("Quechua", "qu");
		mapLang.put("Romansh", "rm");
		mapLang.put("Rundi", "rn");
		mapLang.put("Romanian, Moldavian, Moldovan", "ro");
		mapLang.put("Russian", "ru");
		mapLang.put("Sanskrit", "sa");
		mapLang.put("Sardinian", "sc");
		mapLang.put("Sindhi", "sd");
		mapLang.put("Northern Sami", "se");
		mapLang.put("Samoan", "sm");
		mapLang.put("Sango", "sg");
		mapLang.put("Serbian", "sr");
		mapLang.put("Gaelic", "gd");
		mapLang.put("Shona", "sn");
		mapLang.put("Sinhala, Sinhalese", "si");
		mapLang.put("Slovak", "sk");
		mapLang.put("Slovenian", "sl");
		mapLang.put("Somali", "so");
		mapLang.put("Southern Sotho", "st");
		mapLang.put("Spanish, Castilian", "es");
		mapLang.put("Sundanese", "su");
		mapLang.put("Swahili", "sw");
		mapLang.put("Swati", "ss");
		mapLang.put("Swedish", "sv");
		mapLang.put("Tamil", "ta");
		mapLang.put("Telugu", "te");
		mapLang.put("Tajik", "tg");
		mapLang.put("Thai", "th");
		mapLang.put("Tigrinya", "ti");
		mapLang.put("Tibetan", "bo");
		mapLang.put("Turkmen", "tk");
		mapLang.put("Tagalog", "tl");
		mapLang.put("Tswana", "tn");
		mapLang.put("Tonga", "to");
		mapLang.put("Turkish", "tr");
		mapLang.put("Tsonga", "ts");
		mapLang.put("Tatar", "tt");
		mapLang.put("Twi", "tw");
		mapLang.put("Tahitian", "ty");
		mapLang.put("Uighur, Uyghur", "ug");
		mapLang.put("Ukrainian", "uk");
		mapLang.put("Urdu", "ur");
		mapLang.put("Uzbek", "uz");
		mapLang.put("Venda", "ve");
		mapLang.put("Vietnamese", "vi");
		mapLang.put("Volapük", "vo");
		mapLang.put("Walloon", "wa");
		mapLang.put("Welsh", "cy");
		mapLang.put("Wolof", "wo");
		mapLang.put("Western Frisian", "fy");
		mapLang.put("Xhosa", "xh");
		mapLang.put("Yiddish", "yi");
		mapLang.put("Yoruba", "yo");
		mapLang.put("Zhuang, Chuang", "za");
		mapLang.put("Zulu", "zu");

		int i = 0;
		listLanguages.clear();
		for (String item : mapLang.keySet()) {
			listLanguages.add(item);
			if (item.compareTo(language) == 0)
				initialLanguageMapIndex = i;
			i++;
		}
	}

	private void initCheckBoxes() {
		for (int i = 0; i < checkBoxes.length; ++i)
			checkBoxes[i] = false;
		checkBoxes[CKB_TITLEPAGE] = true;
		checkBoxes[CKB_COPYRIGHT] = true;
		checkBoxes[CKB_DEDICATION] = true;
		checkBoxes[CKB_TABLEOFCONTENTS] = true;
		checkBoxes[CKB_PROLOGUE] = true;
		checkBoxes[CKB_EPILOGUE] = true;
		checkBoxes[CKB_BIOGRAPHY] = true;
		checkBoxes[CKB_COLOPHON] = true;
	}

	private void initChaptersList() {
		listChapters.clear();
		for (int i = 1; i <= chapterCount; ++i) {
			listChapters.add(new Chapter(i, "Chapter " + i));
		}
	}

	/**
	 * Constructor - runs all initialization code.
	 */
	public Model() {
		initIdTypeMap();
		initLanguageMap();
		initCheckBoxes();
		initChaptersList();
	}

}
