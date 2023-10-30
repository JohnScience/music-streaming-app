# Музыкальный сервис

* `front` - фронтенд, написанный на React + JavaScript и собираемый бандлером Vite.
* `back` - бэкенд, написанный на Express.js + JavaScript.

## Установка

1. Удостоверьтесь, что у вас установлен [`git`](https://git-scm.com/).
2. В директории, в которой вы хотите разместить директорию с проектом, выполните команду `git clone --depth 1 https://github.com/JohnScience/music-streaming-app`.
3. Удостоверьтесь, что у вас установлен [`node.js`](https://nodejs.org/en/download/). Вместе с ним должен быть установлен `npm`.
4. Исполните команду `cd music-streaming-app/front && npm i && cd ../back && npm i && cd ..`.

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
