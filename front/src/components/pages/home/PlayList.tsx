import { useState } from "react";
import Pausa from "../home/image_PlayList/Pausa.svg"
import Play from "../home/image_PlayList/Play.svg"
import Playing from "../home/image_PlayList/Playing.svg"
import Image1 from "../home/image_PlayList/1.jpg"
import Image2 from  "../home/image_PlayList/2.jpg"
import Image3 from  "../home/image_PlayList/3.jpg"
import "./PlayList.css"
import React from "react";


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
                <li className=
                {activeItem=== index ? "active" : "component"}
                >
                  <div className="component_id">
                        <h3>{item.id}</h3>
                    </div>
                    <div>
                        <img className="component_image" src={item.image} />
                    </div>
                    <div className="component_title">
                        <p>{item.title + " - " + item.artist}</p>
                    </div>
                    <div className="component_time">
                        {Math.floor(item.time / 60) + ':' + item.time % 60}
                    </div>
                    <div className="component_button">
                        {activeItem === index && isPlaying ? (
                            <div className="play">
                                <div className="play1">
                                 <img src={Playing} />
                                </div>
                                <button className="round" onClick={()=> handlePausa()} >
                             <img src={Pausa} width="22" height="22" />
                          </button>
                           </div>
                        ) : (
                          <button className="round" onClick={()=> handlePlay(index)} >
                             <img src={Play} width="22" height="22" />
                          </button>
                        )
                         }
                    </div>
                </li>
                </ul>
            ))
        )
    }