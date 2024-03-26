package com.theokanning.openai.moderation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;

/**
 * An object containing the moderation data for a single input string
 *
 * https://beta.openai.com/docs/api-reference/moderations/create
 */
@Data
public class Moderation {
    /**
     * Content that expresses, incites, or promotes hate based on race, gender, ethnicity,
     * religion, nationality, sexual orientation, disability status, or caste.
     * Hateful content aimed at non-protected groups (e.g., chess players) is harassment.
     */
    public static final String CATEGORY_HATE = "hate";

    /**
     * Hateful content that also includes violence or serious harm towards the targeted group
     * based on race, gender, ethnicity, religion, nationality, sexual orientation, disability status, or caste.
     */
    public static final String CATEGORY_HATE_THREATENING = "hate/threatening";

    /**
     * Content that expresses, incites, or promotes harassing language towards any target.
     */
    public static final String CATEGORY_HARASSMENT = "harassment";

    /**
     * Harassment content that also includes violence or serious harm towards any target.
     */
    public static final String CATEGORY_HARASSMENT_THREATENING = "harassment/threatening";

    /**
     * Content that promotes, encourages, or depicts acts of self-harm, such as suicide, cutting, and eating disorders.
     */
    public static final String CATEGORY_SELFHARM = "self-harm";

    /**
     * Content where the speaker expresses that they are engaging or intend to engage
     * in acts of self-harm, such as suicide, cutting, and eating disorders.
     */
    public static final String CATEGORY_SELFHARM_INTENT = "self-harm/intent";

    /**
     * Content that encourages performing acts of self-harm, such as suicide, cutting,
     * and eating disorders, or that gives instructions or advice on how to commit such acts.
     */
    public static final String CATEGORY_SELFHARM_INSTRUCTIONS = "self-harm/instructions";

    /**
     * Content meant to arouse sexual excitement, such as the description of sexual activity,
     * or that promotes sexual services (excluding sex education and wellness).
     */
    public static final String CATEGORY_SEXUAL = "sexual";

    /**
     * Sexual content that includes an individual who is under 18 years old.
     */
    public static final String CATEGORY_SEXUAL_MINORS = "sexual/minors";

    /**
     * Content that depicts death, violence, or physical injury.
     */
    public static final String CATEGORY_VIOLENCE = "violence";

    /**
     * Content that depicts death, violence, or physical injury in graphic detail.
     */
    public static final String CATEGORY_VIOLENCE_GRAPHIC = "violence/graphic";

    /**
     * Set to true if the model classifies the content as violating OpenAI's content policy, false otherwise
     */
    public boolean flagged;

    /**
     * Map containing per-category binary content policy violation flags.
     * For each category, the value is true if the model flags the corresponding category as violated, false otherwise.
     */
    public HashMap<String, Boolean> categories = new HashMap<>();

    /**
     * Map containing per-category raw scores output by the model, denoting the model's confidence that the
     * input violates the OpenAI's policy for the category.
     * The value is between 0 and 1, where higher values denote higher confidence.
     * The scores should not be interpreted as probabilities.
     */
    @JsonProperty("category_scores")
    public HashMap<String, Double> categoryScores = new HashMap<>();

    public boolean category(String category) {
        return categories.getOrDefault(category, false);
    }

    public double score(String category) {
        return categoryScores.getOrDefault(category, 0d);
    }
}
