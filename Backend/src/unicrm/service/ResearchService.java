package unicrm.service;

import unicrm.domain.ResearchPaper;
import unicrm.domain.ResearcherDecorator;
import unicrm.domain.UniversityJournal;
import unicrm.domain.User;
import unicrm.repository.JournalRepository;
import unicrm.repository.ResearchPaperRepository;
import unicrm.repository.UserRepository;

import java.util.*;

public class ResearchService {

    private final ResearchPaperRepository paperRepository;
    private final JournalRepository journalRepository;

    public ResearchService(ResearchPaperRepository paperRepository, JournalRepository journalRepository) {
        this.paperRepository = paperRepository;
        this.journalRepository = journalRepository;
    }

    // ── Journals ─────────────────────────────────────────────────────────────

    public UniversityJournal createJournal(String name) {
        UniversityJournal journal = new UniversityJournal(name);
        journalRepository.save(journal);
        return journal;
    }

    public List<UniversityJournal> getAllJournals() {
        return journalRepository.findAll();
    }

    public boolean journalExists(String name) {
        return journalRepository.existsByName(name);
    }

    // ── Papers ────────────────────────────────────────────────────────────────

    public ResearcherDecorator becomeResearcher(User user, UserRepository userRepository) {
        user.setResearcher(true);
        userRepository.save(user);
        return new ResearcherDecorator(user);
    }

    public ResearchPaper publishPaper(String authorUsername, String title, String paperAbstract,
                                      String journalName, List<String> citedPaperIds) {
        ResearchPaper paper = new ResearchPaper(title, paperAbstract, authorUsername);
        paper.setJournalName(journalName);
        paper.setCitedPaperIds(citedPaperIds != null ? citedPaperIds : new ArrayList<>());

        // Increment citation count for each cited paper
        for (String citedId : paper.getCitedPaperIds()) {
            ResearchPaper cited = paperRepository.findById(citedId);
            if (cited != null) {
                cited.setCitations(cited.getCitations() + 1);
                paperRepository.save(cited);
            }
        }

        paperRepository.save(paper);
        return paper;
    }

    public List<ResearchPaper> getAllPapers() {
        return paperRepository.findAll();
    }

    public List<ResearchPaper> getPapersForUser(String username) {
        return paperRepository.findByAuthor(username);
    }

    public String generateCitation(ResearchPaper paper) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String journal = (paper.getJournalName() != null && !paper.getJournalName().isBlank())
                ? paper.getJournalName()
                : "University Research Journal";
        return String.format("%s (%d). %s. %s.", paper.getAuthorUsername(), year, paper.getTitle(), journal);
    }

    public int calculateHIndex(String username) {
        List<ResearchPaper> papers = paperRepository.findByAuthor(username);
        List<Integer> sorted = papers.stream()
                .map(ResearchPaper::getCitations)
                .sorted(Comparator.reverseOrder())
                .toList();

        int h = 0;
        for (int i = 0; i < sorted.size(); i++) {
            if (sorted.get(i) >= i + 1) h = i + 1;
            else break;
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
