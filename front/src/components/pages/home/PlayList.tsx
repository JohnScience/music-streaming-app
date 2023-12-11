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
        time: 3.36
    },
    {
      id:2,
      image: Image2,
      title: 'Heat Waves',
      artist:
        'Glass Animals',
      time: 4.36
    },
    {
      id:3,
      image: Image3,
      title: 'Break My',
      artist:
        'Бейонсе',
      time: 4.56
    },]

    export default function PlayList () {
        const [isPlaying, setIsPlaying] = useState(false)
        const[activeItem, setActiveItem] = useState(null)
        const handleClick=(index) =>{
            setActiveItem(index),
            setIsPlaying(true)
        };
        const handlePausa = (index) => {
            if(isPlaying) {
                setIsPlaying(false)
            } else {
                setIsPlaying(true)
            }
        }


        return(
            musicList.map((item, index)=>(
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
                        <p className="title">{item.title}</p>
                        <p>-</p>
                        <p className="subTitle">{item.artist}</p>
                    </div>
                    <div className="component_time">
                        {item.time}
                    </div>
                    <div className="component_button">
                        {activeItem === index && isPlaying ? (
                            <div className="play">
                                <div className="play1">
                                <input type="image" src={Playing} ></input>
                                </div>
                                <input type="image" src={Pausa} onClick={()=> handlePausa(index)}></input>
                           </div>
                        ) : (
                            
                            <input 
                            type="image" src={Play} onClick={()=> handleClick(index)}></input>
                            
                        )
                         }
                    </div>
                </li>
            ))
        )
    }