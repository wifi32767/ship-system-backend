package com.wifi32767.app.Config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.BaseCodec;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.handler.State;
import org.redisson.client.protocol.Decoder;
import org.redisson.client.protocol.Encoder;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableConfigurationProperties(RedisClientConfigProperties.class)
public class RedisClientConfig {

    @Bean("redissonClient")
    public RedissonClient redissonClient(ConfigurableApplicationContext applicationContext, RedisClientConfigProperties properties) {
        Config config = new Config();

        // 编解码器
        config.setCodec(new FastJsonCodec());

        config.useSingleServer()
                .setAddress("redis://" + properties.getHost() + ":" + properties.getPort())
//                .setPassword(properties.getPassword())
                .setConnectionPoolSize(properties.getPoolSize())
                .setConnectionMinimumIdleSize(properties.getMinIdleSize())
                .setIdleConnectionTimeout(properties.getIdleTimeout())
                .setConnectTimeout(properties.getConnectTimeout())
                .setRetryAttempts(properties.getRetryAttempts())
                .setRetryInterval(properties.getRetryInterval())
                .setPingConnectionInterval(properties.getPingInterval())
                .setKeepAlive(properties.isKeepAlive())
        ;

        return Redisson.create(config);
    }

    public class FastJsonCodec extends BaseCodec {

        // 复用 StringCodec 处理 Map Key（通常是字符串，无需 JSON 序列化）
        private final Encoder mapKeyEncoder = StringCodec.INSTANCE.getValueEncoder();
        private final Decoder<Object> mapKeyDecoder = StringCodec.INSTANCE.getValueDecoder();

        // Value 序列化器（JSON + 类型信息）
        private final Encoder valueEncoder = new Encoder() {
            @Override
            public ByteBuf encode(Object in) throws IOException {
                ByteBuf out = ByteBufAllocator.DEFAULT.buffer();
                try (ByteBufOutputStream os = new ByteBufOutputStream(out)) {
                    // 写入 JSON 字符串，携带类型信息（WriteClassName）
                    String json = JSON.toJSONString(in, SerializerFeature.WriteClassName);
                    os.write(json.getBytes(StandardCharsets.UTF_8));
                    return os.buffer();
                } catch (Exception e) {
                    out.release();
                    throw new IOException(e);
                }
            }
        };

        private final Decoder<Object> valueDecoder = new Decoder<Object>() {
            @Override
            public Object decode(ByteBuf buf, State state) throws IOException {
                // 读取 JSON 并自动识别类型（SupportAutoType）
                try (ByteBufInputStream is = new ByteBufInputStream(buf)) {
                    return JSON.parseObject(is, Object.class, Feature.SupportAutoType);
                }
            }
        };

        @Override
        public Decoder<Object> getValueDecoder() {
            return valueDecoder;
        }

        @Override
        public Encoder getValueEncoder() {
            return valueEncoder;
        }
    }

}
