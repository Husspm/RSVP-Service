package com.company.rsvp;

import com.company.rsvp.model.Rsvp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RsvpDaoTest {

    @Autowired
    RsvpDao rsvpDao;

    Rsvp inputRsvp = new Rsvp();
    Rsvp outputRsvp = new Rsvp();
    List<Rsvp> rsvpList = new ArrayList<>();


    @Before
    public void setUp() throws Exception {
        rsvpDao.deleteAll();

        inputRsvp.setGuestName("Patrick");
        inputRsvp.setPhoneNumber("1112223333");
        inputRsvp.setTotalAttending(5);
    }

    @Test
    public void createRsvp(){
        Rsvp createdRsvp = rsvpDao.save(inputRsvp);

        inputRsvp.setRsvpId(createdRsvp.getRsvpId());

        assertEquals(inputRsvp,createdRsvp);
    }

    @Test
    public void getRsvpById(){
        Rsvp createdRsvp = rsvpDao.save(inputRsvp);
        inputRsvp.setRsvpId(createdRsvp.getRsvpId());

        Rsvp fromDb = rsvpDao.getOne(inputRsvp.getRsvpId());

        assertEquals(createdRsvp,fromDb);

    }

    @Test
    public void getAllRsvps(){
        Rsvp createdRsvp = rsvpDao.save(inputRsvp);
        inputRsvp.setRsvpId(createdRsvp.getRsvpId());
        rsvpList.add(inputRsvp);

        List<Rsvp> listFromDB = rsvpDao.findAll();

        assertTrue(listFromDB.containsAll(rsvpList) && rsvpList.containsAll(listFromDB));

    }

    @Test
    public void updateRsvp(){
        Rsvp createdRsvp = rsvpDao.save(inputRsvp);
        inputRsvp.setRsvpId(createdRsvp.getRsvpId());

        inputRsvp.setPhoneNumber("3334445555");

        Rsvp createdRsvp2 = rsvpDao.save(inputRsvp);

        assertEquals(inputRsvp,createdRsvp2);

    }

    @Test
    public void deleteRsvp(){
        Rsvp createdRsvp = rsvpDao.save(inputRsvp);
        inputRsvp.setRsvpId(createdRsvp.getRsvpId());

        rsvpDao.deleteById(createdRsvp.getRsvpId());

        List<Rsvp> listFromDB = rsvpDao.findAll();

        assertEquals(0, rsvpDao.findAll().size());

    }



}