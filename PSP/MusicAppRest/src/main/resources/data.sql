CREATE TABLE IF NOT EXIST users
(
    user_id
    INT
    PRIMARY
    KEY
    AUTO_INCREMENT,
    email
    VARCHAR
(
    255
) NOT NULL,
    password VARCHAR
(
    255
) NOT NULL,
    code VARCHAR
(
    255
),
    active BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXIST playlists
(
    playlist_id
    INT
    PRIMARY
    KEY
    AUTO_INCREMENT,
    name
    VARCHAR
(
    255
) NOT NULL,
    owner INT,
    FOREIGN KEY
(
    owner
) REFERENCES users
(
    user_id
)
    );

CREATE TABLE IF NOT EXIST songs
(
    song_id
    INT
    PRIMARY
    KEY
    AUTO_INCREMENT,
    name
    VARCHAR
(
    255
) NOT NULL,
    artist VARCHAR
(
    255
)
    );

CREATE TABLE IF NOT EXIST playlist_songs
(
    playlist_id
    INT,
    song_id
    INT,
    PRIMARY
    KEY
(
    playlist_id,
    song_id
),
    FOREIGN KEY
(
    playlist_id
) REFERENCES playlists
(
    playlist_id
),
    FOREIGN KEY
(
    song_id
) REFERENCES songs
(
    song_id
)
    );

delete
* from playlist_songs;
delete
* from songs;
delete
* from playlists;
delete
* from users;


INSERT INTO users (email, password, code, active)
VALUES ('john@example.com', 'password123', 'ABC123', true),
       ('jane@example.com', 'password456', 'DEF456', false);

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


INSERT INTO playlists (name, owner)
VALUES ('Classic Rock Hits', 1),
       ('Pop Favorites', 2);

INSERT INTO playlist_songs (playlist_id, song_id)
VALUES (1, 1),
       (1, 5),
       (1, 7),
       (1, 10),
       (2, 2),
       (2, 3),
       (2, 6),
       (2, 8),
       (2, 9);
