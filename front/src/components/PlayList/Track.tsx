import React from "react";
import { styled }  from "styled-components"
import Pausa from "./assets/Pausa.svg"
import Play from "./assets/Play.svg"
import Playing from "./assets/Playing.svg"
import Player1 from "./Player1";



const Li = styled.li`
display: flex;   
background-color: #1a1918;
width: 95%;
font-weight: bold;
font-size: 1.1rem ;
color: ${({$invalid})=> $invalid ? `#9c088e` : `#bab6b6`}; 
max-width: 59.4rems;
padding: 0.8rem;
margin: 1rem auto;
justify-content: space-around; 
border:${({$invalid})=> $invalid ? `1px solid #212121` : `1px solid #212121`};
border-radius: 5px;
align-items: center ;
box-shadow:${({$invalid})=> $invalid ? `0 0 5px 2px #E283D2` : `none`}  ;
`

const Button = styled.button`
background-color: #e03e2500; 
    border: none; 
    &:hover{
      cursor: pointer;
    }
    
`

const Title = styled.div`
display: flex;
    justify-content: flex-start;
    width: 50%;
`
const Image = styled.div`
img{
border-radius: 55%;
      height: 3.8rem;
    box-shadow: 0 0 2px 2px #9c088e;
  }
`
const ButtonPlay = styled.div`
display: flex;
`
const ButtonPausa = styled.div`
padding-right: 2rem;
`
interface TrackProps {
   
    id: number;
    title: string;
    onPausa: ()=> void;
    time: number ;
    artist: string ;
    image:  string;
    onPlay: (id:number)=> void ; 
    activeItem: number | null ; 
    isPlaying: boolean ; 
   
}

 const Track: React.FC<TrackProps> = ( {
    onPausa, id,  time,title,artist, image, onPlay, activeItem, isPlaying}) => {

    return(
        
        <Li key={id} 
        $invalid = {activeItem === id}>
                  <div >
                        <h3>{id}</h3>
                    </div>
                    
                    <Image>
                        <img  src={image} />
                    </Image>
                    <Title >
                        <p>{title + " - " + artist}</p>
                    </Title>
                    <div >
                        {Math.floor(time / 60) + ':' + time % 60}
                    </div>
                    <div>
                        {activeItem === id && isPlaying ? (
                            
                            <ButtonPlay>
                                <ButtonPausa >
                                 <img src={Playing} />
                                </ButtonPausa>
                                <Button  onClick={()=>onPausa()} >
                             <img src={Pausa} width="22" height="22" />
                          </Button>
                           </ButtonPlay>
                           
                           
                        ) : (
                          <Button  onClick={()=>onPlay(id)} >
                             <img src={Play} width="22" height="22" />
                          </Button>
                        )
                         }
                    </div>
                </Li>
            
    )
 }
 export default Track