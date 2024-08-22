# Java Developer's Journey: Integrating OpenAI and Anthropic APIs with LangChain4j

As a Java developer, I've often felt left out when it comes to working with Large Language Models (LLMs). Many LLM providers offer SDKs primarily for Python or JavaScript, leaving Java developers at a disadvantage. That's why I was excited to discover LangChain4j, a library that bridges this gap by providing a unified Java interface for interacting with multiple LLMs, including OpenAI and Anthropic.

In this article, I'll share my experience using LangChain4j to send API requests to both OpenAI and Anthropic, demonstrating some key features of the library that I found particularly useful.

## How I Used LangChain4j with OpenAI and Anthropic

For those interested in exploring the full implementation or trying it out themselves, I've made the complete code and Maven configuration with all required dependencies available in my GitHub repository https://github.com/georgyturevich/langchain4j-demo

Now, let's look at an example of how I used LangChain4j to work with both OpenAI and Anthropic:

```java
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
```

Before running this code, I made sure to set my API keys as environment variables:
```
export OPENAI_API_KEY="your_openai_api_key_here"
export ANTHROPIC_API_KEY="your_anthropic_api_key_here"
```

To obtain these API keys, you can create them at the following locations:

- Anthropic: https://console.anthropic.com/settings/keys
- OpenAI: https://platform.openai.com/api-keys

Remember to keep these keys secure and never share them publicly.

## Key Features I Found Useful

1. **Unified Interface**: I loved how LangChain4j provides a common `ChatLanguageModel` interface. This made it incredibly easy to switch between different LLM providers.

2. **Prompt Templates**: The ability to create reusable templates with variables was a game-changer for me. It made my code much more flexible and easier to maintain.

3. **Consistent Configuration**: I appreciated how I could use the same settings across different providers. This consistency simplified my development process significantly.

## My Takeaways

While this example only scratches the surface, I've found that LangChain4j offers a wide range of features beyond what I've demonstrated here. The library supports:

- Integration with 15+ other LLM providers
- Streaming of responses from LLMs
- Working with embedding stores and models
- And dozens other features

If you're a Java developer looking to work with LLMs, I highly recommend checking out LangChain4j. For a more comprehensive overview of LangChain4j's capabilities and detailed documentation, visit https://docs.langchain4j.dev.
