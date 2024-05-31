import java.io.File

//The program runs based off of the main function.
fun main() {

    //Create the list where the notes will all be stored.
    val notes = mutableListOf<String>()

    //Initialize the user input variable.
    var input = ""

    //Initial greeting when the program is started.
    println("Hi! Thank you for using this note-taking app. With this app, you will be able to create new notes, " +
            "delete notes, and search through your notes.")

    //As long as the user doesn't enter "6", the program will keep on running.
    while (input != "6"){

    //Display the menu that the user has to choose from.
    println("")
    println("What would you like to do? Please enter the number of your desired function.")
    println("")
    println("1. Create New Note")
    println("2. Delete Note")
    println("3. Search Note")
    println("4. Load Notes")
    println("5. Save Notes")
    println("6. Quit")
    println("")

    //Read the users input and store it as a string.
    input = readln()
    println("")

    //If the user doesn't have any notes, we don't want them to be able to search or save their notes.
        // So this if/else will ensure that doesn't happen.
    if(notes.isEmpty() && (input == "3" || input == "5")) {
        println("You don't have any notes yet! Try creating one first.")
    } else {

        //This when statement works as a switch and case. When the user selects any of these options,
        // the following function will be called.
        when (input) {
            "1" -> createNote(notes)
            "2" -> deleteNote(notes)
            "3" -> searchNote(notes)
            "4" -> loadNote(notes)
            "5" -> saveNote(notes)
            "6" -> println("Thank you! Goodbye!")
            else -> {
                //If the user doesn't select one of the numbers listed above, they will be notified
                // that their selection is not valid, and they will be redirected to select an option.
                println("Please make a valid selection.")
            }

        }
    }
    }
}


//This function will create a new note and attach it to the "notes" mutable list.
fun createNote(notes: MutableList<String>) {
    println("What is the title of your note?")
    println("")

    //Takes the title as a variable named "title".
    val title = readln()

    println("")

    println("Please enter the body of your note:")

    //Takes the body as a variable named "body".
    val body = readln()

    //Puts the title and body the user entered and stores it in the "notes" list.
    notes.add(title)
    notes.add(body)

    //Tells the user the note has been stored.
    println("")
    println("Note taken!")
    println("")
}

//This function will display only the titles. It is called in the "deleteNote" and "searchNote" functions.
fun viewTitles(notes: MutableList<String>) {
    var i = 0
    var j = 0

    //Since "notes" is a mutable list rather than an Array of Arrays, each item (titles and bodies) are
    // individual with no particular defining features. Due to this, we will need to pull the odd members of the list
    // and designate them as titles and display those to the user.
    notes.forEach { element ->
        i++
        if (i % 2 == 0) {
            println("")
        } else {
            j++
            println("$j.) $element")
        }
    }
}


//This function allows the user to delete a note out of the "notes" mutable list.
fun deleteNote(notes: MutableList<String>) {
    println("Which note would you like to delete?")
    println("")

    //Call the "viewTitles" function
    viewTitles(notes)

    //Read the user's input and convert it to a variable that will be able to call the user's desired
    // note title and body.
    val input = readln()
    val selection = input.toInt()
    val deleteNum = (selection*2)-1

    //Remove both the title and the body of the note that the user selected.
    notes.removeAt(deleteNum)
    notes.removeAt(deleteNum-1)

    //Notify the user that the note was successfully deleted.
    println("Note successfully removed!")
}


//This function lists the titles of the notes the user has created and then allows the user to
// select one to see the body of the note.
fun searchNote(notes: MutableList<String>) {
    println("Please select which note you would like to view:")

    //This creates variables that allow both the title and body of a note to be displayed in red to make it
    // easier for the user to read.
    val red = "\u001b[31m"
    val reset = "\u001b[0m"

    //Calls the "viewTitles" function to create a list of notes for the user to select from.
    viewTitles(notes)

    //Read the user's input and convert it to a variable that will be able to call the user's desired
    // note title and body.
    val input = readln()
    val selection = input.toInt()
    val view = (selection*2)-1
    println("")

    //Display the note that the user selected with the title and body of the note being displayed in red.
    println("Title:")
    println(red+notes[view-1]+reset)
    println("")
    println("Body:")
    println(red+notes[view]+reset)

}


//This function loads a list of notes from a text file to be added to or to be viewed by the user.
fun loadNote(notes: MutableList<String>) {
    println("What is the name of the file you would like to load?")
    println("Please include '.txt' at the end of the filename.")

    //Create a variable named "fileName" that will read the name that the user has entered.
    val fileName = File(readln())

    //Adds each line from the file the user has selected as a different element in the "notes" mutable list.
    fileName.forEachLine {
        notes.add(it)
    }
}


//This function will save the notes the user has entered into a text file that the user selects a name for.
fun saveNote(notes: MutableList<String>) {
    println("What file would you like to write your notes to?")
    println("Please include '.txt' at the end of the filename.")

    //Initialize a variable called "fileName" that is the name the user wants to name their file.
    val fileName = File(readln())

    //If this is an existing file, this will delete anything that is in the file.
    fileName.writeText("")

    //Take each element in the "notes" mutable list and add it as an individual line in the file the user has selected.
    notes.forEach { element ->

        fileName.appendText("$element\n")
    }

    //Notify the user the file has been saved.
    println("$fileName saved successfully!")
}