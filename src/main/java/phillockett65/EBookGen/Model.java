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
 * Model is a class that captures the dynamic shared data plus some supporting 
 * constants and provides access via getters and setters.
 */
package phillockett65.EBookGen;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


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

	public final static int ISBN = 0;
	public final static int URL = 1;

	private String familyName = "Dickens";
	private String givenNames = "Charles";
	private String title = "Oliver Twist";
	private String language = "English";
	private String identifier = "isbn-123-1-12-123456-1";
	private String identifierType = "ISBN";

	private int chapterCount = INITIAL_CHAPTER_COUNT;

	private Map<String, String> mapLang = new TreeMap<>();
	private Map<String, Integer> mapIdType = new TreeMap<>();
//	private ObservableList<String> listLang = FXCollections.observableArrayList("UNDEFINED");

	public void generate() {
		System.out.println("Book Title: " + title);
		System.out.println("Book Identifier: " + identifier);
		System.out.println("Identifier Type: " + identifierType);
		System.out.println("Language: " + mapLang.get(language));
		System.out.println("Author Name: " + getAuthorName());
		System.out.println("Sort Name: " + getSortName());
		System.out.println("Chapter Count: " + chapterCount);
		for (boolean flag : checkBoxes)
			System.out.println(flag);
	}

	public Model() {
		mapIdType.put("ISBN", ISBN);
		mapIdType.put("URL", URL);

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

	public Set<String> getIdTypeSet() {
		return mapIdType.keySet();
	}

	public Set<String> getLanguageSet() {
		return mapLang.keySet();
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getIdentifierType() {
		return identifierType;
	}
	public void setIdentifierType(String identifierType) {
		this.identifierType = identifierType;
	}

	public int getChapterCount() {
		return chapterCount;
	}

	public void incChapterCount() {
		this.chapterCount++;
	}

	public void decChapterCount() {
		this.chapterCount--;
	}

	public boolean isCheckBox(int id) {
        return checkBoxes[id];
	}

	public void setCheckBox(int id, boolean state) {
//		System.out.println("setCheckBox(" + id + ", " + state + ")");
		checkBoxes[id] = state;
	}

}
