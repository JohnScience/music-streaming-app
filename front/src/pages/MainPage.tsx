import { Outlet } from 'react-router-dom'

import React from 'react'
import { Link } from 'react-router-dom'
import SearchPanel from '../components/pages/home/SearchPanel'
import GenreList from '../components/pages/home/GenreList'
import PlayList from '../components/pages/home/PlayList'
import LeftMenu from '../components/pages/home/LeftMenu'
import RightMenu from '../components/pages/home/RightMenu'

// import Player from '../components/pages/home/Player'
const Home = () => {
  return (
    <>
      <LeftMenu />
      <div>
        <SearchPanel />
        <GenreList />
        <PlayList />
      </div>
      <RightMenu />

      <Outlet />
    </>
  )
}

export default Home
