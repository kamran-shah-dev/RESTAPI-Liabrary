# Library Management API

A small REST API built with Java 21 and Spring Boot for managing books, library members, and borrowing records. The application currently stores data in memory, so all records are cleared when it stops.

## Requirements

- Java 21
- Maven (or use the included Maven Wrapper)

## Run the application

From the project root, run:

```bash
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The API is available at `http://localhost:8080`.

## API endpoints

All endpoints use the `/api` prefix and accept/return JSON.

### Books

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/books` | List all books |
| `GET` | `/api/books/{id}` | Get a book by ID |
| `GET` | `/api/books/title/{title}` | Get the first exact title match |
| `GET` | `/api/books/author/{author}` | Get the first exact author match |
| `POST` | `/api/books` | Add a book |
| `PUT` | `/api/books/{id}` | Update a book |
| `DELETE` | `/api/books/{id}` | Delete a book |

Example: add a book (IDs are supplied by the client):

```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"id":1,"title":"The Hobbit","author":"J.R.R. Tolkien","publicationYear":1937,"genre":"Fantasy","availableCopies":5}'
```

Example lookups:

```text
GET http://localhost:8080/api/books/title/The%20Hobbit
GET http://localhost:8080/api/books/author/J.R.R.%20Tolkien
```

### Members

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/members` | List all members |
| `GET` | `/api/members/{id}` | Get a member by ID |
| `POST` | `/api/members` | Add a member |
| `PUT` | `/api/members/{id}` | Update a member |
| `DELETE` | `/api/members/{id}` | Delete a member |

Member JSON fields: `id`, `name`, `email`, `phoneNumber`, `startDate`, and `endDate`. Dates use ISO format, for example `2026-01-15`.

### Borrowing

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/api/borrowing-records` | List borrowing records |
| `POST` | `/api/borrow` | Create a borrowing record; borrow and due dates are set by the API |
| `PUT` | `/api/return/{recordId}` | Return a book |

Borrow requests contain `id`, a nested `book`, and a nested `member`. The book and member should refer to records already in the in-memory library.

## Notes

- There is no database configured. Data is lost when the application restarts.
- IDs are not generated automatically; provide them when creating books, members, and borrowing records.
- A lookup that finds no record returns `404 Not Found`. List endpoints return an empty JSON array when there are no records.
