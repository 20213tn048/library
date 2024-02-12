-- Insertar géneros
INSERT INTO genres (description) VALUES
('Novela'),
('Poesía'),
('Drama'),
('Ciencia ficción'),
('Fantasía'),
('Misterio'),
('Terror'),
('Aventura'),
('Suspenso'),
('Juvenil');

-- Insertar libros
INSERT INTO books (name, author, fk_genre_id, publication_year, photo) VALUES
('Cien años de soledad', 'Gabriel García Márquez', 1, 1967, 'C:/Users/DELL/Documents/library/frontend/src/assets/images/soledad.jpg'),
('Romeo y Julieta', 'William Shakespeare', 3, 1597, 'C:/Users/DELL/Documents/library/frontend/src/assets/images/romeoyjulieta.jpg'),
('1984', 'George Orwell', 4, 1949, 'C:/Users/DELL/Documents/library/frontend/src/assets/images/1984.jpg'),
('El señor de los anillos', 'J.R.R. Tolkien', 5, 1954, 'C:/Users/DELL/Documents/library/frontend/src/assets/images/anillo.jpg'),
('El código Da Vinci', 'Dan Brown', 6, 2003, 'C:/Users/DELL/Documents/library/frontend/src/assets/images/davinci.jpg'),
('Drácula', 'Bram Stoker', 7, 1897, 'C:/Users/DELL/Documents/library/frontend/src/assets/images/dracula.jpg'),
('Harry Potter y la piedra filosofal', 'J.K. Rowling', 9, 1997, 'C:/Users/DELL/Documents/library/frontend/src/assets/images/harrypotter.jpg'),
('La isla del tesoro', 'Robert Louis Stevenson', 8, 1883, 'C:/Users/DELL/Documents/library/frontend/src/assets/images/tesoro.jpg');