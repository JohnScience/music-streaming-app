import styles from './Footer.module.scss';
import { Link } from 'react-router-dom';
const Footer = () => {
  return (
    <footer className={styles.footer}>
      <div className="container">
        <ul className={styles.list}>
          <li className={styles.item}>
            <Link>Правообладателям</Link>
          </li>
          <li className={styles.item}>
            <Link>Пользовательское соглашение</Link>
          </li>
          <li className={styles.item}>
            <Link>Правила рекомендаций</Link>
          </li>
          <li className={styles.item}>
            <Link>Справка</Link>
          </li>
        </ul>
      </div>
    </footer>
  );
};

export default Footer;
