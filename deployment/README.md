# Сервер для деплоя [`music-streaming-app`]

Это [`actix-web`] серверное приложение для деплоя [`music-streaming-app`] на сервер, на котором запущено это приложение.

По умолчанию, оно слушает порт `8089` и обрабатывает запросы по следующим конечным точкам:

* GET `/deploy` - получает последнюю версию кода из основной ветки репозитория с помощью `git` и перезапускает многоконтейнерное приложение с помощью `docker compose`.
* GET `/deploy_status` - возвращает статус обновления `(None|Ongoing|Pending|PendingAwaited)`.
* GET `/df`- показывает результат вызова утилиты [`df`] для мониторинга использования дискового пространства.

P.S. В дальнейшем планируется добавить ограничение, по которому сервер будет обновляться только при передаче секретного ключа.

## Сборка

```console
cross build --target x86_64-unknown-linux-gnu --release
```

См. [`cross`] крейт.

## Доставка на сервер

```console
scp target/x86_64-unknown-linux-gnu/release/music-streaming-app-deployment root@92.38.176.86:~/music-streaming-app-deployment-tmp
ssh root@92.38.176.86 "kill $(lsof -i :8089 | awk '{print $2}') && mv ~/music-streaming-app-deployment-tmp ~/music-streaming-app-deployment && chmod +x ~/music-streaming-app-deployment && ~/music-streaming-app-deployment & disown"
```

См. [`scp`](https://en.wikipedia.org/wiki/Secure_copy) и [`ssh`](https://www.ssh.com/academy/ssh/client).

P.S. Чтобы не вводить пароль при каждом вызове `ssh`, можно добавить свой публичный ключ на сервер с помощью `ssh-copy-id`:

```console
ssh-copy-id root@92.38.176.86
```

[`actix-web`]: https://crates.io/crates/actix-web
[`music-streaming-app`]: https://github.com/JohnScience/music-streaming-app
[`cross`]: https://github.com/cross-rs/cross
[`df`]: https://blog.sedicomm.com/2020/06/16/12-poleznyh-komand-df-dlya-proverki-diskovogo-prostranstva-v-linux/
