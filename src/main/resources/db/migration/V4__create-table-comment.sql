CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    user_id INTEGER,
    drawing_id INTEGER,
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (drawing_id) REFERENCES drawings(id)
);