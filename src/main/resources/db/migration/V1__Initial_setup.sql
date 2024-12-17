-- Create table for User
CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL
);

-- Create table for TaskBoard
CREATE TABLE task_boards
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(500),
    is_public   BOOLEAN      NOT NULL,
    user_id     BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Create table for Task
CREATE TABLE tasks
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    description      TEXT,
    status           VARCHAR(50)  NOT NULL,
    due_date         TIMESTAMP,
    task_board_id    BIGINT,
    assigned_user_id BIGINT,
    FOREIGN KEY (task_board_id) REFERENCES task_boards (id),
    FOREIGN KEY (assigned_user_id) REFERENCES users (id)
);

CREATE TABLE task_board_users
(
    task_board_id BIGINT NOT NULL,
    user_id       BIGINT NOT NULL,
    PRIMARY KEY (task_board_id, user_id),
    FOREIGN KEY (task_board_id) REFERENCES task_boards (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);