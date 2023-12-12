import React from 'react'
import img from './MainMenu.png'
import styled from 'styled-components'

const MainMenu: React.FC = () => {
  return (
    <MainMenuStyles>
      <img src={img} alt='' />
    </MainMenuStyles>
  )
}

export default MainMenu

const MainMenuStyles = styled.div``
