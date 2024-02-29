CREATE TABLE drawings (
    id SERIAL PRIMARY KEY,
    user_id INTEGER,
    description TEXT,
    image_url VARCHAR(200),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
