# Программные зависимости


# Git
git нам понадобиться для работы в данном репозитории (как ни странно!). Абсолютно без разницы через какую прослойку вы работаете с git: через графический интерфейс или командную строку. Я лишь накидаю ссылки, где можно скачать нормальные клиенты:

* [git for windows](https://gitforwindows.org/)
* [Github Desktop](https://desktop.github.com/)
* [Sourcetree](https://ru.atlassian.com/software/sourcetree)
* [Установка git](https://git-scm.com/book/ru/v1/%D0%92%D0%B2%D0%B5%D0%B4%D0%B5%D0%BD%D0%B8%D0%B5-%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0-Git)

В операционных системах семействах linux и mac os x обычно уже стоят git клиенты. Проверить можно командой ```git --version```, она что-то читабельное с номером версии должна вывести.

# Docker
В проекте мы используем docker контейнеры. Падение производительности минимальное, развертывание среды везде будет одинаковым (будь мы на продакшен сервере, на windows/linux/mac os x).

Нам достаточно community version: docker и docker-compose.

* [docker for mac os x](https://docs.docker.com/docker-for-mac/)
* [docker for windows](https://docs.docker.com/docker-for-windows/)
* [docker for ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/)
* [docker-compose](https://docs.docker.com/compose/install/)

Если вы используете Windows, то у вас должна быть версия не ниже Pro, в какой-нибудь домашней версии виртуализации отключена. Вместо виртуализации вы можете использовать нативные клиенты mysql и тд, но этим всем вы будете сами заниматься :)


# Java

Java скачать проще всего. Нужна минимальная версия Java 8:
* [Скачать java с официального сайта](https://java.com/ru/download/)