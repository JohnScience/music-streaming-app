import { Outlet } from 'react-router-dom';
import Hero from '../components/pages/home/hero/Hero';

const Home = () => {
  return (
    <>
      <Hero />
      <Outlet />
    </>
  );
};

export default Home;
