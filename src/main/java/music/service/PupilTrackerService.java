
package music.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import music.model.Pupil;
import music.model.Instrument;

@Component
public class PupilTrackerService {

    private final RestClient restClient;

    public PupilTrackerService( ) {
        this.restClient = RestClient.builder()
                .baseUrl("https://pmaier.eu.pythonanywhere.com")
                .build();
    }

    public Pupil getPupil(int matric) {

        Map response = restClient.get()
                .uri("/plt/pupil/{matric}", matric)
                .retrieve()
                .body(Map.class);

        if (response == null || response.get("pupil") == null) {
            return null;
        }

        Map pupilData = (Map) response.get("pupil");

        Pupil pupil = new Pupil();

        pupil.setName((String) pupilData.get("name"));
        pupil.setStudentid((Integer) pupilData.get("matric"));
        pupil.setAge((Integer) pupilData.get("age"));

        Map trackerInstruments = (Map) pupilData.get("instruments");

        List<Instrument> instruments = new ArrayList<>();

        for (Object key : trackerInstruments.keySet()) {

            Map instrumentData = (Map) trackerInstruments.get(key);

            Instrument instrument = new Instrument();

            instrument.setInstrumentName((String) key);
            instrument.setStudentGrade((Integer) instrumentData.get("grade"));

            instruments.add(instrument);
        }

        pupil.setInstrument(instruments);

        return pupil;
    }
}
