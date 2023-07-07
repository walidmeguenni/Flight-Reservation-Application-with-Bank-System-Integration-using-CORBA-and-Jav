package AirLineCorba;


/**
* AirLineCorba/BookingHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Airline.idl
* Thursday, December 22, 2022 12:35:23 PM CET
*/

abstract public class BookingHelper
{
  private static String  _id = "IDL:AirLineCorba/Booking:1.0";

  public static void insert (org.omg.CORBA.Any a, AirLineCorba.Booking that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static AirLineCorba.Booking extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [12];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[0] = new org.omg.CORBA.StructMember (
            "bookingId",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[1] = new org.omg.CORBA.StructMember (
            "volId",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[2] = new org.omg.CORBA.StructMember (
            "clientId",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "firstName",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[4] = new org.omg.CORBA.StructMember (
            "lastName",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[5] = new org.omg.CORBA.StructMember (
            "airport",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[6] = new org.omg.CORBA.StructMember (
            "destination",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[7] = new org.omg.CORBA.StructMember (
            "takeDate",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_double);
          _members0[8] = new org.omg.CORBA.StructMember (
            "totalTicktite",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[9] = new org.omg.CORBA.StructMember (
            "bookingDate",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_double);
          _members0[10] = new org.omg.CORBA.StructMember (
            "price",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_long);
          _members0[11] = new org.omg.CORBA.StructMember (
            "ccp",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (AirLineCorba.BookingHelper.id (), "Booking", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static AirLineCorba.Booking read (org.omg.CORBA.portable.InputStream istream)
  {
    AirLineCorba.Booking value = new AirLineCorba.Booking ();
    value.bookingId = istream.read_long ();
    value.volId = istream.read_long ();
    value.clientId = istream.read_long ();
    value.firstName = istream.read_string ();
    value.lastName = istream.read_string ();
    value.airport = istream.read_string ();
    value.destination = istream.read_string ();
    value.takeDate = istream.read_string ();
    value.totalTicktite = istream.read_double ();
    value.bookingDate = istream.read_string ();
    value.price = istream.read_double ();
    value.ccp = istream.read_long ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, AirLineCorba.Booking value)
  {
    ostream.write_long (value.bookingId);
    ostream.write_long (value.volId);
    ostream.write_long (value.clientId);
    ostream.write_string (value.firstName);
    ostream.write_string (value.lastName);
    ostream.write_string (value.airport);
    ostream.write_string (value.destination);
    ostream.write_string (value.takeDate);
    ostream.write_double (value.totalTicktite);
    ostream.write_string (value.bookingDate);
    ostream.write_double (value.price);
    ostream.write_long (value.ccp);
  }

}