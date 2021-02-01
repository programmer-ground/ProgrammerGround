TARGET=PGChatServer
DOCKER_TAG=pg/chat-server
SRCS=main.go client.go hub.go
BUILD_OPT=-race
ifndef $(GOPATH)
    GOPATH=$(shell go env GOPATH)
    export GOPATH
endif

.PHONY: clean check build build-linux docker

build: check
	@mkdir -p build
	go build ${BUILD_OPT} -o build/${TARGET} ${SRCS}

build-linux: check
	@mkdir -p build
	CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -o build/${TARGET}-linux-amd64 ${SRCS}

test:
	go test ./...

clean:
	rm -fr build/${TARGET}*

docker:
	docker build --rm --tag ${DOCKER_TAG} .
	docker image prune -f --filter label=stage=builder
