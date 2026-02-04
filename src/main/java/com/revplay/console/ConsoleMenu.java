package com.revplay.console;

import com.revplay.dto.*;
import com.revplay.enums.Role;
import com.revplay.enums.PrivacyType;
import com.revplay.model.*;
import com.revplay.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleMenu implements CommandLineRunner {

    private final AuthService authService;
    private final UserService userService;
    private final ArtistService artistService;
    private final SongService songService;
    private final AlbumService albumService;
    private final PlaylistService playlistService;
    private final PlayerService playerService;
    private final FavoriteService favoriteService;

    private User loggedInUser = null;

    public ConsoleMenu(AuthService authService,
                       UserService userService,
                       ArtistService artistService,
                       SongService songService,
                       AlbumService albumService,
                       PlaylistService playlistService,
                       PlayerService playerService,
                       FavoriteService favoriteService) {
        this.authService = authService;
        this.userService = userService;
        this.artistService = artistService;
        this.songService = songService;
        this.albumService = albumService;
        this.playlistService = playlistService;
        this.playerService = playerService;
        this.favoriteService = favoriteService;
    }

    @Override
    public void run(String... args) {
        // Run menu on a separate thread so Spring startup does not block
        new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            while (true) {
                try {
                    if (loggedInUser == null) {
                        showLoginRegisterMenu(sc);
                    } else {
                        if (loggedInUser.getRole() == Role.USER) {
                            userMenu(sc, loggedInUser);
                        } else if (loggedInUser.getRole() == Role.ARTIST) {
                            artistMenu(sc, loggedInUser);
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    System.out.flush();
                }
            }
        }).start();
    }

    private void showLoginRegisterMenu(Scanner sc) {
        System.out.println("\n=== REVPLAY CONSOLE ===");
        System.out.println("1. Register as User");
        System.out.println("2. Register as Artist");
        System.out.println("3. Login");
        System.out.println("4. Exit");
        System.out.print("Choice: ");
        System.out.flush();

        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
            case 2:
                RegisterRequest reg = new RegisterRequest();
                System.out.print("Email: "); System.out.flush();
                reg.setEmail(sc.nextLine());
                System.out.print("Password: "); System.out.flush();
                reg.setPassword(sc.nextLine());
                System.out.print("Password Hint: "); System.out.flush();
                reg.setPasswordHint(sc.nextLine());
                Role role = (choice == 1) ? Role.USER : Role.ARTIST;
                loggedInUser = authService.register(reg, role);
                System.out.println("Registered successfully: " + loggedInUser.getEmail());
                System.out.flush();
                break;
            case 3:
                LoginRequest login = new LoginRequest();
                System.out.print("Email: "); System.out.flush();
                login.setEmail(sc.nextLine());
                System.out.print("Password: "); System.out.flush();
                login.setPassword(sc.nextLine());
                loggedInUser = authService.login(login);
                System.out.println("Logged in successfully as " + loggedInUser.getRole());
                System.out.flush();
                break;
            case 4:
                System.out.println("Exiting...");
                System.out.flush();
                System.exit(0);
            default:
                System.out.println("Invalid choice.");
                System.out.flush();
        }
    }

    private void userMenu(Scanner sc, User user) {
        System.out.println("\n=== USER MENU ===");
        System.out.println("1. Search Songs");
        System.out.println("2. View Favorite Songs");
        System.out.println("3. Mark Song as Favorite");
        System.out.println("4. Play a Song");
        System.out.println("5. Manage Playlists");
        System.out.println("6. Logout");
        System.out.print("Choice: "); System.out.flush();

        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                System.out.print("Enter keyword: "); System.out.flush();
                List<Song> songs = songService.search(sc.nextLine());
                songs.forEach(s -> System.out.println(s.getId() + ". " + s.getTitle()));
                System.out.flush();
                break;
            case 2:
                List<Favorite> favs = favoriteService.getUserFavorites(user);
                favs.forEach(f -> System.out.println(f.getSong().getTitle()));
                System.out.flush();
                break;
            case 3:
                System.out.print("Enter Song ID to favorite: "); System.out.flush();
                Long songId = Long.parseLong(sc.nextLine());
                Song song = songService.getById(songId);
                favoriteService.addFavorite(user, song);
                System.out.println("Song added to favorites!");
                System.out.flush();
                break;
            case 4:
                System.out.print("Enter Song ID to play: "); System.out.flush();
                Long playId = Long.parseLong(sc.nextLine());
                Song playSong = songService.getById(playId);
                playerService.play(user, playSong);
                System.out.println("Playing: " + playSong.getTitle());
                System.out.flush();
                break;
            case 5:
                playlistSubMenu(sc, user);
                break;
            case 6:
                loggedInUser = null;
                System.out.println("Logged out successfully.");
                System.out.flush();
                break;
            default:
                System.out.println("Invalid choice.");
                System.out.flush();
        }
    }

    private void playlistSubMenu(Scanner sc, User user) {
        System.out.println("\n=== PLAYLIST MENU ===");
        System.out.println("1. Create Playlist");
        System.out.println("2. Add Song to Playlist");
        System.out.println("3. View My Playlists");
        System.out.println("4. View Public Playlists");
        System.out.print("Choice: "); System.out.flush();

        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                PlaylistRequest req = new PlaylistRequest();
                System.out.print("Name: "); System.out.flush();
                req.setName(sc.nextLine());
                System.out.print("Description: "); System.out.flush();
                req.setDescription(sc.nextLine());
                System.out.print("Privacy (PUBLIC/PRIVATE): "); System.out.flush();
                req.setPrivacyType(PrivacyType.valueOf(sc.nextLine().toUpperCase()));
                Playlist p = playlistService.create(user, req);
                System.out.println("Created playlist: " + p.getName());
                System.out.flush();
                break;
            case 2:
                System.out.print("Playlist ID: "); System.out.flush();
                Long pid = Long.parseLong(sc.nextLine());
                System.out.print("Song ID: "); System.out.flush();
                Long sid = Long.parseLong(sc.nextLine());
                playlistService.addSong(pid, sid);
                System.out.println("Song added to playlist!");
                System.out.flush();
                break;
            case 3:
                List<Playlist> playlists = playlistService.getUserPlaylists(user);
                playlists.forEach(pl -> System.out.println(pl.getId() + ". " + pl.getName()));
                System.out.flush();
                break;
            case 4:
                List<Playlist> publicPl = playlistService.getPublicPlaylists();
                publicPl.forEach(pl -> System.out.println(pl.getId() + ". " + pl.getName()));
                System.out.flush();
                break;
            default:
                System.out.println("Invalid choice.");
                System.out.flush();
        }
    }

    private void artistMenu(Scanner sc, User user) {
        Artist artist = artistService.getByUser(user);

        System.out.println("\n=== ARTIST MENU ===");
        System.out.println("1. Create Profile");
        System.out.println("2. Upload Song");
        System.out.println("3. Create Album");
        System.out.println("4. View My Songs");
        System.out.println("5. Logout");
        System.out.print("Choice: "); System.out.flush();

        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                ArtistProfileRequest profileReq = new ArtistProfileRequest();
                System.out.print("Bio: "); System.out.flush();
                profileReq.setBio(sc.nextLine());
                System.out.print("Social Links (comma separated): "); System.out.flush();
                profileReq.setSocialLinks(sc.nextLine());
                artistService.createProfile(user, profileReq);
                System.out.println("Profile created!");
                System.out.flush();
                break;

            case 2:
                SongRequest songReq = new SongRequest();
                System.out.print("Song Title: "); System.out.flush();
                songReq.setTitle(sc.nextLine());
                System.out.print("Duration (seconds): "); System.out.flush();
                songReq.setDuration(Integer.parseInt(sc.nextLine()));
                System.out.print("Genre: "); System.out.flush();
                songReq.setGenre(sc.nextLine());
                System.out.print("Album ID (optional, 0 if none): "); System.out.flush();
                Long albumId = Long.parseLong(sc.nextLine());
                Album album = (albumId != 0) ? albumService.getById(albumId) : null;
                songService.upload(artist, songReq, album);
                System.out.println("Song uploaded!");
                System.out.flush();
                break;

            case 3:
                System.out.print("Album Title: "); System.out.flush();
                String albumTitle = sc.nextLine();
                albumService.createAlbum(artist, albumTitle);
                System.out.println("Album created!");
                System.out.flush();
                break;

            case 4:
                List<Song> mySongs = songService.getArtistSongs(artist);
                mySongs.forEach(s -> System.out.println(s.getId() + ". " + s.getTitle()));
                System.out.flush();
                break;

            case 5:
                loggedInUser = null;
                System.out.println("Logged out successfully.");
                System.out.flush();
                break;

            default:
                System.out.println("Invalid choice.");
                System.out.flush();
        }
    }
}
