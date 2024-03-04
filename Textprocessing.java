import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Textprocessing {
    public static void main(String[] args) {

        System.out.println("Enter the path of the text file:");
        try (BufferedReader br = new BufferedReader(new FileReader(getUserInput()))) {
            StringBuilder rawText = new StringBuilder();
            String line;
            //read the file 
            while ((line = br.readLine()) != null) {
                rawText.append(line).append("\n");
            }
            //processing steps
            String cleanedText = cleanText(rawText.toString());
            String normalizedText = normalizeText(cleanedText);
            String[] tokens = tokenizeText(normalizedText);
            String withoutStopWords = removeStopWords(tokens);
            String lemmatizedText = lemmatizeText(withoutStopWords);
            
            System.out.println("Cleaned Text:\n" + cleanedText);
            System.out.println("\nNormalized Text:\n" + normalizedText);
            System.out.println("\nTokenized Text:\n" + String.join(", ", tokens));
            System.out.println("\nText without Stop Words:\n" + withoutStopWords);
            System.out.println("\nLemmatized Text:\n" + lemmatizedText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String getUserInput() throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        return reader.readLine();
    }
    private static String cleanText(String rawText) {
        //Remove non letters
        return rawText.replaceAll("[^a-zA-Z\\s]", "");
    }
    private static String normalizeText(String cleanedText) {
        //letters to lowercase
        return cleanedText.toLowerCase();
    }
    private static String[] tokenizeText(String normalizedText) {
        //tokenize text
        StringTokenizer tokenizer = new StringTokenizer(normalizedText);
        int tokenCount = tokenizer.countTokens();
        String[] tokens = new String[tokenCount];

        for (int i = 0; i < tokenCount; i++) {
            tokens[i] = tokenizer.nextToken();
        }
        return tokens;
    }
    private static String removeStopWords(String[] tokens) {
        Set<String> stopWords = new HashSet<>();
        stopWords.add("a");
        stopWords.add("an");
        stopWords.add("and");
        stopWords.add("are");
        stopWords.add("as");
        stopWords.add("at");
        stopWords.add("be");
        stopWords.add("but");
        stopWords.add("by");
        stopWords.add("for");
        stopWords.add("if");
        stopWords.add("in");
        stopWords.add("into");
        stopWords.add("is");
        stopWords.add("it");
        stopWords.add("no");
        stopWords.add("not");
        stopWords.add("of");
        stopWords.add("on");
        stopWords.add("or");
        stopWords.add("such");
        stopWords.add("that");
        stopWords.add("the");
        stopWords.add("their");
        stopWords.add("then");
        stopWords.add("there");
        stopWords.add("these");
        stopWords.add("they");
        stopWords.add("this");
        stopWords.add("to");
        stopWords.add("was");
        stopWords.add("will");
        stopWords.add("with");
        StringBuilder result = new StringBuilder();
        for (String token : tokens) {
            if (!stopWords.contains(token)) {
                result.append(token).append(" ");
            }
        }
        return result.toString().trim();
    }
    private static String lemmatizeText(String text) {
        return text.replaceAll("s\\b", "");
    }
}
