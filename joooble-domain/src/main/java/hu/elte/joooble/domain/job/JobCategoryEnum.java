package hu.elte.joooble.domain.job;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum JobCategoryEnum {
    OFFICE_JOB ("Office Job"),
    ADMINISTRATION ("Administration"),
    CONTSTRUCTION ("Contruction"),
    REAL_ESTATE ("Real-estate"),
    HR ("HR"),
    LABOR ("Labor"),
    PUBLIC_ADMINISTRATION ("Public administration"),
    EDUCATION ("Education"),
    SCIENCE ("Science"),
    SPORT ("Sport"),
    CUSTOMER_SERVICE ("Customer service"),
    BANK ("Bank"),
    INSURANCE ("Insurance"),
    SALESMANSHIP ("Salesmanship"),
    COMMERCE ("Commerce"),
    IT ("IT"),
    MARKETING ("Marketing"),
    MEDIA ("Media"),
    FINANCE ("Finance"),
    BOOKING ("Booking"),
    PHYSICAL ("Physical"),
    ENGINEERING ("Engineering"),
    PROFESSION ("Profession"), //eredeti szó: szakmunka, szóval ez lehet nem fix
    HEALTHCARE ("Healthcare"),
    PHARMACUTICAL_INDUSTRY ("Pharmacutical industry"),
    FACTORY_JOB ("Factory job"),
    LAW ("Law"),
    AGRICULTURE ("Agriculture"),
    SHIPPING ("Shipping");
    
    final private String name;
	
	private JobCategoryEnum(String name) {
		this.name = name;
	}
    
    public String getCategory() {
		return name;
	}

    public static Map<JobCategoryEnum, String> toMap() {
		return Stream.of(values()).collect(Collectors.toMap(c -> c, c -> c.name));
	}
}
