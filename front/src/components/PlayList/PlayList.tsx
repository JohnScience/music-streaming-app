import { useState } from "react";
import Image1 from "./assets/1.jpg"
import Image2 from  "./assets/2.jpg"
import Image3 from  "./assets/3.jpg"
import React from "react";
import Track from "./Track";
 

interface MmusicList {
  id: number;
  image:  string; 
  title: string;
  time: number ;
  artist: string ;
  
}
const  musicList: MmusicList[] = [
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

   const PlayList = () => {
        const [isPlaying, setIsPlaying] = useState(false)
        const[activeItem, setActiveItem] = useState(null)
        const handlePlay=(index: number | null) =>{
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
              <Track 
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

    export default PlayList