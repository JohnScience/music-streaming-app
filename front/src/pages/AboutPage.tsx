import { Link, Outlet } from 'react-router-dom'

import React from 'react'
import Information from '../components/pages/about/Information'

const AboutPage = () => {
  return (
    <div>
      <Link to='/'>Link to Home page</Link>
      <Information />
      <Outlet />
    </div>
  )
}

export default AboutPage
