CREATE TABLE followers (
    id SERIAL PRIMARY KEY,
    follower_user_id INTEGER,
    followed_user_id INTEGER,
    FOREIGN KEY (follower_user_id) REFERENCES users(id),
    FOREIGN KEY (followed_user_id) REFERENCES users(id),
    CONSTRAINT unique_followers UNIQUE (follower_user_id, followed_user_id)
);