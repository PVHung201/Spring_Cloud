CREATE TABLE IF NOT EXISTS blog_service (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    author_id VARCHAR(255) NOT NULL,
    post_date TIMESTAMP
);