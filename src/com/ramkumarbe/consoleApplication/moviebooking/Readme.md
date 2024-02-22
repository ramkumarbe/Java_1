# Movie Ticket Booking System
This repository contains a console-based movie ticket booking system implemented in Java. It allows users to browse available movies, book tickets for shows, and manage the system as an administrator.
## Features
- **User Management:** Users can create accounts, log in, and book tickets.
- **Movie Management:** Administrators can add new movies and manage existing ones.
- **Show Scheduling:** Administrators can schedule shows for movies.
- **Ticket Booking:** Users can book tickets for available shows.
- **Genre Classification:** Movies are categorized by genres for easier browsing.
## Installation
1. Clone the repository to your local machine:
2. Open the project in your preferred Java IDE.
3. Set up a MySQL database and configure the database connection details in the `MovieRepository` class.
4. Compile and run the project.
## Usage
### As a User
1. Launch the application.
2. Create a new account or log in with existing credentials.
3. Browse available movies and select a show.
4. Book tickets for the chosen show.
5. Proceed to payment and confirm the booking.
### As an Administrator
1. Launch the application.
2. Log in as an administrator.
3. Access the admin panel to add new movies or schedule shows.
4. Log out after managing the system.
## Class Overview
```
                  +-------------------------+
                  |       BaseView          |
                  +-------------------------+
                            /\
                            |
                            |
            +---------------+---------------+
            |                               |
 +-----------------------+           +---------------------+
 |     AddUser           |           |    AdminPanel       |
 +-----------------------+           +---------------------+
 | - addUserViewModel    |           | - viewModel         |
 +-----------------------+           +---------------------+
 | + addNewUser()     	 |           | + adminHome()       |
 | + printErrorMessage() |           | + printMenu()       |
 | + onSuccess()      	 |           | + addShow()         |
 | + onFailure()         |           | + addMovie()        |
 +-----------------------+           | + printMessage()    |
                                     +---------------------+
                                              |
                   +--------------------------+---------------------------+
                   |                                                      |
           +-------------------------+                          +---------------------+
           |    AddUserViewModel     |                          | AdminPanelViewModel |
           +-------------------------+                          +---------------------+
           | - addUserView           |                          | - adminPanel        |
           +-------------------------+                          +---------------------+
           | + validateUserName()    |                          | + getGenres()       |
           | + validatePassword()    |                          | + addMovie()        |
           | + validatePhoneNumber() |                          | + getDateTime()     |
           | + validateMailId()      |                          | + getMovies()       |
           | + addUser()             |                          | + uploadShow()      |
           +-------------------------+                          +---------------------+
                          |                                                    |
                          +----------------------------------------------------+
                                     +---------------------+
                                     |  BookTicket         |
                                     +---------------------+
                                     | - viewModel         |
                                     +---------------------+
                                     | + bookTicket()      |
                                     | + payment()         |
                                     +---------------------+
                                              |
                                     +---------------------+
                                     |  BookTicketViewModel|
                                     +---------------------+
                                     | - bookTicket()      |
                                     +---------------------+
+--------------------------------+
|             Movie              |
+--------------------------------+
| - title: String                |
| - director: String             |
| - genre: List<String>          |
| - price: int                   |
| - seat: int                    |
+--------------------------------+
| + getTitle(): String           |
| + setTitle(String): void       |
| + getDirector(): String        |
| + setDirector(String): void    |
| + getGenre(): List<String>     |
| + setGenre(List<String>): void |
| + getPrice(): int              |
| + setPrice(int): void          |
| + getSeat(): int               |
| + setSeat(int): void           |
+--------------------------------+
+------------------------------------+
|              Show                  |
+------------------------------------+
| - dateTime: LocalDateTime          |
| - movie: Movie                     |
+------------------------------------+
| + getDateTime(): LocalDateTime     |
| + setDateTime(LocalDateTime): void |
| + getMovie(): Movie                |
| + setMovie(Movie): void            |
+------------------------------------+
+--------------------------------+
|             Ticket             |
+--------------------------------+
| - show: Show                   |
| - user: User                   |
| - seats: List<Integer>         |
+--------------------------------+
| + getShow(): Show              |
| + setShow(Show): void          |
| + getUser(): User              |
| + setUser(User): void          |
| + getSeats(): List<Integer>    |
+--------------------------------+
+--------------------------------+
|              User              |
+--------------------------------+
| - name: String                 |
| - password: String             |
| - phoneNumber: String          |
| - mail: String                 |
+--------------------------------+
| + getUserName(): String        |
| + setName(String): void        |
| + getPassword(): String        |
| + setPassword(String): void    |
| + getPhoneNumber(): String     |
| + setPhoneNumber(String): void |
| + getMail(): String            |
| + setMail(String): void        |
+--------------------------------+
```
