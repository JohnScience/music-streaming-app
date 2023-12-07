import React from 'react'
import { Outlet } from 'react-router-dom'

import MainMenu from '../components/pages/home/MainMenu'
import Player from '../components/pages/home/Player'
import styled from 'styled-components'
import RightMenu from '../components/pages/home/RightMenu'

const MainLayout: React.FC = () => {
  return (
    <>
      <MainMenu />
      <MainContent>
        <Outlet />
      </MainContent>
      <RightMenu />
      <Player />
    </>
  )
}

export default MainLayout

const MainContent = styled.main`
  main {
    padding-top: 20px;
  }
`
