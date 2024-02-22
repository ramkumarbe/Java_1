# Phonebook Console Application

The Phonebook Console Application is a simple Java project that allows users to manage their contacts via a command-line interface. It provides functionalities to add new contacts, view existing contacts, search for contacts, and delete contacts. The contacts are stored in a JSON file for persistent storage.

## Features
Add New Contact: Users can add new contacts by providing their name and phone number.
View Contacts: Users can view the list of all existing contacts in the phonebook.
Search Contact: Users can search for contacts by name or phone number.
Delete Contact: Users can delete contacts from the phonebook.
- **Add New Contact:** Users can add new contacts by providing their name and phone number.
- **View Contacts:** Users can view the list of all existing contacts in the phonebook.
- **Search Contact:** Users can search for contacts by name or phone number.
- **Delete Contact:** Users can delete contacts from the phonebook.

## Project Structure
The project follows a modular structure with different packages for distinct functionalities:

com.ramkumarbe.consoleApplication.phonebook.addcontact: Contains classes related to adding new contacts.
com.ramkumarbe.consoleApplication.phonebook.home: Contains classes related to the main interface of the phonebook application.
com.ramkumarbe.consoleApplication.phonebook.repository: Contains classes related to storing and retrieving contacts.
- `com.ramkumarbe.consoleApplication.phonebook.addcontact`: Contains classes related to adding new contacts.
- `com.ramkumarbe.consoleApplication.phonebook.home`: Contains classes related to the main interface of the phonebook application.
- `com.ramkumarbe.consoleApplication.phonebook.repository`: Contains classes related to storing and retrieving contacts.

## Data Storage
Contacts in the phonebook application are stored in a JSON file (Phone_Book.json) for persistent storage. The ContactRepository class handles reading from and writing to this JSON file. When the application starts, it loads existing contacts from the JSON file, and when contacts are added, deleted, or modified, the changes are saved back to the JSON file.
Contacts in the phonebook application are stored in a JSON file (Phone_Book.json) for persistent storage. The `ContactRepository` class handles reading from and writing to this JSON file. When the application starts, it loads existing contacts from the JSON file, and when contacts are added, deleted, or modified, the changes are saved back to the JSON file.

## Usage
Clone the Repository: Clone the repository to your local machine using the following command:

git clone  https://github.com/ramkumarbe/Java/new/main/eclipse-workspace/Java/src/com/ramkumarbe/consoleApplication/phonebook

Compile and Run: Compile the Java files and run the application using the following commands:
1. **Clone the Repository:** Clone the repository to your local machine using the following command:
git clone https://github.com/ramkumarbe/Java/new/main/eclipse-workspace/Java/src/com/ramkumarbe/consoleApplication/phonebook

2. **Compile and Run:** Compile the Java files and run the application using the following commands:
cd phonebook
javac Main.java
java Main
Follow On-screen Instructions: Once the application is running, follow the on-screen instructions to use the different features of the phonebook application.

3. **Follow On-screen Instructions:** Once the application is running, follow the on-screen instructions to use the different features of the phonebook application.
```
+----------------------------------------+
|              Phonebook                 |
+----------------------------------------+
| - contactsList: Set<Contact>           |
+----------------------------------------+
| + main() : void                        |
+----------------------------------------+
+------------------------------------------+
|          Contact                         |
+------------------------------------------+
| - contactName: String                    |
| - phoneNumber: String                    |
+------------------------------------------+
| + Contact(name: String, number: String)  |
| + getContactName() : String              |
| + getPhoneNumber() : String              |
+------------------------------------------+
+-------------------------------------------+
|           Home                            |
+-------------------------------------------+
| - addContact: AddContact                  |
| - viewModel: HomeViewModel                |
+-------------------------------------------+
| + menu() : void                           |
| + deleteContact() : void                  |
| + searchContact() : void                  |
| + printContacts(Set<Contact>) : void      |
| + printMenuBox() : void                   |
| + printContact(Contact) : void            |
| + printMessage(String) : void             |
+-------------------------------------------+
+-------------------------------------------+
|           AddContact                      |
+-------------------------------------------+
| + addContact() : void                     |
| + showMessage(String) : void              |
+-------------------------------------------+
+-------------------------------------------+
|           HomeViewModel                   |
+-------------------------------------------+
| - home: Home                              |
+-------------------------------------------+
| + HomeViewModel(home: Home)               |
| + loadContacts() : void                   |
| + getContacts() : Set<Contact>            |
| + searchContact(String) : void            |
| + deleteContact(String) : void            |
| + finalize() : void                       |
+-------------------------------------------+
+------------------------------------------+
|           ContactRepository              |
+------------------------------------------+
| - contactsList: Set<Contact>             |
| - repository: ContactRepository          |
+------------------------------------------+
| + getInstance() : ContactRepository      |
| + loadContacts() : void                  |
| + writeContactListToJsonFile() : void    |
| + getContacts() : Set<Contact>           |
+------------------------------------------+
```
