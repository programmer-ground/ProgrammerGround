# bulid stage
FROM golang:1.15 AS build
LABEL stage=builder

WORKDIR /src
COPY . .
RUN make build-linux

# final stage
FROM scratch

WORKDIR /
COPY --from=build /src/build/PGChatServer-linux-amd64 /PGChatServer

EXPOSE 8080

CMD ["/PGChatServer", "--stdout"]
