import React from 'react'
import img from './RightMenu.png'
import styled from 'styled-components'

const RightMenu: React.FC = () => {
  return (
    <MainMenuRight>
      <img src={img} alt='' />
    </MainMenuRight>
  )
}

export default RightMenu

const MainMenuRight = styled.div`
  max-width: 348px;
`
