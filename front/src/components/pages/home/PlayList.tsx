import { useState } from "react";
import Pausa from "../home/assets/Pausa.svg"
import Play from "../home/assets/Play.svg"
import Playing from "../home/assets/Playing.svg"
import Image1 from "../home/assets/1.jpg"
import Image2 from  "../home/assets/2.jpg"
import Image3 from  "../home/assets/3.jpg"
import "./PlayList.css"
import React from "react";
import { styled }  from "styled-components"

const Li = styled.li`
display: flex;   
background-color: #252020;
width: 100%;
color: ${({$invalid})=> $invalid ? `#9c088e` : `#bab6b6`}; 
max-width: 900px;
margin: 1rem auto;
justify-content: space-around; 
border:${({$invalid})=> $invalid ? `1px solid #212121` : `1px solid #212121`};
border-radius: 5px;
align-items: center ;
box-shadow:${({$invalid})=> $invalid ? `0 0 5px 2px #E283D2` : `none`}  ;
$img {
  border-radius: 50%;
      height: 3rem;
    box-shadow: 0 0 2px 2px #9c088e;
}
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
    width: 40%;
`
const Image = styled.img`
border-radius: 50%;
      height: 3rem;
    box-shadow: 0 0 2px 2px #9c088e;
`
const ButtonPlay = styled.div`
display: flex;
`
const ButtonPausa = styled.div`
padding-right: 2rem;
`
const  musicList = [
    {
      id: 1,
      image: Image1,
      title: "Easy on Me",
      artist:
        'Адель',
        time: 250
    },
    {
      id:2,
      image: Image2,
      title: 'Heat Waves',
      artist:
        'Glass Animals',
      time: 210
    },
    {
      id:3,
      image: Image3,
      title: 'Break My',
      artist:
        'Бейонсе',
      time: 260
    },]

    export default function PlayList () {
        const [isPlaying, setIsPlaying] = useState(false)
        const[activeItem, setActiveItem] = useState(null)
        const handlePlay=(index) =>{
            setActiveItem(index),
            setIsPlaying(true)
        };
        const handlePausa = () => {
            if(isPlaying) {
                setIsPlaying(false)
            } else {
                setIsPlaying(true)
            }
        }
          

        return(
            musicList.map((item, index)=>(
              <ul >
                <Li $invalid = {activeItem === index}
                >
                  <div >
                        <h3>{item.id}</h3>
                    </div>
                    
                        <img className="component_image" src={item.image} />
                    
                    <Title >
                        <p>{item.title + " - " + item.artist}</p>
                    </Title>
                    <div >
                        {Math.floor(item.time / 60) + ':' + item.time % 60}
                    </div>
                    <div>
                        {activeItem === index && isPlaying ? (
                            <ButtonPlay>
                                <ButtonPausa >
                                 <img src={Playing} />
                                </ButtonPausa>
                                <Button  onClick={()=> handlePausa()} >
                             <img src={Pausa} width="22" height="22" />
                          </Button>
                           </ButtonPlay>
                        ) : (
                          <Button  onClick={()=> handlePlay(index)} >
                             <img src={Play} width="22" height="22" />
                          </Button>
                        )
                         }
                    </div>
                </Li>
                </ul>
            ))
        )
    }