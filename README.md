  A web-based educational application for library management. Librarians should be able to register readers, 
issue books to them, and release books (after the reader returns the book to the library).
  Entities:
Person (fields: Full Name (UNIQUE), year of birth)
Book (fields: title, author, year).
Relationship between entities: One-to-Many.
  A person can have multiple books. A book can belong to only one person.
The database should have two tables - Person and Book.

Required functionality:

1) Pages for adding, editing, and deleting a person.
2) Pages for adding, editing, and deleting a book.
3) Page with a list of all people (people are clickable - clicking on them redirects to the person's page).
4) Page with a list of all books (books are clickable - clicking on them redirects to the book's page).
5) Person's page showing the values of their fields and a list of books they have taken. If a person has not taken any books yet, the text "The person has not taken any books yet" should be displayed instead of the list.
6) Book's page showing the values of its fields and the name of the person who has taken the book. If the book has not been taken by anyone, the text "This book is available" should be displayed.
7) On the book's page, if the book is taken by a person, there should be a button next to their name labeled "Release Book". This button is clicked by the librarian when the reader returns the book to the library.
8) After clicking this button, the book becomes available again and disappears from the person's book list.
9) On the book's page, if the book is available, there should be a dropdown list (<select>) with all the people and a button labeled "Assign Book". This button is clicked by the librarian when a reader wants to take this book home.
10) After clicking this button, the book should belong to the selected person and appear in their book list.
