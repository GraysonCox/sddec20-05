FROM balenalib/raspberry-pi-debian:buster

RUN cd ~
RUN apt-get update -y
RUN apt-get upgrade -y
RUN apt-get install -y libmicrohttpd-dev libjansson-dev libssl-dev libsofia-sip-ua-dev libglib2.0-dev libopus-dev libogg-dev libcurl4-openssl-dev liblua5.3-dev libconfig-dev pkg-config gengetopt libtool automake git python3-picamera libnice-dev ffmpeg wget make

RUN wget https://github.com/cisco/libsrtp/archive/v2.3.0.tar.gz \
	 && tar xf v2.3.0.tar.gz \
	 && cd libsrtp-2.3.0 \
	 && ./configure --prefix=/usr --enable-openssl \
	 && make shared_library \
	 && make install \
	 && cd .. 

RUN git clone https://github.com/meetecho/janus-gateway.git \
	&& cd janus-gateway \
	&& sh autogen.sh \
	&& ./configure --disable-websockets --disable-data-channels --disable-rabbitmq --disable-docs --prefix=/opt/janus \
	&& make \
	&& make install \
	&& make configs

COPY janus.plugin.streaming.jcfg /opt/janus/etc/janus/
COPY commands.sh /

RUN usermod -a -G video root
CMD modprobe bcm2835-v4l2

CMD ["/commands.sh"]

