FROM debian:9.4

RUN apt-get update \
    && apt-get install -y --no-install-recomends \
        bzip2 \
        unzip \
        xz-utils \
    && rm -rf /var/lib/apt/lists/*

ENV LANG C.UTF-8

