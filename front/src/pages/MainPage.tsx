import { Outlet } from 'react-router-dom'

import React from 'react'
import { Link } from 'react-router-dom'
import SearchPanel from '../components/pages/home/SearchPanel'
import GenreList from '../components/pages/home/GenreList'
import PlayList from '../components/pages/home/PlayList'
import styled from 'styled-components'


const Home = () => {
  return (
    <>
    <Container>
      <SearchPanel />
      <GenreList />
      <PlayList />
      </Container>
    
    </>
  )
}

export default Home

const Container = styled.div`
  max-width: 770px;
  margin: auto;
`
