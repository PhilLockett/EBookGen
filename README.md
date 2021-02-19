# EBookGen
A simple application to generate a customized epub template.

## Overview
This project has been set up as a Maven project that uses JavaFX, FXML and 
CSS to render the GUI. Maven can be run from the command line as shown below.
Maven resolves dependencies and builds the application independently of an IDE.

This application generates a customized epub template file structure. This 
template is closely based on the template created by 
[Eric Muss-Barnes](http://www.EricMuss-Barnes.com), and he describes how to 
use it in this [video](https://www.youtube.com/watch?v=EiUMb7bgYeQ&t=2s).

This application flattens his structure and moves all modifiable files into the
TemplateXX/OEBPS directory (where XX is the next available 2 digit decimal 
value). The application automatically generates the files: "content.opf", 
"toc.ncx" and "toc.xhtml" based on user input and these files should not be 
modified.

The application also generates the command files "buildXX.bat" and 
"buildXX.sh", either of which can be used to create an epub file 
(TemplateXX/TemplateXX.epub) using the contents of the template. However these 
command files require an installed zip client, that can be run from the 
command line. The command file(s) should be updated to run the installed zip 
client. Note: by default the generated command files run the 
[7-Zip](https://www.7-zip.org/) client from it's default install directory.

## Dependencies
EBookGen is dependent on the following:

  * Java 15.0.1
  * Apache Maven 3.6.3

The code has been structured as a standard Maven project which requires Maven 
and a JDK to be installed. A quick web search will help, but if not 
[Apache](https://maven.apache.org/install.html) should guide you through the
install. Also [OpenJFX](https://openjfx.io/openjfx-docs/) can help set up your 
favourite IDE.

## Cloning and Running
The following commands clone and execute the code:

    git clone https://github.com/PhilLockett/EBookGen.git
	cd EBookGen/
	mvn clean javafx:run

### Updating
To get the latest code, run the following command from the EBookGen directory:

    git pull --rebase origin

Then use the mvn command above to run it. If the update fails, delete the 
entire EBookGen directory and execute the "Cloning and Running" steps again.

## Recommended work flow

  1. Generate a template using your initial best guess for the required sections.
  2. Update the xhtml files with your content.
  3. Check content by viewing OEBPS/toc.xhtml in a browser and correct any errors.
  4. If you realize the original template was wrong, generate a new template.
  5. Copy the content from the old template to the new.
  6. Go back to step 2.

## Points of interest
This code has the following points of interest:

  * EBookGen is a Maven project that uses JavaFX.
  * A GUI created with SceneBuilder utilizing FXML and CSS.
  * ChoiceBox providing ISO language name to 2 letter ISO 639-1 code.
  * TableView with in table editable cells.
  
