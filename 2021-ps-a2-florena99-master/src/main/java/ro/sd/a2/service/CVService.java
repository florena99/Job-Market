package ro.sd.a2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.sd.a2.model.entity.CV;
import ro.sd.a2.model.entity.Education;
import ro.sd.a2.model.entity.Users;
import ro.sd.a2.service.repository.CVRepository;
import ro.sd.a2.service.repository.EduRepository;
import ro.sd.a2.service.repository.UserRepository;
@Service
public class CVService {

    @Autowired
    private CVRepository cvRepository;

    private EduRepository eduRepository;

    private EduService eduService;

    public CV save(CV cv) {
        cvRepository.save(cv);
        return cv;
    }

    public CV update(CV cv) {
        cvRepository.save(cv);
        return cv;
    }
    public CV findCV(Users users) {
        return cvRepository.findCVByUsers(users);

    }

    public void edit(CV newCv, Users user){
        CV cv= findCV(user);
        cv.setFirstName(newCv.getFirstName());
        cv.setLastName(newCv.getLastName());
        cv.setEmail(newCv.getEmail());
        cv.setAddress(newCv.getAddress());
        cv.setExperience(newCv.getExperience());
        cv.setBirthdate(newCv.getBirthdate());
        cvRepository.save(cv);
    }

    public void delete(Users user){
        cvRepository.deleteByUsers(user);

    }
}
