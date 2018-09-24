package code.controller

import code.util.Data
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.event.Event
import javafx.event.EventHandler
import javafx.event.EventType
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TextArea
import javafx.scene.control.TextField

/**
 *  Gets text from input fields and inserts
 *  them into the database
 */
class InputController implements Initializable{

    Data sql = new Data()

    public ComboBox table
    public TextField title
    public TextField section
    public TextField paragraph
    public TextField term
    public TextArea meaning
    public Button insert


    void insert() {

        def getTable = table.getValue().toString()
        def getTitle = title.getText()
        def getSection = section.getText()
        def getParagraph = paragraph.getText()
        def getTerm = term.getText()
        def getMeaning = meaning.getText()

        if (getTable == 'us_code' || getTable == 'implementing_regs') {
            String[] params = [getTitle, getSection, getParagraph, getTerm, getMeaning]
            if(sql.executeQuery(getTable, params)){
                cleanUp()
            }
        } else if (getTable == 'blacks_law') {
            String[] params = [getTitle, getSection, getParagraph, getTerm, getMeaning]
            if(sql.executeQuery(getTable, params)){
                cleanUp()
            }
        } else if (getTable == 'cornell_links') {
            String[] params = [getTitle, getMeaning]
            if(sql.executeQuery(getTable, params)){
                cleanUp()
            }
        } else if (getTable == 'crimes') {
            String[] params = [getTitle, getSection, getMeaning]
            if(sql.executeQuery(getTable, params)){
                cleanUp()
            }
        }else if(getTable == 'supreme_court'){
            String[] params = [getTitle, getMeaning]
            if(sql.executeQuery(getTable, params)){
                cleanUp()
            }
        }else{//words of art
            String[] params = [getTitle, getMeaning]
            if(sql.executeQuery(getTable, params)){
                cleanUp()
            }
        }
    }

    void cleanUp(){
        //clean up
        table.getSelectionModel().clearSelection()
        title.setText("")
        section.setText("")
        paragraph.setText("")
        term.setText("")
        meaning.setText("")
    }

    @Override
    void initialize(URL url, ResourceBundle resourceBundle) {
        insert.defaultButton = true
        setListener()
    }

    void setListener(){

        table.valueProperty().addListener(new ChangeListener() {
            @Override
            void changed(ObservableValue observableValue, Object o, Object new_val) {

                title.setVisible(true)
                title.setPromptText("Title")

                section.setVisible(true)
                section.setPromptText("Section")

                paragraph.setVisible(true)
                paragraph.setPromptText("Paragraph")

                term.setVisible(true)
                term.setPromptText("Term")

                meaning.setPromptText("Meaning")

                if (table.getValue().toString() == 'us_code' || table.getValue().toString() == 'implementing_regs') {
                    //pass
                } else if (table.getValue().toString() == 'blacks_law') {
                    title.setPromptText("Edition")
                    section.setPromptText("Page")
                    paragraph.setPromptText("Term")
                    term.setVisible(false)
                    meaning.setPromptText("Definition")

                } else if (table.getValue().toString() == 'cornell_links') {
                    title.setPromptText("Reference")
                    section.setVisible(false)
                    paragraph.setVisible(false)
                    term.setVisible(false)
                    meaning.setPromptText("Link")

                } else if (table.getValue().toString() == 'crimes') {
                    paragraph.setVisible(false)
                    term.setVisible(false)
                    meaning.setPromptText("Explanation")

                }else if(table.getValue().toString() == 'supreme_court'){
                    title.setPromptText("Reference")
                    section.setVisible(false)
                    paragraph.setVisible(false)
                    term.setVisible(false)
                    meaning.setPromptText("Interpretation")

                }else{//words_of_art
                    title.setPromptText("Term")
                    section.setVisible(false)
                    paragraph.setVisible(false)
                    term.setVisible(false)

                }
            }
        })
    }
}
