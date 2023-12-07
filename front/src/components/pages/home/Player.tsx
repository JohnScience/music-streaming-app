import React from 'react'
import img from './Player.png'
import styled from 'styled-components'
const Player: React.FC = () => {
  return (
    <MainPlayer>
      <img src={img} alt='' />
    </MainPlayer>
  )
}

export default Player

const MainPlayer = styled.div``
