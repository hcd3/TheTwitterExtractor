/*
 * Copyright (C) 2020 Norvax, Inc.
 * All Rights Reserved
 *
 * This is UNPUBLISHED PROPRIETARY SOURCE CODE of Norvax, Inc.; the contents
 * of this file may not be disclosed to third parties, copied or duplicated
 * in any form, in whole or in part, without the prior written permission of
 * Norvax, Inc. The copyright notice above does not evidence any actual or
 * intended publication of such source code.
 *
 * Permission is hereby granted solely to the licensee for use of this source
 * code in its unaltered state. This source code may not be modified by
 * licensee except under direction of Norvax, Inc. Neither may this source
 * code be given under any circumstances to non-licensees in any form,
 * including source or binary. Modification of this source constitutes breach
 * of contract, which voids any potential pending support responsibilities by
 * Norvax, Inc. Divulging the exact or paraphrased contents of this source
 * code to unlicensed parties either directly or indirectly constitutes
 * violation of federal and international copyright and trade secret laws, and
 * will be duly prosecuted to the fullest extent permitted under law.
 *
 * This software is provided by Norvax, Inc. ``as is'' and any express or
 * implied warranties, including, but not limited to, the implied warranties
 * of merchantability and fitness for a particular purpose are disclaimed. In
 * no event shall the regents or contributors be liable for any direct,
 * indirect, incidental, special, exemplary, or consequential damages
 * (including, but not limited to, procurement of substitute goods or
 * services; loss of use, data, or profits; or business interruption) however
 * caused and on any theory of liability, whether in contract, strict
 * liability, or tort (including negligence or otherwise) arising in any way
 * out of the use of this software, even if advised of the possibility of such
 * damage.
 */

package com.project.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.project.model.Tweet;

@Configuration
public class KafkaConfig {

    @Value("${kafka.broker.address}")
    private String brokerAddress;

    private ProducerFactory<String, Tweet> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerAddress);
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return props;
    }

    @Bean
    public KafkaTemplate<String, Tweet> kafkaTemplate() {
        return new KafkaTemplate<String, Tweet>(producerFactory());
    }

}
