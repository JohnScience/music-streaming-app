import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { Howl, Howler } from 'howler';

const Hero: React.FC = () => {
  const [playlistTracks, setPlaylistTracks] = useState([]);
  const [currentTrackIndex, setCurrentTrackIndex] = useState(null);
  const [playlistSound, setPlaylistSound] = useState(null);
  const [isPlaying, setIsPlaying] = useState(false);
<<<<<<< Updated upstream

=======
  const [timeRemaining, setTimeRemaining] = useState(0);
  const [duration, setDuration] = useState(0);

  const formatDuration = (duration: number) => {
    const minutes = Math.floor(duration / 60);
    const seconds = Math.floor(duration % 60);
    return `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
  };
>>>>>>> Stashed changes
  useEffect(() => {
    const fetchPlaylistTracks = async () => {
      try {
        const response = await fetch(
          'http://localhost:8080/https://api.deezer.com/user/2529/playlists'
        );
        const data = await response.json();

        if (data && data.data && data.data.length > 0) {
<<<<<<< Updated upstream
          const playlistId = data.data[0].id;
=======
          const playlistId = data.data[2].id;
>>>>>>> Stashed changes
          const playlistResponse = await fetch(
            `http://localhost:8080/https://api.deezer.com/playlist/${playlistId}/tracks`
          );
          const playlistData = await playlistResponse.json();
<<<<<<< Updated upstream
=======
          console.log(playlistData);
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
          // При успешной загрузке музыки
        },
        onend: () => {
          // Когда воспроизведение завершено

=======
          console.log('Audio duration:', newPlaylistSound.duration());
        },
        onplay: () => {
          const duration = newPlaylistSound.duration();

          startCountdown(duration);
        },
        onend: () => {
>>>>>>> Stashed changes
          playNextTrack();
        },
      });

      setPlaylistSound(newPlaylistSound);

<<<<<<< Updated upstream
      // Воспроизводим выбранный трек
=======
>>>>>>> Stashed changes
      if (isPlaying) {
        newPlaylistSound.play();
      }

<<<<<<< Updated upstream
      // Выгружаем ресурсы при размонтировании компонента или при изменении трека
=======
>>>>>>> Stashed changes
      return () => {
        newPlaylistSound.unload();
      };
    }
  }, [playlistTracks, currentTrackIndex, isPlaying]);
<<<<<<< Updated upstream
=======
  const playTrack = (index) => {
    if (index === currentTrackIndex) {
      playPauseToggle();
      setCurrentTrackIndex(null);
    } else {
      setCurrentTrackIndex(index);
      setIsPlaying(true);
    }
  };
>>>>>>> Stashed changes

  const playPauseToggle = () => {
    setIsPlaying((prevIsPlaying) => !prevIsPlaying);

    if (playlistSound) {
      if (isPlaying) {
        playlistSound.pause();
      } else {
        playlistSound.play();
      }
<<<<<<< Updated upstream
=======
    } else {
      playNextTrack();
>>>>>>> Stashed changes
    }
  };

  const playNextTrack = () => {
    setCurrentTrackIndex((prevIndex) => {
      const newIndex =
        prevIndex === null ? 0 : (prevIndex + 1) % playlistTracks.length;
      return newIndex;
    });
<<<<<<< Updated upstream
    setIsPlaying(true); // Автоматически начать воспроизведение следующего трека
=======
    setIsPlaying(true);
>>>>>>> Stashed changes
  };

  const playPrevTrack = () => {
    setCurrentTrackIndex((prevIndex) => {
      const newIndex =
        prevIndex === null
          ? 0
          : (prevIndex - 1 + playlistTracks.length) % playlistTracks.length;
      return newIndex;
    });
<<<<<<< Updated upstream
    setIsPlaying(true); // Автоматически начать воспроизведение предыдущего трека
=======
    setIsPlaying(true);
  };

  const startCountdown = (duration: number) => {
    let remainingTime = duration;
    const countdownInterval = setInterval(() => {
      setTimeRemaining(remainingTime);
      remainingTime -= 1;

      if (remainingTime < 0) {
        clearInterval(countdownInterval);
        setTimeRemaining(0);
      }
    }, 1000);
>>>>>>> Stashed changes
  };

  return (
    <PlaylistPlayerContainer>
      <h2>Playlist</h2>
<<<<<<< Updated upstream
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
=======

      <ControlButton onClick={playPauseToggle}>
        {isPlaying ? <span>Pause</span> : <span>Play</span>}
      </ControlButton>
      {playlistTracks.length > 0 && (
        <PlayerControlsContainer>
          <ControlButton onClick={playPrevTrack}>Previous</ControlButton>

          <ControlButton onClick={playNextTrack}>Next</ControlButton>
        </PlayerControlsContainer>
      )}
      <TrackList>
        {playlistTracks.map((track, index) => {
          console.log(track);
          return (
            <ListItem key={track.id} isActive={currentTrackIndex === index}>
              <TrackInfoContainer>
                <TrackNumber>{index + 1}</TrackNumber>
                <TrackImage
                  src={track.artist.picture_small}
                  alt="Album Cover"
                />

                <TrackTitle>{track.title}</TrackTitle>
                {/* <Duration>{formatDuration(track.duration)}</Duration> */}
              </TrackInfoContainer>
              <div>
                {timeRemaining > 0 && currentTrackIndex === index && (
                  <CountdownContainer>{timeRemaining}</CountdownContainer>
                )}
              </div>
              <TrackButton
                onClick={() => playTrack(index)}
                isActive={currentTrackIndex === index}
              >
                {currentTrackIndex === index ? (
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="18"
                    height="22"
                    viewBox="0 0 18 26"
                    fill="none"
                  >
                    <rect
                      width="5"
                      height="26"
                      rx="2.5"
                      fill="url(#paint0_linear_9_59)"
                    />
                    <rect
                      x="13"
                      width="5"
                      height="26"
                      rx="2.5"
                      fill="url(#paint1_linear_9_59)"
                    />
                    <defs>
                      <linearGradient
                        id="paint0_linear_9_59"
                        x1="-0.644654"
                        y1="-4.00001"
                        x2="6.63814"
                        y2="-3.91413"
                        gradientUnits="userSpaceOnUse"
                      >
                        <stop stop-color="#B5179E" />
                        <stop offset="1" stop-color="#7209B7" />
                      </linearGradient>
                      <linearGradient
                        id="paint1_linear_9_59"
                        x1="12.3553"
                        y1="-4.00001"
                        x2="19.6381"
                        y2="-3.91413"
                        gradientUnits="userSpaceOnUse"
                      >
                        <stop stop-color="#B5179E" />
                        <stop offset="1" stop-color="#7209B7" />
                      </linearGradient>
                    </defs>
                  </svg>
                ) : (
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="19"
                    height="22"
                    viewBox="0 0 19 22"
                    fill="none"
                  >
                    <path
                      d="M18.5 10.134C19.1667 10.5189 19.1667 11.4811 18.5 11.866L2 21.3923C1.33333 21.7772 0.5 21.2961 0.5 20.5263L0.5 1.47372C0.5 0.703919 1.33333 0.222795 2 0.607695L18.5 10.134Z"
                      fill="#B8B8B8"
                    />
                  </svg>
                )}
              </TrackButton>
            </ListItem>
          );
        })}
      </TrackList>
>>>>>>> Stashed changes
    </PlaylistPlayerContainer>
  );
};

export default Hero;

const PlaylistPlayerContainer = styled.div`
<<<<<<< Updated upstream
  max-width: 400px;
  margin: 20px auto;
  background-color: #f5f5f5;
=======
  margin: 20px auto;
>>>>>>> Stashed changes
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
`;
const TrackInfoContainer = styled.div`
  display: flex;
  align-items: center;
<<<<<<< Updated upstream
  margin-top: 20px;
`;

const AlbumCover = styled.img`
  width: 80px;
  height: 80px;
=======
  column-gap: 40px;
`;

const TrackImage = styled.img`
>>>>>>> Stashed changes
  border-radius: 50%;
  margin-right: 20px;
`;

<<<<<<< Updated upstream
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
=======
const PlayerControlsContainer = styled.div`
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
`;
const CountdownContainer = styled.div``;
const ControlButton = styled.button`
  border: none;
  background: none;
  color: white;
  padding: 0;
  cursor: pointer;
`;

const Duration = styled.div``;

const TrackList = styled.ul`
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  row-gap: 16px;
`;

const ListItem = styled.li<{ isActive: boolean }>`
  color: ${({ isActive }) => (isActive ? '#7209B7' : '#b8b8b8;')};
  background: rgba(35, 35, 35, 0.3);
  display: flex;
  align-items: center;
  transition: 0.3s;
  justify-content: space-between;
  padding: 12px 17px 12px 17px;
`;

const TrackButton = styled.button<{ isActive: boolean }>`
  background: none;
  border: none;
  text-align: left;
  cursor: pointer;
  border-radius: 5px;
  transition: 0.3s;
`;
const TrackTitle = styled.p`
  font-size: 18px;
`;

const TrackNumber = styled.span`
  font-family: Inter;
  font-size: 27px;
  font-style: normal;
  font-weight: 500;
  line-height: normal;
  letter-spacing: 0.54px;
  text-transform: capitalize;
>>>>>>> Stashed changes
`;
