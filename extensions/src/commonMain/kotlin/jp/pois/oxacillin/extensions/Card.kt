package jp.pois.oxacillin.extensions

public interface Card {
    public val bindingValues: jp.pois.oxacillin.extensions.Card.BindingValues

    public interface BindingValues {
        public val choice1Label: jp.pois.oxacillin.extensions.Card.BindingValues.StringValue?
        public val choice2Label: jp.pois.oxacillin.extensions.Card.BindingValues.StringValue?
        public val choice3Label: jp.pois.oxacillin.extensions.Card.BindingValues.StringValue?
        public val choice4Label: jp.pois.oxacillin.extensions.Card.BindingValues.StringValue?

        public val choice1Count: jp.pois.oxacillin.extensions.Card.BindingValues.StringValue?
        public val choice2Count: jp.pois.oxacillin.extensions.Card.BindingValues.StringValue?
        public val choice3Count: jp.pois.oxacillin.extensions.Card.BindingValues.StringValue?
        public val choice4Count: jp.pois.oxacillin.extensions.Card.BindingValues.StringValue?

        public val countsAreFinal: jp.pois.oxacillin.extensions.Card.BindingValues.BooleanValue?
        public val endDatetimeUtc: jp.pois.oxacillin.extensions.Card.BindingValues.StringValue?
        public val lastUpdatedDatetimeUtc: jp.pois.oxacillin.extensions.Card.BindingValues.StringValue?
        public val durationMinutes: jp.pois.oxacillin.extensions.Card.BindingValues.StringValue?

        public interface StringValue {
            public val type: String
            public val stringValue: String
        }

        public interface BooleanValue {
            public val type: String
            public val booleanValue: Boolean
        }
    }
}