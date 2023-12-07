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
      <main>
        <Outlet />
      </main>
      <RightMenu />

      <Player />
    </>
  )
}

export default MainLayout
