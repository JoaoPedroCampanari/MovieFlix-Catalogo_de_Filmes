create table movie_streaming(
    movie_id UUID,
    streaming_id UUID,
    constraint fk_movie_streaming_movie foreign key(movie_id) references movie(id),
    constraint fk_movie_streaming_streaming foreign key(streaming_id) references streaming(id)
);