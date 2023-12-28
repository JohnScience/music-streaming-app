import { useEffect, useState } from "react";
import Image1 from "./assets/1.jpg"
import Image2 from  "./assets/2.jpg"
import Image3 from  "./assets/3.jpg"
import React from "react";
import gala from "./assets/Melanie Martinez - Cake.mp3";
import sound from "./assets/Lesa FS - Между нами.mp3"
import Track from "./Track";
import {styled} from "styled-components";
import axios from "axios"
import Player1 from "./Player";
 
const src = "https://reqres.in/api/users?page=1"
interface TrackDescription {
  audio: any;
  id: number;
  image:  string; 
  title: string;
  time: number ;
  artist: string ;
  
}
const  musicList: TrackDescription[] = [
    {
      audio: gala,
      id: 1,
      image: Image1,
      title: "Easy on Me",
      artist:
        'Адель',
        time: 250
    },
    {
      audio: sound,
      id:2,
      image: Image2,
      title: 'Heat Waves',
      artist:
        'Glass Animals',
      time: 210
    },
    {
      audio: gala,
      id:3,
      image: Image3,
      title: 'Break My',
      artist:
        'Бейонсе',
      time: 260
    },
    {
      audio: sound,
      id: 4,
      image: Image1,
      title: "Easy on Me",
      artist:
        'Адель',
        time: 250
    },
    {
      audio: gala,
      id:5,
      image: Image2,
      title: 'Heat Waves',
      artist:
        'Glass Animals',
      time: 210
    },
    {
      audio: sound,
      id:6,
      image: Image3,
      title: 'Break My',
      artist:
        'Бейонсе',
      time: 260
    },
    {
      audio: gala,
      id:7,
      image: Image3,
      title: 'Break My',
      artist:
        'Бейонсе',
      time: 260
    },
    
  ]

  const CustomScrollbar = styled.div`
   overflow: auto;
  overflow-x:hidden;
  
  &::-webkit-scrollbar {
    width: 12px; 
  }

  &::-webkit-scrollbar-thumb {
    background-color: #302F2F; 
  }

  &::-webkit-scrollbar-track {
width: 100%;
    background-color: #1a1918; 
  }
  padding-right: 0px
  `
  const Ul = styled.ul`
  margin: 0;
  padding: 0;
 width: 99%;
 height: 41.6rem;
 
  `

  
   const PlayList = () => {
    const [articles, setArticles] = useState([])
    useEffect(() => {
      axios
         .get(src)
         .then(data =>{
            console.log(data.data.data)
         })
    }, [])


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
          <>
          <CustomScrollbar>
            <Ul>
            {musicList.map((item)=>(
              <Track 
              key={item.title} 
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
            </Ul> 
           
          </CustomScrollbar>
          </>
        )
    }

    export default PlayList