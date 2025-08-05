create table movie_category(
    movie_id UUID,
    category_id UUID,
    constraint fk_movie_category_movie foreign key(movie_id) references movie(id),
    constraint fk_movie_category_category foreign key(category_id) references category(id)
);