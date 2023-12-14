import { useState } from "react";
import Image1 from "../home/assets/1.jpg"
import Image2 from  "../home/assets/2.jpg"
import Image3 from  "../home/assets/3.jpg"
import React from "react";
import { styled }  from "styled-components"
import Treck from "./Treck";
 


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
          <ul>
            {musicList.map((item)=>(
              <Treck 
              id={item.id}
              artist={item.artist}
              time={item.time}
              title={item.title}
              image={item.image}
              onPlay={handlePlay}
              onPausa={handlePausa}
              activeItem={activeItem}
              isPlaying={isPlaying}
              />
             
            ))}
          </ul>
        )
    }