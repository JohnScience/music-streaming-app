import { Outlet } from 'react-router-dom';
import Hero from '../components/pages/home/hero/Hero';
import React from 'react';

const Home = () => {
  return (
    <>
      <Hero />
      <Outlet />
    </>
  );
};

export default Home;
