import React from 'react'
import { Outlet } from 'react-router-dom'
import Player from '../components/pages/home/Player'

const MainLayout: React.FC = () => {
  return (
    <>
      <main>
        <Outlet />
      </main>
      <Player/>
    </>
  )
}

export default MainLayout
