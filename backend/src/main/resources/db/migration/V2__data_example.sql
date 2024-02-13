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
INSERT INTO books (name, author, fk_genre_id, publication_year) VALUES
('Cien años de soledad', 'Gabriel García Márquez', 1, 1967),
('Romeo y Julieta', 'William Shakespeare', 3, 1597),
('1984', 'George Orwell', 4, 1949),
('El señor de los anillos', 'J.R.R. Tolkien', 5, 1954),
('El código Da Vinci', 'Dan Brown', 6, 2003),
('Drácula', 'Bram Stoker', 7, 1897),
('Harry Potter y la piedra filosofal', 'J.K. Rowling', 9, 1997),
('La isla del tesoro', 'Robert Louis Stevenson', 8, 1883);