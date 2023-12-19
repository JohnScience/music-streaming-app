import { useEffect, useState, useRef } from "react";
import useSound from "use-sound";
import gala from "./assets/Lesa FS - Между нами.mp3";
import { styled }  from "styled-components"
import "../PlayList/Player.css"
import React from "react";
import Pausa from "./assets/Pausa 2.svg"
import Volume from "./assets/Group (1).svg"
import Play from "./assets/Play.svg"
import imageMusic from "./assets/images.jpg"



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

const Li = styled.li`
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
const ElementsVolume = styled.div`
display: flex;
`
const ButtonVolume = styled.div`
padding-right: 2rem;
color: ${({$invalid})=> $invalid ? `#9c088e` : `#bab6b6`};
width: 20px;
`

const sound = {
    
        audio:{gala},
        image: {imageMusic},
        title: 'Heat Waves',
        artist:
          'Glass Animals',
        time: 210
      
}
export default function Player1() {
    
    //  const [time, setTime] = useState({
    //      min: "",
    //      sec: ""
    //    });
    //    const [currTime, setCurrTime] = useState({
    //      min: "",
    //      sec: ""
    //    });
       
       const [currentSong, setCurrentSong] = useState(sound.audio)
      
     const [play, setPlay] = useState(false)
     const soundRef = useRef<HTMLAudioElement>(null)
     const MAX = 20

     function handleVolume(e: React.ChangeEvent<HTMLInputElement>): void{
        const {value} =  e.target;
        const volume = Number(value) / MAX;
        soundRef.current!.volume = volume;
     }

     const handleInterval= () =>{
       const duration = soundRef.current?.duration;
       const ct =  soundRef.current?.currentTime
       
       setCurrentSong({ ...currentSong, "progress": ct / duration * 100, "lenfth": duration})
     }

    function playingButton(): void {
        if (play) {
          soundRef.current?.pause()
          setPlay(false);
        } else {
          void soundRef.current?.play();
          setPlay(true);
        }
      };
    
    
    return (
        
       <Li $invalid = {play}
       > 
        <Image>
            <img src={imageMusic} />
        </Image>
        <Title>
          <h2 >{sound.artist}</h2>
          <p >{sound.title}</p>
        </Title>
        
        <div>
            {play ?  (
            <Button onClick={()=>playingButton()} >
                <img src={Pausa} width="40" height="40" />
                 </Button>
                 ) : (
                 <Button   onClick={()=>playingButton()} >
                    <img src={Play} width="25" height="25" />
                </Button>
                )}
        </div>
        <ElementsVolume>
            <div>
                <Time >
                    <p>
                         0
                    </p>
                     <p>
                        0
                    </p>
                </Time>
                
        <input
          type="range"
          min="0"
          onChange={()=>handleInterval()}
          
          />
        </div>
        </ElementsVolume>
        <ElementsVolume>
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
       </ElementsVolume>
       <div>
       <audio ref={soundRef} loop src={gala} onTimeUpdate= {handleInterval}/>
       </div>
 </Li>
 )
 }