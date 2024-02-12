CREATE TABLE IF NOT EXISTS genres(
    genre_id BIGINT AUTO_INCREMENT,
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (genre_id)
);

CREATE TABLE IF NOT EXISTS books(
    book_id BIGINT AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    fk_genre_id BIGINT NOT NULL,
    publication_year SMALLINT UNSIGNED ZEROFILL,
    photo VARCHAR(255) NOT NULL,
    primary key (book_id),
    foreign key (fk_genre_id) references genres(genre_id)
);