package unicrm.service;

import unicrm.domain.ResearchPaper;
import unicrm.domain.ResearcherDecorator;
import unicrm.domain.User;
import unicrm.repository.ResearchPaperRepository;
import unicrm.repository.UserRepository;

import java.util.*;

public class ResearchService {

    private final ResearchPaperRepository paperRepository;

    public ResearchService(ResearchPaperRepository paperRepository) {
        this.paperRepository = paperRepository;
    }

    public ResearcherDecorator becomeResearcher(User user, UserRepository userRepository) {
        user.setResearcher(true);
        userRepository.save(user);
        return new ResearcherDecorator(user);
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

    public String generateCitation(ResearchPaper paper) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return String.format("%s (%d). %s. University Research Journal.",
                paper.getAuthorUsername(), year, paper.getTitle());
    }

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
