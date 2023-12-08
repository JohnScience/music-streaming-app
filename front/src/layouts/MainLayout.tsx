import React from 'react'
import { Outlet } from 'react-router-dom'

import MainMenu from '../components/pages/home/MainMenu'
import Player from '../components/pages/home/Player'
import styled from 'styled-components'
import RightMenu from '../components/pages/home/RightMenu'

const MainLayout: React.FC = () => {
  return (
    <ContentWrapper>
      <MainMenu />
      <MainContent>
        <Outlet />
      </MainContent>
      <RightMenu />
      <Player />
    </ContentWrapper>
  )
}

export default MainLayout

const MainContent = styled.main`
  padding-top: 20px;
`

const ContentWrapper = styled.div`
  margin: auto;
  max-width: 1440px;
  display: grid;
  grid-template-columns: 275px 1fr 348px;
  grid-template-rows: 1fr auto;
  column-gap: 30px;
  justify-content: center;
`
