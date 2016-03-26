package com.aeg.ims.transfer.config;

import com.aeg.ims.transfer.batch.JobCompletionNotificationListener;
import com.aeg.ims.transfer.batch.PersonItemProcessor;
import com.aeg.ims.transfer.model.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Person> reader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
        reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "firstName", "lastName" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    public FlatFileItemWriter<Person> writer() {
        FlatFileItemWriter<Person> writer = new FlatFileItemWriter<Person>();
        writer.setResource(new FileSystemResource("src/main/resources/output.csv"));
        writer.setLineAggregator(new LineAggregator<Person>() {
            @Override
            public String aggregate(Person item) {
                return String.format("%s, %s", item.getFirstName(), item.getLastName());
            }
        });
        return writer;
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionNotificationListener();
    }

    @Bean
    public Job dataTransferJob() {
        return jobBuilderFactory.get("dataTransferJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(readInbound())
                .next(writeOutbound())
                .end()
                .build();
    }

    @Bean
    public Step readInbound() {
        return stepBuilderFactory.get("readInbound")
                .<Person, Person> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Step writeOutbound() {
        return stepBuilderFactory.get("writeOutbound")
                .<Person, Person> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

}
