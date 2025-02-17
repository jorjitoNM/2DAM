-- Delete existing data (if any) in the correct order to avoid foreign key constraints
DELETE FROM playlist_songs;
DELETE FROM songs;
DELETE FROM playlists;
DELETE FROM users;

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

-- Insert data into users
INSERT INTO users (email, password, code, active)
VALUES ('john@example.com', 'password123', 'ABC123', true),
       ('jane@example.com', 'password456', 'DEF456', false);

-- Insert data into songs
INSERT INTO songs (name, artist)
VALUES ('Bohemian Rhapsody', 'Queen'),
       ('Shape of You', 'Ed Sheeran'),
       ('Rolling in the Deep', 'Adele'),
       ('Billie Jean', 'Michael Jackson'),
       ('Hotel California', 'Eagles'),
       ('Blinding Lights', 'The Weeknd'),
       ('Stairway to Heaven', 'Led Zeppelin'),
       ('Uptown Funk', 'Mark Ronson ft. Bruno Mars'),
       ('Someone Like You', 'Adele'),
       ('Smells Like Teen Spirit', 'Nirvana');

-- Insert data into playlists (use valid email addresses for the owner column)
INSERT INTO playlists (name, owner)
VALUES ('Classic Rock Hits', 'john@example.com'),  -- Owned by John
       ('Pop Favorites', 'jane@example.com');      -- Owned by Jane

-- Insert data into playlist_songs
INSERT INTO playlist_songs (playlist_id, song_id)
VALUES (1, 1),  -- Bohemian Rhapsody in Classic Rock Hits
       (1, 5),  -- Hotel California in Classic Rock Hits
       (1, 7),  -- Stairway to Heaven in Classic Rock Hits
       (1, 10), -- Smells Like Teen Spirit in Classic Rock Hits
       (2, 2),  -- Shape of You in Pop Favorites
       (2, 3),  -- Rolling in the Deep in Pop Favorites
       (2, 6),  -- Blinding Lights in Pop Favorites
       (2, 8),  -- Uptown Funk in Pop Favorites
       (2, 9);  -- Someone Like You in Pop Favorites