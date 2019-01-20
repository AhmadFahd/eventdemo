package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.Services.RatingServices;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.dto.RatingDTO;
import com.ahmadfahd.entity.FeedEntity;
import com.ahmadfahd.entity.RatingEntity;
import com.ahmadfahd.entity.TicketsEntity;
import com.ahmadfahd.enums.ACTIONS;
import com.ahmadfahd.repository.*;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServicesImp implements RatingServices {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private TicketsRepository ticketsRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private EventsRepository eventsRepository;

    @Override
    public List<RatingDTO> getAllRatings() {

        List<RatingEntity> ratingEntityList = ratingRepository.findAll();
        List<RatingDTO> ratingDTOList = ObjectMapperUtils.mapAll(ratingEntityList, RatingDTO.class);
        return ratingDTOList;
    }

    @Override
    public RatingDTO findById(Long rateid) {
        RatingEntity ratingEntity = ratingRepository.findById(rateid).get();
        RatingDTO ratingDTO = modelMapper.map(ratingEntity, RatingDTO.class);
        return ratingDTO;
    }

    @Override
    public void addRating(int rate, Long uId, Long eId) {
//        FIXME: 12/25/2018 needs Test
        long rated= ticketsRepository.countByUserIdAndEventIdAndChickedTrue(uId,eId);
        if(rated == 1) {
            RatingEntity ratingEntity = new RatingEntity();
            ratingEntity.setUser(usersRepository.findById(uId).get());
            ratingEntity.setEvent(eventsRepository.findById(eId).get());
            ratingEntity.setRate(rate);
            FeedEntity feedEntity = new FeedEntity();
            feedEntity.setUser(ratingEntity.getUser());
            feedEntity.setTime(LocalDateTime.now());
            feedEntity.setAction(ACTIONS.RATE.toString());
            ratingRepository.save(ratingEntity);
            feedEntity.setTargetRate(ratingEntity);
            feedRepository.save(feedEntity);
        }
    }

    @Override
    public double findRateAvg(Long uid) {
            List<RatingEntity> ratingEntities = ratingRepository.findByEventOrganizerId(uid);
        long rates = ratingEntities.size();
        if (rates > 0) {
            double avg;
            double sum = 0;
            for (RatingEntity ratingEntity : ratingEntities) {
                sum = sum + ratingEntity.getRate();
            }
            avg = sum / rates;
            return avg;
        }
        return 0;
    }
}
