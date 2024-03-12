CREATE TABLE favorites (
    id SERIAL PRIMARY KEY,
    user_id INTEGER,
    drawing_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (drawing_id) REFERENCES drawings(id)
);