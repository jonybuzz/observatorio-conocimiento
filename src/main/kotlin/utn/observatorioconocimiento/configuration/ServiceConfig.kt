package utn.observatorioconocimiento.configuration

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import java.util.*

@Configuration
class ServiceConfig {

    @Bean
    @Primary
    fun defaultObjectMapper(timeZone: TimeZone): ObjectMapper {
        return Jackson2ObjectMapperBuilder.json()
            .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .failOnEmptyBeans(false)
            .failOnUnknownProperties(false)
            .modules(JavaTimeModule(), KotlinModule.Builder().build())
            .featuresToDisable(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE
            )
            .timeZone(timeZone)
            .build()
    }

    @Bean
    fun timeZone(): TimeZone {
        return TimeZone.getDefault()
    }

}