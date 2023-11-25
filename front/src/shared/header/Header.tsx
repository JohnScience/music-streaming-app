import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

const Header: React.FC = () => {
  return (
    <MainHeader>
      <div className="container">
        <Navigation>
          <WrapperLogo>
            <Link to="/">
              <Logo src="https://freesvg.org/img/Pink-Bunny-Icon.png" alt="" />
            </Link>
          </WrapperLogo>
          <List>
            <Item>
              <Link to="/" className="link">
                Главная
              </Link>
            </Item>
            <Item>
              <Link to="/" className="link">
                не главная
              </Link>
            </Item>
            <Item>
              <Link to="/" className="link">
                и это
              </Link>
            </Item>
            <Item>
              <Link to="/" className="link">
                а эта?(нет)
              </Link>
            </Item>
          </List>
          <SearchPanel>будущее место для поисковика</SearchPanel>

          <ButtonsWrapper>
            <Link to="/">Промокод</Link>
            <LoginLink to="" className="link_promo">
              Войти
            </LoginLink>
          </ButtonsWrapper>
        </Navigation>
      </div>
    </MainHeader>
  );
};

export default Header;
const MainHeader = styled.header``;
const WrapperLogo = styled.div``;
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
const SearchPanel = styled.div``;
const Item = styled.li``;
const ButtonsWrapper = styled.div``;
const LoginLink = styled(Link)`
  margin-left: 20px;
`;
