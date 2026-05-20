package unicrm.service;

import unicrm.domain.ResearchPaper;
import unicrm.repository.ResearchPaperRepository;

import java.util.*;

public class ResearchService {

    private final ResearchPaperRepository paperRepository;

    public ResearchService(ResearchPaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    public ResearchPaper publishPaper(String authorUsername, String title, String paperAbstract) {
        ResearchPaper paper = new ResearchPaper(title, paperAbstract, authorUsername);
        paperRepository.save(paper);
        return paper;
    }

    public List<ResearchPaper> getPapersForUser(String username) {
        return paperRepository.findByAuthor(username);
    }

    public ResearchPaper findPaperByTitle(String title) {
        return paperRepository.findByTitle(title);
    }

    /** APA-style citation: Author (year). Title. University Research Journal. */
    public String generateCitation(ResearchPaper paper) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return String.format("%s (%d). %s. University Research Journal.",
                paper.getAuthorUsername(), year, paper.getTitle());
    }

    /**
     * Calculates H-Index for a given author.
     * H-Index = max h such that the author has >= h papers with >= h citations.
     */
    public int calculateHIndex(String username) {
        List<ResearchPaper> papers = paperRepository.findByAuthor(username);
        List<Integer> sorted = papers.stream()
                .map(ResearchPaper::getCitations)
                .sorted(Comparator.reverseOrder())
                .toList();

        int h = 0;
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i) >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }
        return h;
    }

    /**
     * Returns top N researchers sorted by H-Index descending.
     * Returns a list of username → hIndex entries.
     */
    public List<Map.Entry<String, Integer>> getTopResearchers(int limit) {
        Map<String, Integer> hIndexMap = new LinkedHashMap<>();

        paperRepository.findAll().stream()
                .map(ResearchPaper::getAuthorUsername)
                .distinct()
                .forEach(username -> hIndexMap.put(username, calculateHIndex(username)));

        return hIndexMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(limit)
                .toList();
    }
}
