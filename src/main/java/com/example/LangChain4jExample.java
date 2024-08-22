package com.example;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.anthropic.AnthropicChatModel;

import java.util.Map;

public class LangChain4jExample {
    private static final String OPENAI_MODEL_NAME = "gpt-4o";
    private static final String ANTHROPIC_MODEL_NAME = "claude-3-5-sonnet-20240620";
    private static final double MODEL_TEMPERATURE = 0.7;
    private static final int MODEL_MAX_TOKENS = 300;

    public static void main(String[] args) {
        // Initialize OpenAI model
        OpenAiChatModel openAiChatModel = OpenAiChatModel.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .modelName(OPENAI_MODEL_NAME)
                .temperature(MODEL_TEMPERATURE)
                .maxTokens(MODEL_MAX_TOKENS)
                .build();

        // Initialize Anthropic model
        AnthropicChatModel anthropicChatModel = AnthropicChatModel.builder()
                .apiKey(System.getenv("ANTHROPIC_API_KEY"))
                .modelName(ANTHROPIC_MODEL_NAME)
                .temperature(MODEL_TEMPERATURE)
                .maxTokens(MODEL_MAX_TOKENS)
                .build();

        // Create a prompt template
        PromptTemplate template = PromptTemplate.from(
    "What is the capital of {{country}}? What places should I visit here?.");

        // Use the template with OpenAI and Anthropic
        String prompt = template.apply(Map.of("country", "France")).toString();
        printModelResponse(openAiChatModel, prompt, "OpenAI");
        printModelResponse(anthropicChatModel, prompt, "Anthropic");
    }

    private static void printModelResponse(ChatLanguageModel model, String prompt, String modelName) {
        String modelResponse = model.generate(prompt);
        String responseMessage = modelName + " Response: " + modelResponse;
        System.out.println(responseMessage);
    }
}