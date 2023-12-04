import { Link, Outlet } from 'react-router-dom'

import React from 'react'

const AboutPage = () => {
  return (
    <>
      <>About Page</>
      <Link to='/'>Link to Home page</Link>
      <Outlet />
    </>
  )
}

export default AboutPage
