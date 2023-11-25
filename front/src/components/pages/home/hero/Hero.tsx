import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { Howl, Howler } from 'howler';

const Hero: React.FC = () => {
  const [playlistTracks, setPlaylistTracks] = useState([]);
  const [currentTrackIndex, setCurrentTrackIndex] = useState(null);
  const [playlistSound, setPlaylistSound] = useState(null);
  const [isPlaying, setIsPlaying] = useState(false);

  useEffect(() => {
    const fetchPlaylistTracks = async () => {
      try {
        const response = await fetch(
          'http://localhost:8080/https://api.deezer.com/user/2529/playlists'
        );
        const data = await response.json();

        if (data && data.data && data.data.length > 0) {
          const playlistId = data.data[0].id;
          const playlistResponse = await fetch(
            `http://localhost:8080/https://api.deezer.com/playlist/${playlistId}/tracks`
          );
          const playlistData = await playlistResponse.json();

          if (playlistData && playlistData.data) {
            setPlaylistTracks(playlistData.data);
          }
        }
      } catch (error) {
        console.error('Error fetching playlist:', error);
      }
    };

    fetchPlaylistTracks();
  }, []);

  useEffect(() => {
    if (playlistTracks.length > 0 && currentTrackIndex !== null) {
      const audioUrl = playlistTracks[currentTrackIndex].preview;

      if (playlistSound) {
        playlistSound.unload();
      }

      const newPlaylistSound = new Howl({
        src: [audioUrl],
        volume: 0.5,
        html5: true,
        autoplay: isPlaying,
        format: ['mp3', 'ogg', 'aac'],

        onload: () => {
          // При успешной загрузке музыки
        },
        onend: () => {
          // Когда воспроизведение завершено

          playNextTrack();
        },
      });

      setPlaylistSound(newPlaylistSound);

      // Воспроизводим выбранный трек
      if (isPlaying) {
        newPlaylistSound.play();
      }

      // Выгружаем ресурсы при размонтировании компонента или при изменении трека
      return () => {
        newPlaylistSound.unload();
      };
    }
  }, [playlistTracks, currentTrackIndex, isPlaying]);

  const playPauseToggle = () => {
    setIsPlaying((prevIsPlaying) => !prevIsPlaying);

    if (playlistSound) {
      if (isPlaying) {
        playlistSound.pause();
      } else {
        playlistSound.play();
      }
    }
  };

  const playNextTrack = () => {
    setCurrentTrackIndex((prevIndex) => {
      const newIndex =
        prevIndex === null ? 0 : (prevIndex + 1) % playlistTracks.length;
      return newIndex;
    });
    setIsPlaying(true); // Автоматически начать воспроизведение следующего трека
  };

  const playPrevTrack = () => {
    setCurrentTrackIndex((prevIndex) => {
      const newIndex =
        prevIndex === null
          ? 0
          : (prevIndex - 1 + playlistTracks.length) % playlistTracks.length;
      return newIndex;
    });
    setIsPlaying(true); // Автоматически начать воспроизведение предыдущего трека
  };

  return (
    <PlaylistPlayerContainer>
      <h2>Playlist</h2>
      {currentTrackIndex !== null && (
        <TrackInfoContainer>
          <AlbumCover
            src={playlistTracks[currentTrackIndex].album.cover_medium}
            alt="Album Cover"
          />
          <TrackDetails>
            <TrackTitle>
              Now Playing: {playlistTracks[currentTrackIndex].title}
            </TrackTitle>
            <ArtistName>
              Artist: {playlistTracks[currentTrackIndex].artist.name}
            </ArtistName>
          </TrackDetails>
        </TrackInfoContainer>
      )}
      {playlistTracks.length > 0 && (
        <PlayerControlsContainer>
          <ControlButton onClick={playPrevTrack}>Previous</ControlButton>
          <ControlButton onClick={playPauseToggle}>
            {isPlaying ? <span>Pause</span> : <span>Play</span>}
          </ControlButton>
          <ControlButton onClick={playNextTrack}>Next</ControlButton>
        </PlayerControlsContainer>
      )}
      <TrackListContainer>
        {playlistTracks.map((track, index) => (
          <li key={track.id}>
            <TrackButton onClick={() => setCurrentTrackIndex(index)}>
              {track.title}
            </TrackButton>
          </li>
        ))}
      </TrackListContainer>
    </PlaylistPlayerContainer>
  );
};

export default Hero;

const PlaylistPlayerContainer = styled.div`
  max-width: 400px;
  margin: 20px auto;
  background-color: #f5f5f5;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
`;
const TrackInfoContainer = styled.div`
  display: flex;
  align-items: center;
  margin-top: 20px;
`;

const AlbumCover = styled.img`
  width: 80px;
  height: 80px;
  border-radius: 50%;
  margin-right: 20px;
`;

const TrackDetails = styled.div`
  flex-grow: 1;
`;

const TrackTitle = styled.p`
  font-size: 18px;
`;

const ArtistName = styled.p`
  color: #666;
`;

const PlayerControlsContainer = styled.div`
  display: flex;
  justify-content: space-around;

  margin-top: 20px;
`;

const ControlButton = styled.button`
  background-color: #3498db;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #2980b9;
  }
`;

const TrackListContainer = styled.ul`
  margin-top: 20px;
`;

const TrackButton = styled.button`
  background-color: #ecf0f1;
  color: #333;
  border: none;
  padding: 10px;
  margin: 5px 0;
  width: 100%;
  text-align: left;
  cursor: pointer;
  border-radius: 5px;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #d5d8dc;
  }
`;
