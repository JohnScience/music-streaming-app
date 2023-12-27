import React from 'react'
import { Outlet } from 'react-router-dom'

import MainMenu from '../components/shared/MainMenu'
import RightMenu from '../components/shared/RightMenu'
import styled from 'styled-components'
import PlayerModule from '../components/Player/Player'

const MainLayout: React.FC = () => {
  return (
    <ContentContainer>
      <Wrapper>
        <MainMenu />
        <MainContent>
          <Outlet />
        </MainContent>
        <RightMenu />
      </Wrapper>
      <PlayerModule />
    </ContentContainer>
  )
}

export default MainLayout

const MainContent = styled.main`
  padding-top: 20px;
`

const ContentContainer = styled.div`
  max-width: 1440px;
  margin: auto;
`
const Wrapper = styled.div`
  display: flex;
  column-gap: 20px;
  justify-content: center;
`
