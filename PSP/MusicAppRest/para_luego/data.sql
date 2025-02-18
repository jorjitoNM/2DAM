-- Delete existing data (if any) in the correct order to avoid foreign key constraints
delete from playlist_songs;
delete from songs;
delete from playlists;
delete from users;


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