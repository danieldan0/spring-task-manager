# spring-task-manager

# Функціональні вимоги
## Користувачі
1. Користувачі мають мати можливість реєструватися використовуючи унікальний нікнейм, пароль та електрону пошту.
2. Користувачі мають мати можливість входу на сайт використовуючи їх нікнейм та пароль.
3. Лише авторизовані користувачі можуть створювати дошки завдань та виконувати операції над завданнями.
## Контроль над дошками завдань
1. Створення дошок завдань
   - Дошка завдань може бути створена користувачем надаючи їй назву та опис. Вона може бути публічною або приватною.
2. Перегляд дошок завдань
3. Редагування дошок завдань
4. Видалення дошок завдань
5. Колаборація над дошками завдань
   - До дошок завдань можуть бути додані додаткові користувачі які матимуть можливість працювати над завданнями.
## Контроль завдань
1. Створення завдань
   - Завдання може бути створена користувачем надаючи їй назву, опис, статус та планований час виконання.
2. Перегляд завдань
3. Редагування завдань
4. Видалення завдань
5. Розподіл завдань
## Співпраця та дозволи
1. Рівні дозволів
   - Власник: творець дошки, має повний контроль. Може керувати завданнями, налаштуваннями та співавторами.
   - Адміністратор: такі ж самі права як і власник.
   - Редактор: співавторами з дозволом на редагування. Можуть створювати, редагувати та видаляти завдання.
   - Переглядач: лише має можливість переглядати дошку та завдання на ній.
2. Публічні дошки
   - Їх можуть переглядати навіть не зареєстровані користувачі, але редагувати їх можуть лише пиизначені редактори.
## Категоризація завдань
1. Категоризація завдань по статусу
2. Фільтрування завдань по планованому часу виконання
3. Пошук завдань по назві чи опису
## Сповіщення про завдання
1. Сповіщення по пошті для завдань, час виконання яких наближується
2. Сповіщення про прострочені завдання
## Потреби безпеки
1. Шифрування паролів
   - Паролі мають зберігатися лише у зашифрованому вигляді
2. Безпечні кінцеві точки API
   - Кінцеві точки мають бути доступні на основі дозволів. 
## Постійність даних
1. Інтеграція бази даних
2. Підтримка резервного копіювання та відновлення даних.
3. Можливість експортування та імпортування окремих дошок.
## Журнали аудиту
- Дати власникам та адміністраторам дошок бачити журнал змін зроблених над дошкою завдань.

## ERD
https://dbdiagram.io/d/6733894fe9daa85aca33ab7c
![Untitled (1)](https://github.com/user-attachments/assets/5be972dd-cdcf-4ded-a7db-edb27fc9d28c)

# REST API Endpoints

## Task Endpoints

### Get Task by ID
- **Endpoint**: `/tasks/<int:task_id>`
- **Method**: `GET`
- **Description**: Retrieve a task by its ID.
- **Response**:
  ```json
  {
    "TaskID": 1,
    "Title": "Sample Task",
    "Description": "This is a sample task.",
    "Status": "To Do",
    "DueDate": "2023-10-10T00:00:00",
    "AssignedUserID": 2,
    "BoardID": 1
  }
  ```

### Create a New Task
- **Endpoint**: `/tasks`
- **Method**: `POST`
- **Description**: Create a new task.
- **Request Body**:
  ```json
  {
    "Title": "New Task",
    "Description": "Description of the new task.",
    "Status": "To Do",
    "DueDate": "2023-10-10T00:00:00",
    "AssignedUserID": 2,
    "BoardID": 1
  }
  ```
- **Response**:
  ```json
  {
    "message": "Task created successfully"
  }
  ```

### Update a Task by ID
- **Endpoint**: `/tasks/<int:task_id>`
- **Method**: `PUT`
- **Description**: Update an existing task by its ID.
- **Request Body**:
  ```json
  {
    "Title": "Updated Task",
    "Description": "Updated description of the task.",
    "Status": "In Progress",
    "DueDate": "2023-10-15T00:00:00",
    "AssignedUserID": 3,
    "BoardID": 1
  }
  ```
- **Response**:
  ```json
  {
    "message": "Task updated successfully"
  }
  ```

### Delete a Task by ID
- **Endpoint**: `/tasks/<int:task_id>`
- **Method**: `DELETE`
- **Description**: Delete a task by its ID.
- **Response**:
  ```json
  {
    "message": "Task deleted successfully"
  }
  ```

## Task Board Endpoints

### Get Task Board by ID
- **Endpoint**: `/task_boards/<int:board_id>`
- **Method**: `GET`
- **Description**: Retrieve a task board by its ID.
- **Response**:
  ```json
  {
    "BoardID": 1,
    "Name": "Sample Board"
  }
  ```

### Create a New Task Board
- **Endpoint**: `/task_boards`
- **Method**: `POST`
- **Description**: Create a new task board.
- **Request Body**:
  ```json
  {
    "Name": "New Board"
  }
  ```
- **Response**:
  ```json
  {
    "message": "Task board created successfully"
  }
  ```

### Update a Task Board by ID
- **Endpoint**: `/task_boards/<int:board_id>`
- **Method**: `PUT`
- **Description**: Update an existing task board by its ID.
- **Request Body**:
  ```json
  {
    "Name": "Updated Board"
  }
  ```
- **Response**:
  ```json
  {
    "message": "Task board updated successfully"
  }
  ```

### Delete a Task Board by ID
- **Endpoint**: `/task_boards/<int:board_id>`
- **Method**: `DELETE`
- **Description**: Delete a task board by its ID.
- **Response**:
  ```json
  {
    "message": "Task board deleted successfully"
  }
  ```

## User Endpoints

### Get User by ID
- **Endpoint**: `/users/<int:user_id>`
- **Method**: `GET`
- **Description**: Retrieve a user by their ID.
- **Response**:
  ```json
  {
    "UserID": 1,
    "Username": "sampleuser"
  }
  ```

### Create a New User
- **Endpoint**: 

users


- **Method**: `POST`
- **Description**: Create a new user.
- **Request Body**:
  ```json
  {
    "Username": "newuser"
  }
  ```
- **Response**:
  ```json
  {
    "message": "User created successfully"
  }
  ```

### Update a User by ID
- **Endpoint**: `/users/<int:user_id>`
- **Method**: `PUT`
- **Description**: Update an existing user by their ID.
- **Request Body**:
  ```json
  {
    "Username": "updateduser"
  }
  ```
- **Response**:
  ```json
  {
    "message": "User updated successfully"
  }
  ```

### Delete a User by ID
- **Endpoint**: `/users/<int:user_id>`
- **Method**: `DELETE`
- **Description**: Delete a user by their ID.
- **Response**:
  ```json
  {
    "message": "User deleted successfully"
  }
  ```

## Board User Endpoints

### Get Board User by IDs
- **Endpoint**: `/board_users/<int:board_id>/<int:user_id>`
- **Method**: `GET`
- **Description**: Retrieve a board user by their board ID and user ID.
- **Response**:
  ```json
  {
    "BoardID": 1,
    "UserID": 1,
    "PermissionLevel": 2
  }
  ```

### Create a New Board User
- **Endpoint**: `/board_users`
- **Method**: `POST`
- **Description**: Create a new board user.
- **Request Body**:
  ```json
  {
    "BoardID": 1,
    "UserID": 1,
    "PermissionLevel": 2
  }
  ```
- **Response**:
  ```json
  {
    "message": "Board user created successfully"
  }
  ```

### Update a Board User by IDs
- **Endpoint**: `/board_users/<int:board_id>/<int:user_id>`
- **Method**: `PUT`
- **Description**: Update an existing board user by their board ID and user ID.
- **Request Body**:
  ```json
  {
    "PermissionLevel": 3
  }
  ```
- **Response**:
  ```json
  {
    "message": "Board user updated successfully"
  }
  ```

### Delete a Board User by IDs
- **Endpoint**: `/board_users/<int:board_id>/<int:user_id>`
- **Method**: `DELETE`
- **Description**: Delete a board user by their board ID and user ID.
- **Response**:
  ```json
  {
    "message": "Board user deleted successfully"
  }
  ```
