package br.com.caelum.vraptor.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Before;
import org.junit.Test;

public class DoubleConverterTest {
    
    private DoubleConverter converter;

    @Before
    public void setup() {
        this.converter = new DoubleConverter();
    }
    
    @Test
    public void shouldBeAbleToConvertNumbers(){
        assertThat((Double) converter.convert("2.3", Double.class), is(equalTo(2.3d)));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void shouldComplainAboutInvalidNumber() {
        converter.convert("---", Double.class);
    }
    
    @Test
    public void shouldComplainAboutNull() {
        assertThat(converter.convert(null, Double.class), is(nullValue()));
    }

}
