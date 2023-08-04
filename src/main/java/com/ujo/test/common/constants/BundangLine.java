package com.ujo.test.common.constants;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum BundangLine {
    WANGSIPRI ("K210", "왕십리역"),
    SEOULSUP("K211", "서울숲역"),
    APGUJEONGRODEO("K212", "압구정로데오역"),
    GANGNAMGUCHEONG("K213", "강남구청역"),
    SEONJEONGREUNG("K214", "선정릉역"),
    SEONREUNG("K215", "선릉역"),
    HANTI("K216", "한티역"),
    DOGOG("K217", "도곡역"),
    GURYONG("K218", "구룡역"),
    GAEPODONG("K219", "개포동역"),
    DAEMOSANIPGU("K220", "대모산입구역"),
    SUSEO("K221", "수서역"),
    BOKJEONG("K222", "복정역"),
    GACHEONDAE("K223", "가천대역"),
    TAEPYEONG("K224", "태평역"),
    MORAN("K225", "모란역"),
    YATAB("K226", "야탑역"),
    IMAE("K227", "이매역"),
    SEOHYEON("K228", "서현역"),
    SUNAE("K229", "수내역"),
    JEONGJA("K230", "정자역"),
    MIGEUM("K231", "미금역"),
    ORI("K232", "오리역"),
    JUKJEON("K233", "죽전역"),
    BOJEONG("K234", "보정역"),
    GUSEONG("K235", "구성역"),
    SINGAR("K236", "신갈역"),
    GIHEUNG("K237", "기흥역"),
    SANGGAR("K238", "상갈역"),
    CHEONGMYEONG("K239", "청명역"),
    YEONGTONG("K240", "영통역"),
    MANGPO("K241", "망포역"),
    MAETANGWONSEON("K242", "매탄권선역"),
    SUWONSICHEONG("K243", "수원시청역"),
    MAEGYO("K244", "매교역"),
    SUWON("K245", "수원역"),
    GOSAEG("K246", "고색역");


    private final String code;
    private final String koreanName;

    BundangLine(String code, String koreanName) {
        this.code = code;
        this.koreanName = koreanName;
    }

    public String code() {
        return code;
    }

    public String koreanName() {
        return koreanName;
    }

    private static final Map<String, BundangLine> BY_CODE =
            Stream.of(values()).collect(Collectors.toMap(BundangLine::code, Function.identity()));

    private static final Map<String, BundangLine> BY_NAME =
            Stream.of(values()).collect(Collectors.toMap(BundangLine::koreanName, Function.identity()));

    public static BundangLine valueOfCode(String code) {
        return BY_CODE.get(code);
    }

    public static BundangLine valueOfName(String koreanName) {
        return BY_NAME.get(koreanName);
    }
}
