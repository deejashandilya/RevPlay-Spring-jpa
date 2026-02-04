package com.revplay.service;



import com.revplay.dto.SongRequest;
import com.revplay.model.Album;
import com.revplay.model.Artist;
import com.revplay.model.Song;
import com.revplay.repository.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class SongServiceTest {

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private SongService songService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void uploadSong_success() {
        // GIVEN
        Artist artist = new Artist();
        Album album = new Album();

        SongRequest request = new SongRequest();
        request.setTitle("Test Song");
        request.setGenre("POP");
        request.setDuration(200);

        Song savedSong = new Song();
        savedSong.setTitle("Test Song");

        when(songRepository.save(any(Song.class))).thenReturn(savedSong);

        // WHEN
        Song result = songService.upload(artist, request, album);

        // THEN
        assertNotNull(result);
        assertEquals("Test Song", result.getTitle());

        verify(songRepository, times(1)).save(any(Song.class));
    }
}
