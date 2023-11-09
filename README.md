# Музыкальный сервис

В процессе инициации разработки.

* `front` - фронтенд, написанный на React + JavaScript и собираемый бандлером Vite.
* `back` - бэкенд, написанный на Express.js + JavaScript.

Так как разработка находится на раннем этапе, выбор технологий может меняться.

## Установка

0. Если вы плохо представляете как пользоваться терминалом, посмотрите видео вроде [этого](https://www.youtube.com/watch?v=LtZpdRJvU1A).
1. Удостоверьтесь, что у вас установлен [`git`](https://git-scm.com/). Если вы только что его установили, то перезапустите терминал (например, `cmd` или `powershell`), если он у вас запущен. Чтобы удостовериться, что `git` установлен, выполните команду `git -v`. Если вы видите версию, то всё в порядке.
2. Склонируйте репозиторий. Для этого, директории, в которой вы хотите разместить директорию с проектом, в терминале выполните команду `git clone --depth 1 https://github.com/JohnScience/music-streaming-app`. Для того, чтобы сменить директорию (change directory) на нужную, используйте `cd <путь>`.
3. Удостоверьтесь, что у вас установлен [`node.js`](https://nodejs.org/en/download/). Если вы только что его установили, то перезапустите терминал, если они у вас запущен. Чтобы удостовериться, что `node` установлен, выполните команду `node -v`. Если вы видите версию, то всё в порядке.
4. Удостоверьтесь, что `npm` у вас [добавлен в переменную среды](https://remontka.pro/add-to-path-variable-windows/) `Path`. Для этого выполните команду `npm -v`. Если вы видите версию, то всё в порядке. Если вы её не видите, вам придётся добавить `npm` в переменную среды `Path` вручную. Если у вас работает `node`, но не `npm`, то попробуйте добавить директорию с `node` (смотрите `where node`) в переменную среды `Path`. После этого вам придётся перезапустить терминал и вернуться в вашу директорию, где вы клонировали репозиторий.
5. Исполните команду `cd music-streaming-app/front && npm i && cd ../back && npm i && cd ..`. В случае ошибки `Лексема "&&" не является допустимым разделителем операторов в этой версии`, пишите команды разделённые `&&` поочерёдно, то есть

```console
cd music-streaming-app/front
npm i
cd ../back
npm i
cd ..
```

После этого, вы можете следовать инструкциям в `front/README.md` и `back/README.md`.

## Создание Docker-образа

1. Удостоверьтесь, что у вас установлен [`docker`](https://docs.docker.com/get-docker/).

2. В директории с проектом запустите следующую команду:

```console
docker build -f Dockerfile.webapp -t music-streaming-webapp .
```

Это создаст Docker-образ с именем `music-streaming-webapp`.

## Запуск Docker-образа (в detached режиме)

1. Удостоверьтесь, что вы создали Docker-образ `music-streaming-webapp` (см. предыдущий раздел).

2. В директории с проектом запустите следующую команду:

```console
docker run --name music-streaming-webapp -p 8000:3000 -d music-streaming-webapp
```

Это запустит Docker-образ `music-streaming-webapp` и привяжет порт 3000 контейнера к порту 8000 хоста. Чтобы остановить контейнер, используйте команду:

```console
docker kill music-streaming-webapp
```

## Запуск Docker-образа (в foreground режиме)

1. Удостоверьтесь, что вы создали Docker-образ `music-streaming-webapp`.

2. В директории с проектом запустите следующую команду:

```console
docker run --name music-streaming-webapp -p 8000:3000 -t -i --rm music-streaming-webapp
```

После остановки с помощью `Ctrl+C`, благодаря флагу `--rm`, контейнер будет удалён.
