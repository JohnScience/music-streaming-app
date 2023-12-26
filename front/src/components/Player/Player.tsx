import { useEffect, useState, useRef } from "react";
import gala from "../PlayList/assets/Melanie Martinez - Cake.mp3";
import { styled }  from "styled-components"
import React from "react";
import Pausa from "../PlayList/assets/Pausa 2.svg"
import Volume from "../PlayList/assets/Group (1).svg"
import Play from "../PlayList/assets/Play.svg"
import imageMusic from "../PlayList/assets/images.jpg"

const Button = styled.button`
background-color: #e03e2500; 
    border: none; 
    &:hover{
      cursor: pointer;
    }
    padding-right: 5rem;
    
`
const Time = styled.div`
display: flex;
justify-content: space-between;
`

const PlayerComponent = styled.li`
display: flex;   
background-color: #1a1918;
width: 95%;
font-weight: bold;
font-size: 1.1rem ;
color: ${({$invalid})=> $invalid ? `#9c088e` : `#bab6b6`}; 

margin: 1rem;
justify-content: space-around; 
border:${({$invalid})=> $invalid ? `1px solid #212121` : `1px solid #212121`};
border-radius: 5px;
align-items: center ;
box-shadow:${({$invalid})=> $invalid ? `0 0 5px 2px #E283D2` : `none`}  ;
`
const Image = styled.div`
img{
    margin:1rem;
border-radius: 60%;
      height: 6rem;
    box-shadow: 0 0 2px 2px #9c088e;
  }
`
const Title = styled.div`
width: 10%;
    align-items: start ;
   
`
const FlexBox = styled.div`
display: flex;
`
const ButtonVolume = styled.div`
padding-right: 2rem;
color: ${({$invalid})=> $invalid ? `#9c088e` : `#bab6b6`};
width: 20px;
`
const InputSliderProgress = styled.input`

  -webkit-appearance: none;
  width: 500px;
  height: 6px;
  background: linear-gradient(to right, #9c088e 0%, #9c088e ${({ value }) => value/2 }%, #bab6b6 ${({ value}) =>value/2}%,  #bab6b6 100%);
  outline: none;
  opacity: 0.7;
  -webkit-transition: .2s;
  transition: opacity .2s;
  border-radius: 10px;


&::-webkit-slider-thumb {
  /* Стилизация переключателя */
  -webkit-appearance: none;
  appearance: none;
  width: 15px;
  height: 15px;
  background: purple;
  cursor: pointer;
  border-radius: 50%;
}

&::-webkit-slider-thumb:hover {
  /* При наведение курсора на переключатель */
  background: #800080;
}

&::-moz-range-thumb {
  /* Стилизация переключателя для Firefox */
  width: 15px;
  height: 15px;
  background: purple;
  cursor: pointer;
  border-radius: 50%;
}
`
const InputSliderVolume =  styled.input`

-webkit-appearance: none;
width: 100%;
height: 6px;
background: linear-gradient(to right, #9c088e 0%, #9c088e ${({ value }) => value }%, #bab6b6 ${({ value}) =>value}%,  #bab6b6 100%);
outline: none;
opacity: 0.7;
-webkit-transition: .2s;
transition: opacity .2s;
border-radius: 10px;


&::-webkit-slider-thumb {
/* Стилизация переключателя */
-webkit-appearance: none;
appearance: none;
width: 15px;
height: 15px;
background: purple;
cursor: pointer;
border-radius: 50%;
}

&::-webkit-slider-thumb:hover {
/* При наведение курсора на переключатель */
background: #800080;
}

&::-moz-range-thumb {
/* Стилизация переключателя для Firefox */
width: 15px;
height: 15px;
background: purple;
cursor: pointer;
border-radius: 50%;
}
`



   interface PlayerDescription {
       audio: any,
       image:  string,
       title: string,
       time: number ,
       artist: string 
}
    interface PlayerProps {
      
       audio: any;
       title: string;
       switchingPlayaButton: ()=> void;
       time: number ;
       artist: string ;
       image:  string;
       handleVolume: ()=> void ; 
    
     } 

const sound:PlayerDescription = {
    
        audio: gala,
        image: imageMusic,
        title: 'Heat Waves',
        time: 210,
        artist: 'Glass Animals'
}
 const  Player1: React.FC<PlayerProps> = ()  => {
  const [play, setPlay] = useState(false)
  const soundRef: React.RefObject<HTMLAudioElement> = useRef<HTMLAudioElement>(null)
  const MAX = 100
  
      const [currentTime, setCurrentTime] = useState(0);
      const [duration, setDuration] = useState(0);
    
      useEffect(() => {
        if (soundRef.current) {
          soundRef.current.addEventListener('timeupdate', handleTimeUpdate);
          soundRef.current.addEventListener('loadedmetadata', handleLoadedMetadata);
          soundRef.current.addEventListener('ended', handleAudioEnded);
        }
    
        return () => {
          if (soundRef.current) {
            soundRef.current.removeEventListener('timeupdate', handleTimeUpdate);
            soundRef.current.removeEventListener('loadedmetadata', handleLoadedMetadata);
            soundRef.current.removeEventListener('ended', handleAudioEnded);
          }
        };
      }, []);
    
      const handleTimeUpdate = () => {
        if (soundRef.current) {
          setCurrentTime(soundRef.current.currentTime);
        }
      };
    
      const handleLoadedMetadata = () => {
        if (soundRef.current) {
          setDuration(soundRef.current.duration);
        }
      };
    
      const handleAudioEnded = () => {
        // Handle the audio ending, e.g., play the next track
      };
    
         const [progress, setProgress] = useState(0);
      const handleSeek = (event: React.ChangeEvent<HTMLInputElement>) => {
        const newTime = parseFloat(event.target.value);
        setCurrentTime(newTime);
        if (soundRef.current) {
          soundRef.current.currentTime = newTime;
        }
        const newProgress = parseInt(event.target.value);
           setProgress(newProgress);
      };
    
      const formatTime = (time: number) => {
        const minutes = Math.floor(time / 60);
        const seconds = Math.floor(time % 60);
        return `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
      };
    
      const [progress1, setProgress1] = useState(0);
     const  handleVolume = (e: React.ChangeEvent<HTMLInputElement>): void =>{
        const {value} =  e.target;
        const volume = Number(value) / MAX;
        if(soundRef.current){
          soundRef.current.volume = volume;
        }
        const newProgress = parseInt(e.target.value);
           setProgress1(newProgress);
     }

    const  switchingPlayaButton= () => {
        if (play) {
          soundRef.current?.pause()
          setPlay(false);
        } else {
          void soundRef.current?.play();
          setPlay(true);
          
        }
      };
      
      
       
    return (
        
       <PlayerComponent $invalid = {play}
       > 
        <Image>
            <img src={imageMusic} />
        </Image>
        <Title>
          <h3 >{sound.artist}</h3>
          <p >{sound.title}</p>
        </Title>
        
        <div>
            {play ?  (
            <Button onClick={switchingPlayaButton} >
                <img src={Pausa} width="40" height="40" />
                 </Button>
                 ) : (
                 <Button   onClick={switchingPlayaButton} >
                    <img src={Play} width="25" height="25" />
                </Button>
                )}
        </div>
        <FlexBox>
            <div>
                <Time >
                    <p>
                    {`${formatTime(currentTime)}`} 
                    </p>
                     <p>
                     {`${formatTime(duration)}`}
                    </p>
                    
                </Time>
             
             <InputSliderProgress
        type="range"
        min={0}
        max={duration}
        onChange={handleSeek}
        step={0.1}
        value={progress}
        
        
      />
    
        </div>
        </FlexBox>
        <FlexBox>
        <ButtonVolume 
        >
           <img src={Volume} width="30" height="30"/>
        </ButtonVolume>
           <InputSliderVolume
              type="range"
              min= "0"
              max={MAX}
              onChange={(e) => handleVolume(e)}
              value={progress1}
          /> 
      
       </FlexBox>
       <div>
       <audio ref={soundRef} loop src={sound.audio}  />
       </div>
 </PlayerComponent>
 )
 }

 export default Player1