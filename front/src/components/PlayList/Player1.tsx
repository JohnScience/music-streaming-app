import { useEffect, useState, useRef } from "react";
import gala from "./assets/Melanie Martinez - Cake.mp3";
import { styled }  from "styled-components"
import "../PlayList/Player.css"
import React from "react";
import Pausa from "./assets/Pausa 2.svg"
import Volume from "./assets/Group (1).svg"
import Play from "./assets/Play.svg"
import imageMusic from "./assets/images.jpg"
import Progress from "./Progress";



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

const PlayerComponent = styled.div`
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
border-radius: 55%;
      height: 5rem;
    box-shadow: 0 0 2px 2px #9c088e;
  }
`
const Title = styled.div`
width: 10%;
    align-items: start ;
   
`
const FlexBlock = styled.div`
display: flex;
`
const ButtonVolume = styled.div`
padding-right: 2rem;
color: ${({$invalid})=> $invalid ? `#9c088e` : `#bab6b6`};
width: 20px;
`

// const sound = {
    
//         audio:{gala},
//         image: {imageMusic},
//         title: 'Heat Waves',
//         artist:
//           'Glass Animals',
//         time: 210
      
// }
export default function Player1() {
    
  const [isPlaying, setIsPlaying] = useState(false);
  const [time, setTime] = useState({
    min: "",
    sec: ""
  });
  const [currTime, setCurrTime] = useState({
    min: "",
    sec: ""
  });


      
     const [state, setPlay] = useState({
        playing: false,
        volume:0,
        loadedSeconds: 1,
        playedSeconds: 0
     })
     const soundRef = useRef<HTMLAudioElement>(null)
    
     const MAX = 20
     const {
        playing,
        volume,
        loadedSeconds,
        playedSeconds
     } = state

     function handleVolume(e: React.ChangeEvent<HTMLInputElement>): void{
        const {value} =  e.target;
        const volume = Number(value) / MAX;
        soundRef.current!.volume = volume;
     }
    

    //  const handleInterval= () =>{
    //    const duration:number = soundRef.current?.duration;
    //    const ct:number =  soundRef.current?.currentTime
    //    console.log(duration, ct)
    //    setCurrentSong({ ...currentSong, "progress": ct / duration * 100, "length": duration})
    //  }
    const onSetVideoTimestamp = (event: React.ChangeEvent<HTMLAudioElement>):void => {
      const timestamp = event.currentTarget.currentTime
      onSetVideoTimestamp(Math.floor(timestamp))
    }
    const  playingButton = () => {
        if (state) {
          soundRef.current?.play()
          setPlay(false);
        } else {
          void soundRef.current?.pause();
          setPlay(true);
        }
      };
    const handleProgress = (e) =>{
      setPlay({...state, ...e})
    }
    //  const handleProgress = (soundRef)=>{
    //   const duration:number = soundRef.current?.duration;
    //   const ct:number =  soundRef.current?.currentTime
    //   const currentProgress = ct / duration * 100 || 0; 
        
    //  }
    return (
        
       <PlayerComponent $invalid = {!state}
       > 
        <Image>
            <img src={imageMusic} />
        </Image>
        <Title>
          <h3 >{sound.artist}</h3>
          <p >{sound.title}</p>
        </Title>
        
        <div>
            {state ?  (
            <Button   onClick={()=>playingButton()} >
                    <img src={Play} width="25" height="25" />
                </Button>
                 ) : (
                    <Button onClick={()=>playingButton()} >
                    <img src={Pausa} width="40" height="40" />
                     </Button>
                )}
        </div>
        <FlexBlock>
            <div>
                <Time >
                <p>
            {currTime.min}:{currTime.sec}
          </p>
          <p>
            {time.min}:{time.sec}
          </p>
                </Time>
                <input
              type="range"
              min={0}
              max={loadedSeconds}
              onChange={(e) => handleProgress(e)}
          />
        </div>
        </FlexBlock>
        <FlexBlock>
        <ButtonVolume >
           <img src={Volume} width="30" height="20"/>
        </ButtonVolume>
        <div>
           <input
              type="range"
              min={0}
              max={MAX}
              onChange={(e) => handleVolume(e)}
          />
       </div>
       </FlexBlock>
       <div>
       <audio ref={soundRef}  loop src={gala} onChange={(e) => handleProgress(e)}   onTimeUpdate={onSetVideoTimestamp}/>
       </div>
 </PlayerComponent>
 )
 }