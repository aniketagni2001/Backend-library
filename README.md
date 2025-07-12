# Library Book Catalog API - Simple Version

A simple Java REST API for managing a library's book catalog.

## What this API does:

- ✅ Add new books
- ✅ Get all books
- ✅ Get a book by ID
- ✅ Get a book by ISBN
- ✅ Delete a book
- ✅ Update book availability
- ✅ Update book details

## What you need to run this:

1. **Java 8 or higher**
2. **Maven** (to build the project)
3. **MySQL** (running on localhost:3306)

## How to run:

1. **Make sure MySQL is running**
2. **Open command prompt/terminal in the project folder**
3. **Run this command:**
   ```bash
   mvn spring-boot:run
   ```
4. **The API will start on:** `http://localhost:8080`

## API Endpoints (Simple List):

| Method | URL | What it does |
|--------|-----|--------------|
| `POST` | `/api/books` | Add a new book |
| `GET` | `/api/books` | Get all books |
| `GET` | `/api/books/{id}` | Get book by ID |
| `GET` | `/api/books/isbn/{isbn}` | Get book by ISBN |
| `DELETE` | `/api/books/{id}` | Delete a book |
| `PATCH` | `/api/books/{id}/availability` | Update book availability |
| `PUT` | `/api/books/{id}` | Update book details |
| `GET` | `/api/books/health` | Check if API is working |

## 📚 How to Add a New Book

### Method 1: Using cURL (Command Line)

```bash
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Your Book Title",
    "author": "Author Name",
    "isbn": "978-1234567890",
    "available": true
  }'
```

### Method 2: Using Postman

1. **Open Postman**
2. **Create a new request:**
   - Method: `POST`
   - URL: `http://localhost:8080/api/books`
3. **Go to Headers tab:**
   - Add: `Content-Type` = `application/json`
4. **Go to Body tab:**
   - Select: `raw`
   - Select: `JSON`
   - Add this JSON:
   ```json
   {
     "title": "Your Book Title",
     "author": "Author Name",
     "isbn": "978-1234567890",
     "available": true
   }
   ```
5. **Click Send**

### Method 3: Using Browser (Simple Test)

1. **Open browser**
2. **Go to:** `http://localhost:8080/api/books`
3. **You'll see all books (but can't add from browser)**

## 🧪 How to Test API with Postman

### Step 1: Import the Collection

1. **Open Postman**
2. **Click "Import"**
3. **Select the file:** `Library_Book_Catalog_API.postman_collection.json`
4. **Click "Import"**

### Step 2: Test Each Endpoint

#### 1. Health Check
- **Request:** `GET http://localhost:8080/api/books/health`
- **Expected Response:** `"Library API is running successfully!"`

#### 2. Get All Books
- **Request:** `GET http://localhost:8080/api/books`
- **Expected Response:** List of all books in JSON format

#### 3. Add a New Book
- **Request:** `POST http://localhost:8080/api/books`
- **Headers:** `Content-Type: application/json`
- **Body:**
```json
{
  "title": "Test Book",
  "author": "Test Author",
  "isbn": "978-1234567890",
  "available": true
}
```
- **Expected Response:** The created book with ID

#### 4. Get Book by ID
- **Request:** `GET http://localhost:8080/api/books/1`
- **Expected Response:** Book with ID 1

#### 5. Update Book Availability
- **Request:** `PATCH http://localhost:8080/api/books/1/availability`
- **Headers:** `Content-Type: application/json`
- **Body:** `false`
- **Expected Response:** Updated book

#### 6. Delete a Book
- **Request:** `DELETE http://localhost:8080/api/books/1`
- **Expected Response:** Success message

### Step 3: Test Error Cases

#### Test 1: Add Book with Missing Fields
- **Request:** `POST http://localhost:8080/api/books`
- **Body:**
```json
{
  "title": "Incomplete Book"
}
```
- **Expected Response:** Error message about missing fields

#### Test 2: Get Non-existent Book
- **Request:** `GET http://localhost:8080/api/books/999`
- **Expected Response:** 404 Not Found

#### Test 3: Add Book with Duplicate ISBN
- **Request:** `POST http://localhost:8080/api/books`
- **Body:** Use same ISBN as existing book
- **Expected Response:** Error about duplicate ISBN

## 📝 Example Book Data

Here are some example books you can add:

### Indian Books:
```json
{
  "title": "Ramayana",
  "author": "Valmiki",
  "isbn": "978-0140447444",
  "available": true
}
```

```json
{
  "title": "Mahabharata",
  "author": "Vyasa",
  "isbn": "978-0140446812",
  "available": true
}
```

```json
{
  "title": "Bhagavad Gita",
  "author": "Krishna Dvaipayana Vyasa",
  "isbn": "978-0140449233",
  "available": false
}
```

### Other Books:
```json
{
  "title": "The Hobbit",
  "author": "J.R.R. Tolkien",
  "isbn": "978-0547928241",
  "available": true
}
```

```json
{
  "title": "Harry Potter and the Philosopher's Stone",
  "author": "J.K. Rowling",
  "isbn": "978-0747532699",
  "available": true
}
```

## 🔍 Understanding API Responses

### Success Response (200/201):
```json
{
  "id": 1,
  "title": "Ramayana",
  "author": "Valmiki",
  "isbn": "978-0140447444",
  "available": true
}
```

### Error Response (400):
```json
{
  "title": "Title is required",
  "author": "Author is required",
  "isbn": "ISBN is required"
}
```

### Not Found Response (404):
- Empty response body

## 📋 Postman Tips

### 1. Environment Variables
Create an environment in Postman:
- **Variable:** `baseUrl`
- **Value:** `http://localhost:8080`
- **Use in requests:** `{{baseUrl}}/api/books`

### 2. Collection Variables
In the collection, you can set:
- **Variable:** `bookId`
- **Use in requests:** `{{baseUrl}}/api/books/{{bookId}}`

### 3. Pre-request Scripts
To automatically set book ID after creating a book:
```javascript
pm.test("Set book ID", function () {
    var jsonData = pm.response.json();
    pm.collectionVariables.set("bookId", jsonData.id);
});
```

### 4. Test Scripts
To verify responses:
```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Response has required fields", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property('id');
    pm.expect(jsonData).to.have.property('title');
    pm.expect(jsonData).to.have.property('author');
});
```

## 🚨 Common Issues and Solutions

### Issue 1: "Connection refused"
**Solution:** Make sure the API is running (`mvn spring-boot:run`)

### Issue 2: "MySQL connection failed"
**Solution:** Start MySQL service

### Issue 3: "Port 8080 already in use"
**Solution:** Change port in `application.properties` or kill the process using port 8080

### Issue 4: "Validation failed"
**Solution:** Check that all required fields (title, author, isbn) are provided

### Issue 5: "ISBN already exists"
**Solution:** Use a different ISBN number

## 📊 Testing Checklist

- [ ] Health check works
- [ ] Can get all books
- [ ] Can add a new book
- [ ] Can get book by ID
- [ ] Can get book by ISBN
- [ ] Can update book availability
- [ ] Can update book details
- [ ] Can delete a book
- [ ] Error handling works for invalid data
- [ ] Error handling works for non-existent books

## Book Object Structure:

```json
{
  "id": 1,
  "title": "Ramayana",
  "author": "Valmiki",
  "isbn": "978-0140447444",
  "available": true
}
```

## Sample Books (automatically added):

1. **Ramayana** by Valmiki
2. **Mahabharata** by Vyasa
3. **Bhagavad Gita** by Krishna Dvaipayana Vyasa
4. **Panchatantra** by Vishnu Sharma
5. **Arthashastra** by Kautilya

## Test the API:

1. **Start the application**
2. **Open your browser**
3. **Go to:** `http://localhost:8080/api/books`
4. **You should see the list of books**

## Using Postman:

1. **Import the file:** `Library_Book_Catalog_API.postman_collection.json`
2. **All the API calls are ready to use**

## Project Structure (Simple):

```
src/main/java/com/library/
├── LibraryApplication.java          (Main class)
├── entity/
│   └── Book.java                   (Book data structure)
├── repository/
│   └── BookRepository.java         (Database operations)
├── service/
│   └── BookService.java            (Business logic)
├── controller/
│   └── BookController.java         (API endpoints)
├── exception/
│   └── GlobalExceptionHandler.java (Error handling)
└── config/
    └── DataInitializer.java        (Add sample data)
```

## Database:

- **Database name:** `library_db` (created automatically)
- **Table name:** `books` (created automatically)
- **Connection:** `jdbc:mysql://127.0.0.1:3306/library_db`

## Common Issues:

1. **MySQL not running:** Start MySQL service
2. **Port 8080 in use:** Change port in `application.properties`
3. **Database connection error:** Check MySQL is running on localhost:3306

## Health Check:

Test if the API is working:
```bash
curl -X GET http://localhost:8080/api/books/health
```

Should return: `"Library API is running successfully!"` 