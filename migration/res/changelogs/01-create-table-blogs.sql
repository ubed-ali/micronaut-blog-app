CREATE TABLE IF NOT EXISTS blogs(
    id BIG-SERIAL,
    user_id INTEGER,
    title VARCHAR,
    content TEXT,
    published BOOLEAN SET DEFAULT FALSE,
    published_at TIMESTAMP,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES blogs_users(id)
);
