-- Create tables if they don't exist
CREATE TABLE IF NOT EXISTS users
(
    email    VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    code     VARCHAR(255),
    active   BOOLEAN      NOT NULL
);

CREATE TABLE IF NOT EXISTS playlists
(
    playlist_id INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    owner       VARCHAR(255),
    FOREIGN KEY (owner) REFERENCES users (email)
);

CREATE TABLE IF NOT EXISTS songs
(
    song_id INT PRIMARY KEY AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL,
    artist  VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS playlist_songs
(
    playlist_id INT,
    song_id     INT,
    PRIMARY KEY (playlist_id, song_id),
    FOREIGN KEY (playlist_id) REFERENCES playlists (playlist_id),
    FOREIGN KEY (song_id) REFERENCES songs (song_id)
);
