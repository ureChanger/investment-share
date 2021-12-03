package com.kola.kola_beta.main.data.dummy;

import com.kola.kola_beta.main.data.publication.PublicationData;

import java.util.ArrayList;
import java.util.Arrays;

public class DummyPublic {
    private ArrayList<PublicationData> dummyPublic;
    public DummyPublic() {
        this.dummyPublic = new ArrayList<>(
                Arrays.asList(
                        new PublicationData(
                                0,
                                "스택스 한국 공식 커뮤니티",
                                new ArrayList<>(
                                        Arrays.asList(0)
                                ),
                                "스택스 커뮤니티와 함께 만들어가는 공간입니다"
                                ),
                        new PublicationData(
                                0,
                                "스택스 한국 공식 커뮤니티",
                                new ArrayList<>(
                                        Arrays.asList(0)
                                ),
                                "스택스 커뮤니티와 함께 만들어가는 공간입니다"
                        )
                )
        );
    }

    public ArrayList<PublicationData> getDummyPublic() {
        return dummyPublic;
    }

    public void setDummyPublic(ArrayList<PublicationData> dummyPublic) {
        this.dummyPublic = dummyPublic;
    }
}
