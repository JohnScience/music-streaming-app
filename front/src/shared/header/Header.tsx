import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const Navigation = styled.nav`
  display: flex;
  align-items: center;
  column-gap: 50px;
  justify-content: space-between;
`;

const Logo = styled.img`
  max-width: 100px;
`;

const List = styled.ul`
  display: flex;
  column-gap: 30px;
  align-items: center;
`;

const LoginLink = styled(Link)`
  margin-left: 20px;
`;

const Header: React.FC = () => {
  return (
    <header>
      <div className="container">
        <Navigation>
          <div>
            <Link to="/">
              <Logo src="https://freesvg.org/img/Pink-Bunny-Icon.png" alt="" />
            </Link>
          </div>
          <List>
            <li>
              <Link to="/" className="link">
                Главная
              </Link>
            </li>
            <li>
              <Link to="/" className="link">
                не главная
              </Link>
            </li>
            <li>
              <Link to="/" className="link">
                и это
              </Link>
            </li>
            <li>
              <Link to="/" className="link">
                а эта?(нет)
              </Link>
            </li>
          </List>
          <div>Search</div>

          <div>
            <Link to="/">Промокод</Link>
            <LoginLink to="" className="link_promo">
              Войти
            </LoginLink>
          </div>
        </Navigation>
      </div>
    </header>
  );
};

export default Header;
