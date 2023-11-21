# Музыкальный сервис

В разработке. Подробности в [Telegram канале](https://t.me/projectmusicapp).

* `front` - фронтенд, пишущийся на React + JavaScript и собираемый бандлером Vite.
* `back` - бэкенд, пишущийся на Spring Boot + Java.

Так как разработка находится на раннем этапе, выбор технологий может меняться.

## Разработка

Для разработки вам необходимо форкнуть репозиторий и клонировать *ваш форк*. На этом этапе разработки мы следуем GitHub Flow с feature-/refactor- ветками в ваших форках и pull request'ами в основной репозиторий.

Для коммитов мы используем стиль [Conventional Commits](https://www.conventionalcommits.org/ru/v1.0.0/).

* **Q:** [Как сделать форк](https://vk.com/@257913611-kak-forknut-repozitorii-i-sdelat-pul-rekvest)?
* **Q:** [Как клонировать репозиторий-форк](https://docs.github.com/ru/repositories/creating-and-managing-repositories/cloning-a-repository)?
* **Q:** [Как сделать ветку](https://githowto.com/ru/creating_a_branch)?
* **Q:** Как назвать ветку? Если вы делаете новую фичу, то `feature/название-фичи`, если рефакторинг, то `refactor/название-рефакторинга`.
* **Q:** [Как сделать коммит](https://struchkov.dev/blog/ru/git-how-to-commit/)?
* **Q:** Как назвать коммит? Следуйте [Conventional Commits](https://www.conventionalcommits.org/ru/v1.0.0/) стилю.
* **Q:** [Как сделать pull request](https://rustycrate.ru/%D1%80%D1%83%D0%BA%D0%BE%D0%B2%D0%BE%D0%B4%D1%81%D1%82%D0%B2%D0%B0/2016/03/07/contributing.html)?

---

## Проверка

### Установка (для проверки)

Для клонирования репозитория напишите

```console
git clone --depth 1 https://github.com/JohnScience/music-streaming-app
```

В директории, в которую вы хотите установить директорию с проектом с помощью [git](https://git-scm.com/downloads).

### Запуск (для проверки)

1. Убедитесь, что у вас установлен Docker на вашей машине. Если нет, вы можете скачать его с [официального сайта Docker](https://www.docker.com/products/docker-desktop).

2. Перейдите в директорию `back` проекта

```console
cd music-streaming-app/back
```

3. Чтобы очистить и собрать JAR файл вашего проекта с помощью Maven выполните команду

```console
mvn clean package
```

4. Для запуска docker контейнера введите комманду 

```console
docker-compose up 
```
