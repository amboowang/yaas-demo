package com.sample.wishlistDemo.api.generated;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Generated dto.
 */
@javax.annotation.Generated(value = "hybris", date = "Tue Feb 21 05:15:54 EST 2017")
@XmlRootElement
@JsonAutoDetect(isGetterVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE,
		creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.NONE)
public class Amount
{

	@com.fasterxml.jackson.annotation.JsonProperty(value="amounts")
	@javax.validation.constraints.DecimalMin(value="0")
	@javax.validation.constraints.NotNull
	private java.lang.Double _amounts;
	
	public java.lang.Double getAmounts()
	{
		return _amounts;
	}

	public void setAmounts(final java.lang.Double _amounts)
	{
		this._amounts = _amounts;
	}

}
