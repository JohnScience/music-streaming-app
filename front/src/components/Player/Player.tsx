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
const InputSliderProgress = styled.div`
input[type=range] {
  height: 10px;
  -webkit-appearance: none;
  margin: 10px 0;
  width: 400px;
  background-color: #1a1918;
}
input[type=range]:focus {
  outline: none;
}
input[type=range]::-webkit-slider-runnable-track {
  width: 100%;
  height: 10px;
  cursor: pointer;
  animate: 0.2s;
  box-shadow: 0px 0px 0px #504b4b;
  background: #b6b6b6;
  border-radius: 12px;
  border: 0px solid #797875;
}
input[type=range]::-webkit-slider-thumb {
  box-shadow: 2px 2px 1px #353535;
  border: 1px solid #6c6962;
  height: 15px;
  width: 15px;
  border-radius: 27px;
  background: #f200da;
  cursor: pointer;
  -webkit-appearance: none;
  margin-top: -3px;
}
input[type=range]:focus::-webkit-slider-runnable-track {
  background: #b6b6b6;
}
input[type=range]::-moz-range-track {
  width: 100%;
  height: 10px;
  cursor: pointer;
  animate: 0.2s;
  box-shadow: 0px 0px 0px #504b4b;
  background: #b6b6b6;
  border-radius: 12px;
  border: 0px solid #797875;
}
input[type=range]::-moz-range-thumb {
  box-shadow: 2px 2px 1px #353535;
  border: 1px solid #6c6962;
  height: 15px;
  width: 15px;
  border-radius: 27px;
  background: #f200da;
  cursor: pointer;
}
input[type=range]::-ms-track {
  width: 100%;
  height: 10px;
  cursor: pointer;
  animate: 0.2s;
  background: transparent;
  border-color: transparent;
  color: transparent;
}
input[type=range]::-ms-fill-lower {
  background: #b6b6b6;
  border: 0px solid #797875;
  border-radius: 27px;
  box-shadow: 0px 0px 0px #504b4b;
}
input[type=range]::-ms-fill-upper {
  background: #b6b6b6;
  border: 0px solid #797875;
  border-radius: 27px;
  box-shadow: 0px 0px 0px #504b4b;
}
input[type=range]::-ms-thumb {
  margin-top: 1px;
  box-shadow: 2px 2px 1px #353535;
  border: 1px solid #6c6962;
  height: 15px;
  width: 15px;
  border-radius: 27px;
  background: #f200da;
  cursor: pointer;
}
input[type=range]:focus::-ms-fill-lower {
  background: #b6b6b6;
}
input[type=range]:focus::-ms-fill-upper {
  background: #b6b6b6;
}
input[type="range"]::-moz-range-progress {
  background-color: #212121; 
}
`
const InputSliderVolume = styled.div`
input[type=range] {
  height: 10px;
  -webkit-appearance: none;
  margin: 10px 0;
  width: 100%;
  background-color: #1a1918;
}
input[type=range]:focus {
  outline: none;
}
input[type=range]::-webkit-slider-runnable-track {
  width: 100%;
  height: 10px;
  cursor: pointer;
  animate: 0.2s;
  box-shadow: 0px 0px 0px #504b4b;
  background: #b6b6b6;
  border-radius: 12px;
  border: 0px solid #797875;
}
input[type=range]::-webkit-slider-thumb {
  box-shadow: 2px 2px 1px #353535;
  border: 1px solid #6c6962;
  height: 15px;
  width: 15px;
  border-radius: 27px;
  background: #f200da;
  cursor: pointer;
  -webkit-appearance: none;
  margin-top: -3px;
}
input[type=range]:focus::-webkit-slider-runnable-track {
  background: #b6b6b6;
}
input[type=range]::-moz-range-track {
  width: 100%;
  height: 10px;
  cursor: pointer;
  animate: 0.2s;
  box-shadow: 0px 0px 0px #504b4b;
  background: #b6b6b6;
  border-radius: 12px;
  border: 0px solid #797875;
}
input[type=range]::-moz-range-thumb {
  box-shadow: 2px 2px 1px #353535;
  border: 1px solid #6c6962;
  height: 15px;
  width: 15px;
  border-radius: 27px;
  background: #f200da;
  cursor: pointer;
}
input[type=range]::-ms-track {
  width: 100%;
  height: 10px;
  cursor: pointer;
  animate: 0.2s;
  background: transparent;
  border-color: transparent;
  color: transparent;
}
input[type=range]::-ms-fill-lower {
  background: #b6b6b6;
  border: 0px solid #797875;
  border-radius: 27px;
  box-shadow: 0px 0px 0px #504b4b;
}
input[type=range]::-ms-fill-upper {
  background: #b6b6b6;
  border: 0px solid #797875;
  border-radius: 27px;
  box-shadow: 0px 0px 0px #504b4b;
}
input[type=range]::-ms-thumb {
  margin-top: 1px;
  box-shadow: 2px 2px 1px #353535;
  border: 1px solid #6c6962;
  height: 15px;
  width: 15px;
  border-radius: 27px;
  background: #f200da;
  cursor: pointer;
}
input[type=range]:focus::-ms-fill-lower {
  background: #b6b6b6;
}
input[type=range]:focus::-ms-fill-upper {
  background: #b6b6b6;
}
input[type="range"]::-moz-range-progress {
  background-color: #43e5f7;
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
  const MAX = 20
  
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
    
      const handleSeek = (event: React.ChangeEvent<HTMLInputElement>) => {
        const newTime = parseFloat(event.target.value);
        setCurrentTime(newTime);
        if (soundRef.current) {
          soundRef.current.currentTime = newTime;
        }
      };
    
      const formatTime = (time: number) => {
        const minutes = Math.floor(time / 60);
        const seconds = Math.floor(time % 60);
        return `${String(minutes).padStart(2, '0')}:${String(seconds).padStart(2, '0')}`;
      };
    
        
     const  handleVolume = (e: React.ChangeEvent<HTMLInputElement>): void =>{
        const {value} =  e.target;
        const volume = Number(value) / MAX;
        if(soundRef.current){
          soundRef.current.volume = volume;
        }
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
             <InputSliderProgress>  
             <input
        type="range"
        min={0}
        max={duration}
        value={currentTime}
        onChange={handleSeek}
        step={0.1}
      />
      
          </InputSliderProgress> 
        </div>
        </FlexBox>
        <FlexBox>
        <ButtonVolume 
        >
           <img src={Volume} width="30" height="30"/>
        </ButtonVolume>
        <InputSliderVolume>
           <input
              type="range"
              min={0}
              max={MAX}
              onChange={(e) => handleVolume(e)}
          />
       </InputSliderVolume>
       </FlexBox>
       <div>
       <audio ref={soundRef} loop src={sound.audio}  />
       </div>
 </PlayerComponent>
 )
 }

 export default Player1