import React from 'react';
import { Outlet } from 'react-router-dom';
import Header from '../shared/header/Header';
import Footer from '../shared/footer/Footer';

const MainLayout: React.FC = () => {
  return (
    <>
      <Header />

      <main>
        <Outlet />
      </main>
      <Footer />
    </>
  );
};

export default MainLayout;
