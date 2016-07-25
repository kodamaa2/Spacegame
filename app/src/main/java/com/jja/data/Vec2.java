package com.jja.data;

/**
 * Created by Andreas on 25.07.2016.
 */
public class Vec2 {

    public float x;
    public float y;

    public Vec2( float x, float y )
    {
        this.x = x;
        this.y = y;
    }

    /**
     * erstellt einen Vektor mit [0,0]
     */
    public Vec2()
    {
        this( 0.0f, 0.0f );
    }

    /**
     * erstellt einen Vektor mit [value, value]
     */
    public Vec2( float value )
    {
        this( value, value );
    }

    public Vec2( Vec2 vec )
    {
        this( vec.x, vec.y );
    }

    /**
     * addiert right zum Vektor der add aufruft
     */
    public Vec2 add( Vec2 right )
    {
        this.x += right.x;
        this.y += right.y;

        return this;
    }

    /**
     * liefert Ergebnis der Addition
     */
    public static Vec2 add( Vec2 left, Vec2 right )
    {
        return new Vec2( left.x + right.x,
                left.y + right.y );
    }

    /**
     * subtrahiert right von Vektor der sub aufruft
     */
    public Vec2 sub( Vec2 right )
    {
        this.x -= right.x;
        this.y -= right.y;

        return this;
    }

    /**
     * liefert Ergebnis der Subtraktion
     */
    public static Vec2 sub( Vec2 left, Vec2 right )
    {
        return new Vec2( left.x - right.x,
                left.y - right.y );
    }

    /**
     * multipliziert den Vektor der mul aufruft mit value
     */
    public Vec2 mul( float value )
    {
        this.x *= value;
        this.y *= value;

        return this;
    }

    /**
     *  liefert Ergebnis der Multiplikation
     */
    public static Vec2 mul( Vec2 vec, float value )
    {
        return new Vec2( vec.x * value,
                vec.y * value );
    }

    /**
     *  liefert Ergebnis des Skalarprodukts
     */
    public static float dot( Vec2 left, Vec2 right )
    {
        return left.x * right.x + left.y * right.y;
    }

    /**
     *  liefert Laenge des Vektors
    */
    public static float length( Vec2 vec )
    {
        return (float) Math.sqrt( dot(vec, vec) );
    }

    /**
     *  normalisiert den Vektor, der normalize() aufruft
     */
    public Vec2 normalize()
    {
        float lengthInverted = 1.0f / length( this );

        this.x *= lengthInverted;
        this.y *= lengthInverted;

        return this;
    }

    /**
     *  liefert Ergebnis der Normalisierung ( in Vec2 )
     */
    public static Vec2 normalize( Vec2 vec )
    {
        Vec2 result = new Vec2( vec );

        return result.normalize();
    }
    /**
     *  liefert Vektor als String
     */
    public String toString()
    {
        return "[" + this.x + ", " + this.y + "]";
    }

    @Override
    public boolean equals( Object obj )
    {
        if( obj == this )
            return true;

        if( obj == null || obj.getClass() != this.getClass() )
            return false;

        Vec2 vec = (Vec2) obj;
        if( this.x == vec.x
                && ( this.y == vec.y )) {
            return true;
        }
        else {
            return false;
        }

    }
}
