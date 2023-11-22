import { Link } from 'react-router-dom';
import styles from './Header.module.scss';

const Header = () => {
  return (
    <header className={styles.header}>
      <div className='container'>
        <nav className={styles.navigation}>
          <div>
            <Link to="/">
              <img
                src="https://freesvg.org/img/Pink-Bunny-Icon.png"
                alt=""
                className={styles.logo}
              />
            </Link>
          </div>
          <ul className={styles.list}>
            <li>
              <Link to="/" className={styles.link}>
                Главная
              </Link>
            </li>
            <li>
              <Link to="/" className={styles.link}>
                не главная
              </Link>
            </li>
            <li>
              <Link to="/" className={styles.link}>
                и это
              </Link>
            </li>
            <li>
              <Link to="/" className={styles.link}>
                а эта?(нет)
              </Link>
            </li>
          </ul>
          <div>Search</div>

          <div>
            <Link to="">Промокод</Link>
            <Link to="" className={styles.link_promo}>
              Войти
            </Link>
          </div>
        </nav>
      </div>
    </header>
  );
};

export default Header;
