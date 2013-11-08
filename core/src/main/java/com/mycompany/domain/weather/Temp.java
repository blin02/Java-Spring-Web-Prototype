
package com.mycompany.domain.weather;

public class Temp {

    protected String morningLow;
    protected String daytimeHigh;

    /**
     * Gets the value of the morningLow property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMorningLow() {
        return morningLow;
    }

    /**
     * Sets the value of the morningLow property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMorningLow(String value) {
        this.morningLow = value;
    }

    /**
     * Gets the value of the daytimeHigh property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaytimeHigh() {
        return daytimeHigh;
    }

    /**
     * Sets the value of the daytimeHigh property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaytimeHigh(String value) {
        this.daytimeHigh = value;
    }

}
